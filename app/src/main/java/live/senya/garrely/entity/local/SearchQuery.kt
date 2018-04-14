package live.senya.garrely.entity.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = SearchQuery.TABLE_NAME)
data class SearchQuery(

        @ColumnInfo(name = SearchQuery.COLUMN_NAME_TEXT)
        var text: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SearchQuery.COLUMN_NAME_ID)
    var id: Long = 0L

    companion object {
        const val TABLE_NAME = "queries"
        
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TEXT = "text"
    }
}

