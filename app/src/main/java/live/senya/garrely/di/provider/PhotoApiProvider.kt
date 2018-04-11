package live.senya.garrely.di.provider

import live.senya.garrely.model.data.remote.PixabayApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class PhotoApiProvider @Inject constructor(
        private val retrofit: Retrofit
) : Provider<PixabayApi> {
    
    override fun get(): PixabayApi = retrofit.create(PixabayApi::class.java)
}