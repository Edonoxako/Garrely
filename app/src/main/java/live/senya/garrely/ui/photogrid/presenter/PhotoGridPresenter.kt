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

    private var currentPage = 0
    private var isLoading = false

    override fun onFirstViewAttach() {
        interactor.observeState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::applyState, Timber::e)
                .store()

        requestNextPage()
    }

    fun requestNextPage() {
        if (isLoading) return
        interactor.requestPage("", ++currentPage)
    }

    fun openPhotoPager(photoId: Long) {

    }

    private fun applyState(state: State) {
        viewState.showPhotos(state.data.flatMap { it.value })
        isLoading = state.isLoading
    }

}