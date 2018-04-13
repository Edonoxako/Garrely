package live.senya.garrely.entity

import com.squareup.moshi.Json
import live.senya.garrely.ui.common.adapter.DiffCalculable

data class Photo(

        val id: Long,

        @Json(name = "webformatURL")
        val previewUrl: String

) : DiffCalculable {

    override fun isTheSame(other: DiffCalculable): Boolean {
        if (other !is Photo) return false
        return id == other.id
    }

    override fun isContentTheSame(other: DiffCalculable) = true
}