package live.senya.garrely.ui.photogrid.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import live.senya.garrely.entity.State
import live.senya.garrely.model.interactor.photo.PhotoInteractor
import live.senya.garrely.ui.base.RxPresenter
import live.senya.garrely.ui.photogrid.view.PhotoGridView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class PhotoGridPresenter @Inject constructor(
        private val interactor: PhotoInteractor
) : RxPresenter<PhotoGridView>() {

    private var lastPageNumber = 0

    override fun onFirstViewAttach() {
        interactor.observeState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::applyState, Timber::e)
                .store()

        requestNextPage()
    }

    fun requestNextPage() = interactor.requestPage("", lastPageNumber.inc())

    fun openPhotoPager(photoId: Long) {

    }

    private fun applyState(state: State) {
        state.data.takeIf { it.isNotEmpty() }
                ?.lastKey()
                ?.number
                ?.let { lastPageNumber = it }
        
        viewState.showPhotos(state.data.flatMap { it.value })
    }

}