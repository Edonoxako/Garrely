package live.senya.garrely.model.repository.photo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import live.senya.garrely.entity.Photo
import live.senya.garrely.model.data.remote.PixabayApi
import live.senya.garrely.model.store.photo.PhotoStore
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val photoApi: PixabayApi,
        private val photoStore: PhotoStore
) {

    fun getPhotos(page: Int): Single<List<Photo>> {
        return photoApi.search(page, true, true)
                .map { it.photos }
                .subscribeOn(Schedulers.io())
    }
}