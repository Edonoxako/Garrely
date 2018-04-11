package live.senya.garrely.ui.main.view

import com.arellomobile.mvp.MvpView
import live.senya.garrely.entity.Photo

interface MainView : MvpView {

    fun displayPhotos(photos: List<Photo>)
}