package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import live.senya.garrely.entity.local.QueryToPhoto
import live.senya.garrely.entity.local.QueryWithPhoto

@Dao
interface QueryToPhotoDao {
    
    @Insert
    fun insert(queriesWithPhoto: Iterable<QueryToPhoto>)
    
    @Query(QueryToPhoto.QUERY_BY_SEARCH_QUERY_TEXT)
    fun getByQueryText(queryText: String): Single<List<QueryWithPhoto>>
}
