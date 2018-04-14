package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import live.senya.garrely.entity.Photo

@Dao
interface PhotoDao {
    
    @Insert
    fun insert(photos: Iterable<Photo>)
}