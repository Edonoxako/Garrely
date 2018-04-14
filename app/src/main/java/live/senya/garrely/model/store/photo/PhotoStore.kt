package live.senya.garrely.model.store.photo

import live.senya.garrely.entity.local.QueryWithPhoto
import live.senya.garrely.model.data.local.db.GarrelyDb
import javax.inject.Inject

class PhotoStore @Inject constructor(
        private val db: GarrelyDb
) {

    // wip
    fun save(queryWithPhoto: QueryWithPhoto) {
//        val querysToPhoto = QueryToPhoto()
        
        db.executeInTransaction {
            searchQueryDao().insert(queryWithPhoto.queries)
            photoDao().insert(queryWithPhoto.photos)
            
        }
    }
}