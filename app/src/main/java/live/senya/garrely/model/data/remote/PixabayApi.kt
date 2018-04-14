package live.senya.garrely.model.data.remote

import io.reactivex.Single
import live.senya.garrely.entity.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun search(
            @Query("page") page: Int,
            @Query("editors_choice") editorsChoice: Boolean,
            @Query("safesearch") safeSearch: Boolean
    ): Single<ApiResponse>
}