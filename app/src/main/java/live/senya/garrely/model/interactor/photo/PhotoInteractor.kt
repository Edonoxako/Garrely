package live.senya.garrely.model.interactor.photo

import io.reactivex.Single
import live.senya.garrely.entity.Photo

interface PhotoInteractor {
    
    fun getPhotos(): Single<List<Photo>>
}