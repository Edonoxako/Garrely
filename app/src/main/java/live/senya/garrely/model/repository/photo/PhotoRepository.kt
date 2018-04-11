package live.senya.garrely.model.repository.photo

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import live.senya.garrely.entity.Photo
import live.senya.garrely.model.data.remote.PixabayApi
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val photoApi: PixabayApi
) {

    fun getPhotos(): Single<List<Photo>> {
        return photoApi.search(1, true, true)
                .map { it.photos }
                .subscribeOn(Schedulers.io())
    }
}