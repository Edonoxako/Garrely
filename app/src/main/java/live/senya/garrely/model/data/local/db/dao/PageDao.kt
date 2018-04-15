package live.senya.garrely.model.data.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import live.senya.garrely.entity.local.db.Page

@Dao
interface PageDao {

    @Insert
    fun insert(page: Page): Long

    @Query(Page.QUERY_BY_SEARCH_QUERY_AND_PAGE_NUMBER)
    fun get(searchQuery: String, pageNumber: Int): Maybe<Page>
}