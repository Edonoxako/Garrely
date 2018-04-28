package live.senya.garrely.model.interactor.photo

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import live.senya.garrely.entity.NetworkState
import live.senya.garrely.entity.State
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.model.repository.networkstate.NetworkStateRepository
import live.senya.garrely.model.repository.photo.PhotoRepository
import live.senya.garrely.util.extension.addTo
import java.lang.IllegalStateException
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
        private val photoRepository: PhotoRepository,
        private val networkStateRepository: NetworkStateRepository
) {

    private val relay = BehaviorSubject.create<State>()
    private val subscriptions = CompositeDisposable()

            // todo clean up on terminate
    fun observeState(): Observable<State> {
        return relay.scan { accumulated: State, item: State ->
            item.apply { data.putAll(accumulated.data) }
        }
    }

    fun requestPage(query: String, number: Int) {
        photoRepository.observePage(query, number)
                .switchMap {
                    when {
                        it.isEmpty() -> fetchPage(query, number)
                        it.first().isFresh.not() -> refreshPage(it.first())
                        else -> getPhotos(it.first())
                    }
                }
                .subscribe(relay::onNext)
                .addTo(subscriptions)
    }

    private fun refreshPage(page: Page): Observable<State> {
        return getPhotos(page).concatMap { dataState ->
            fetchPage(page.query, page.number, true)
                    .map { it.copy(data = dataState.data) }
        }
    }

    private fun getPhotos(page: Page): Observable<State> {
        return photoRepository.getPhotos(page)
                .map { State(data = sortedMapOf(page to it)) }
                .toObservable()
    }

    private fun fetchPage(
            query: String,
            number: Int,
            silentFetch: Boolean = false
    ): Observable<State> {
        return networkStateRepository.observeNetworkState()
                .flatMap {
                    when (it) {
                        NetworkState.DISCONNECTED -> Observable.just(
                                State(error = IllegalStateException())
                        )
                        NetworkState.CONNECTED ->
                            photoRepository.fetchPage(query, number)
                                    .toObservable<State>()
                                    .startWith(
                                            State(isLoading = !silentFetch)
                                    )
                                    .onErrorReturnItem(
                                            State(error = IllegalStateException())
                                    )
                    }
                }
    }
    
}