package live.senya.garrely.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import live.senya.garrely.ui.common.adapter.DiffCalculable

@Entity(tableName = Photo.TABLE_NAME)
data class Photo(

        @PrimaryKey
        @ColumnInfo(name = Photo.COLUMN_NAME_ID)
        var id: Long,

        @ColumnInfo(name = "preview_url")
        @Json(name = "webformatURL")
        var previewUrl: String

) : DiffCalculable {

    override fun isTheSame(other: DiffCalculable): Boolean {
        if (other !is Photo) return false
        return id == other.id
    }

    override fun isContentTheSame(other: DiffCalculable) = true

    companion object {
        const val TABLE_NAME = "photos"
        
        const val COLUMN_NAME_ID = "id"
    }
}