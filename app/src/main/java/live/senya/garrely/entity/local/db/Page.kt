package live.senya.garrely.entity.local.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import live.senya.garrely.entity.Constants

@Entity(tableName = Page.TABLE_NAME)
data class Page(

        @ColumnInfo(name = COLUMN_NAME_QUERY)
        var query: String,

        @ColumnInfo(name = COLUMN_NAME_NUMBER)
        var number: Int,

        var date: Long = System.currentTimeMillis()

) : Comparable<Page> {

    override fun compareTo(other: Page): Int {
        return number.compareTo(other.number)
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_NAME_ID)
    var id: Long = 0L

    val isFresh: Boolean
        get() {
            val timePassed = System.currentTimeMillis() - date
            return timePassed < Constants.CACHE_FADE_TIME
        }

    companion object {
        const val TABLE_NAME = "pages"

        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_QUERY = "query"
        const val COLUMN_NAME_NUMBER = "number"
        
        const val QUERY_BY_SEARCH_QUERY_AND_PAGE_NUMBER = """
            SELECT * 
            FROM ${Page.TABLE_NAME}
            WHERE 
                ${Page.COLUMN_NAME_QUERY} = :searchQuery
                AND
                ${Page.COLUMN_NAME_NUMBER} = :pageNumber
        """
    }
}
