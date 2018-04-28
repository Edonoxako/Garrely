package live.senya.garrely.model.repository.photo

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.PhotoPage
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.model.data.remote.PixabayApi
import live.senya.garrely.model.store.photo.PhotoStore
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val photoApi: PixabayApi,
        private val photoStore: PhotoStore
) {

    fun fetchPage(query: String, number: Int): Completable {
        return photoApi.search(number, true, true)
                .map {
                    PhotoPage(
                            Page(query, number, System.currentTimeMillis()),
                            it.photos
                    )
                }
                .flatMapCompletable(photoStore::save)
                .subscribeOn(Schedulers.io())
    }

    fun observePage(query: String, number: Int): Observable<List<Page>> {
        return photoStore.observePage(query, number)
                .toObservable()
                .subscribeOn(Schedulers.io())
    }

    fun getPhotos(page: Page): Single<List<Photo>> {
        return photoStore.getPhotos(page)
                .subscribeOn(Schedulers.io())
    }

}