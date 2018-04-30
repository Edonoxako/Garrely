package live.senya.garrely.model.repository.photo

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import live.senya.garrely.entity.Constants.USE_SAFE_SEARCH
import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.entity.mapper.PhotoPageMapper
import live.senya.garrely.model.data.remote.PixabayApi
import live.senya.garrely.model.store.photo.PhotoStore
import javax.inject.Inject

class PhotoRepository @Inject constructor(
        private val photoApi: PixabayApi,
        private val photoStore: PhotoStore,
        private val mapper: PhotoPageMapper
) {

    fun fetchPage(query: String, number: Int): Completable {
        return photoApi.search(
                page = number, 
                editorsChoice = true, 
                safeSearch = USE_SAFE_SEARCH
        )
                .map { response ->
                    val page = Page(query, number, System.currentTimeMillis())
                    
                    mapper.map(page, response)
                }
                .flatMapCompletable(photoStore::save)
                .subscribeOn(Schedulers.io())
    }

    fun observePage(query: String, number: Int): Observable<List<Page>> {
        return photoStore.observePage(query, number)
                .toObservable()
                .subscribeOn(Schedulers.io())
    }

    fun getPhotos(page: Page): Single<List<Photo>> {
        return photoStore.getPhotos(page)
                .subscribeOn(Schedulers.io())
    }
    
}