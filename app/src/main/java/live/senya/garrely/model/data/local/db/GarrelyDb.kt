package live.senya.garrely.model.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.QueryToPhoto
import live.senya.garrely.entity.local.SearchQuery
import live.senya.garrely.model.data.local.db.dao.PhotoDao
import live.senya.garrely.model.data.local.db.dao.QueryToPhotoDao
import live.senya.garrely.model.data.local.db.dao.SearchQueryDao

@Database(
        entities = [
            Photo::class,
            SearchQuery::class,
            QueryToPhoto::class
        ],
        version = GarrelyDb.DB_VERSION
)
abstract class GarrelyDb : RoomDatabase() {
    
    abstract fun photoDao(): PhotoDao
    abstract fun searchQueryDao(): SearchQueryDao
    abstract fun queryToPhotoDao(): QueryToPhotoDao

    fun executeInTransaction(func: GarrelyDb.() -> Unit) = runInTransaction { func.invoke(this) }
    
    companion object {
        const val DB_NAME = "garrely_db"
        const val DB_VERSION = 1
    }
}