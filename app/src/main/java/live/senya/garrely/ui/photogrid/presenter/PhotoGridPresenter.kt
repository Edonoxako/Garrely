package live.senya.garrely.ui.photogrid.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import live.senya.garrely.entity.Photo
import live.senya.garrely.model.interactor.photo.PhotoInteractor
import live.senya.garrely.ui.base.RxPresenter
import live.senya.garrely.ui.photogrid.view.PhotoGridView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class PhotoGridPresenter @Inject constructor(
        private val interactor: PhotoInteractor
) : RxPresenter<PhotoGridView>() {

    private val cache: MutableList<Photo> = ArrayList()

    private var currentPage = 1
    private var isLoading = false

    override fun onFirstViewAttach() {
        fetchPage(1)
    }

    private fun fetchPage(page: Int) {
        interactor.getPhotos(page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading = true }
                .doOnSuccess { cache.addAll(it) }
                .doFinally { isLoading = false }
                .map { cache }
                .subscribe(viewState::showPhotos, Timber::e)
                .store()
    }

    fun fetchNextPage() {
        if (isLoading) return
        fetchPage(++currentPage)
    }

    fun openPhotoPager(photoId: Long) {

    }

}