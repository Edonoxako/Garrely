package live.senya.garrely.entity.local.db

import android.arch.persistence.room.*
import live.senya.garrely.entity.Photo

@Entity(
        tableName = PageToPhoto.TABLE_NAME,
        foreignKeys = [
            ForeignKey(
                    entity = Page::class,
                    childColumns = [(PageToPhoto.COLUMN_NAME_PAGE_ID)],
                    parentColumns = [(Page.COLUMN_NAME_ID)],
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Photo::class,
                    childColumns = [(PageToPhoto.COLUMN_NAME_PHOTO_ID)],
                    parentColumns = [Photo.COLUMN_NAME_ID],
                    onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [
            Index(
                    PageToPhoto.COLUMN_NAME_PAGE_ID,
                    PageToPhoto.COLUMN_NAME_PHOTO_ID,
                    unique = true
            )
        ]
)
data class PageToPhoto(

        @ColumnInfo(name = COLUMN_NAME_PAGE_ID)
        var pageId: Long,

        @ColumnInfo(name = COLUMN_NAME_PHOTO_ID)
        var photoId: Long
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    companion object {
        const val TABLE_NAME = "query_to_photo"

        const val COLUMN_NAME_PAGE_ID = "page_id"
        const val COLUMN_NAME_PHOTO_ID = "photo_id"
    }
}
