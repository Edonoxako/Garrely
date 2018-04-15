package live.senya.garrely.model.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import live.senya.garrely.entity.Photo
import live.senya.garrely.entity.local.db.Page
import live.senya.garrely.entity.local.db.PageToPhoto
import live.senya.garrely.model.data.local.db.dao.PageDao
import live.senya.garrely.model.data.local.db.dao.PageToPhotoDao
import live.senya.garrely.model.data.local.db.dao.PhotoDao

@Database(
        entities = [
            Photo::class,
            Page::class,
            PageToPhoto::class
        ],
        version = GarrelyDb.DB_VERSION
)
abstract class GarrelyDb : RoomDatabase() {
    
    abstract fun photoDao(): PhotoDao
    abstract fun pageDao(): PageDao
    abstract fun pageToPhotoDao(): PageToPhotoDao

    fun executeInTransaction(func: GarrelyDb.() -> Unit) = runInTransaction { func.invoke(this) }
    
    companion object {
        const val DB_NAME = "garrely_db"
        const val DB_VERSION = 1
    }
}