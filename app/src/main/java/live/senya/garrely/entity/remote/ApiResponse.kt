package live.senya.garrely.entity.remote

import com.squareup.moshi.Json
import live.senya.garrely.entity.Photo

data class ApiResponse(

        val total: Long,
        
        @Json(name = "hits")
        val photos: List<Photo>
)