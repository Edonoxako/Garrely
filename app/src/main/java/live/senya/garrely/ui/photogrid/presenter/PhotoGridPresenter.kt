package live.senya.garrely.ui.photogrid.presenter

import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import live.senya.garrely.model.interactor.photo.PhotoInteractor
import live.senya.garrely.ui.base.RxPresenter
import live.senya.garrely.ui.photogrid.view.PhotoGridView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class PhotoGridPresenter @Inject constructor(
        private val interactor: PhotoInteractor
) : RxPresenter<PhotoGridView>() {

    override fun onFirstViewAttach() {
        interactor.getPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewState::showPhotos, Timber::e)
    }

    fun openPhotoPager(photoId: Long) {
        
    }
    
}