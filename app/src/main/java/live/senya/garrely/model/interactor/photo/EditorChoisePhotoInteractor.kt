package live.senya.garrely.model.interactor.photo

import io.reactivex.Single
import live.senya.garrely.entity.Photo
import live.senya.garrely.model.repository.photo.PhotoRepository
import javax.inject.Inject

class EditorChoisePhotoInteractor @Inject constructor(
        private val photoRepository: PhotoRepository
) : PhotoInteractor {

    override fun getPhotos(page: Int): Single<List<Photo>> {
        return photoRepository.getPhotos(page)
    }
}