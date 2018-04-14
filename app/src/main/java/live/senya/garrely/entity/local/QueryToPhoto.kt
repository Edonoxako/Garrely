package live.senya.garrely.entity.local

import android.arch.persistence.room.*
import live.senya.garrely.entity.Photo

@Entity(
        tableName = QueryToPhoto.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = SearchQuery::class,
                    childColumns = [(QueryToPhoto.COLUMN_NAME_QUERY_ID)],
                    parentColumns = [(SearchQuery.COLUMN_NAME_ID)],
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Photo::class,
                    childColumns = [(QueryToPhoto.COLUMN_NAME_PHOTO_ID)],
                    parentColumns = [(Photo.COLUMN_NAME_ID)],
                    onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [
            Index(
                    QueryToPhoto.COLUMN_NAME_QUERY_ID,
                    QueryToPhoto.COLUMN_NAME_PHOTO_ID,
                    unique = true
            )
        ]
)
data class QueryToPhoto(

        @ColumnInfo(name = COLUMN_NAME_QUERY_ID)
        var queryId: Long,

        @ColumnInfo(name = COLUMN_NAME_PHOTO_ID)
        var photoId: Long
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    companion object {
        const val TABLE_NAME = "query_to_photo"

        const val COLUMN_NAME_QUERY_ID = "query_id"
        const val COLUMN_NAME_PHOTO_ID = "photo_id"

        const val QUERY_BY_SEARCH_QUERY_TEXT = """
            SELECT * FROM 
            ${QueryToPhoto.TABLE_NAME} JOIN ${SearchQuery.TABLE_NAME} ON
                ${QueryToPhoto.TABLE_NAME}.${QueryToPhoto.COLUMN_NAME_QUERY_ID} = ${SearchQuery.TABLE_NAME}.${SearchQuery.COLUMN_NAME_ID} 
            WHERE ${SearchQuery.COLUMN_NAME_TEXT} = :queryText
        """
    }
}
