package live.senya.garrely.model.store.photo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.PhotoPage
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.entity.local.db.PageToPhoto
import live.senya.garrely.model.data.local.db.GarrelyDb
import javax.inject.Inject

class PhotoStore @Inject constructor(
        private val db: GarrelyDb
) {

    fun save(photoPage: PhotoPage): Completable = Completable.fromCallable {
        db.executeInTransaction {
            val pageId = pageDao().insert(photoPage.page)
            val photoIds = photoDao().insert(photoPage.photos)

            val pagesToPhotos = photoIds.map { PageToPhoto(pageId, it) }

            pageToPhotoDao().insert(pagesToPhotos)
        }
    }

    fun getPhotos(page: Page): Single<List<Photo>> {
        return db.photoDao().getByPageId(page.id)
    }

    fun observePage(searchQuery: String, pageNumber: Int): Flowable<List<Page>> {
        return db.pageDao().observe(searchQuery, pageNumber)
    }
}