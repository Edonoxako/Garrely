package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import live.senya.garrely.entity.local.db.PageToPhoto

@Dao
interface PageToPhotoDao {

    @Insert
    fun insert(pagesToPhotoDao: Collection<PageToPhoto>)
}
