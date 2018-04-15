package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import live.senya.garrely.entity.Photo

@Dao
interface PhotoDao {

    @Insert
    fun insert(photos: Collection<Photo>): List<Long>

    @Query(Photo.QUERY_BY_PAGE_ID)
    fun getByPageId(pageId: Long): Single<List<Photo>>
}