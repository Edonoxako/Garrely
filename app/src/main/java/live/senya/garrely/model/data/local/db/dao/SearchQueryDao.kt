package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import live.senya.garrely.entity.local.SearchQuery

@Dao
interface SearchQueryDao {

    @Insert
    fun insert(queries: Iterable<SearchQuery>)
}