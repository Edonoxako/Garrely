package live.senya.garrely.ui.photogrid.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import live.senya.garrely.entity.Photo

@StateStrategyType(AddToEndSingleStrategy::class)
interface PhotoGridView : MvpView {
    
    fun showPhotos(photos: List<Photo>)
}