package live.senya.garrely.entity

import com.squareup.moshi.Json

data class ApiResponse(

        val total: Long,
        
        @Json(name = "hits")
        val photos: List<Photo>
)