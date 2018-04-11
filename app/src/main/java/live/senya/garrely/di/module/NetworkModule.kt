package live.senya.garrely.di.module

import live.senya.garrely.di.provider.OkHttpProvider
import live.senya.garrely.di.provider.PhotoApiProvider
import live.senya.garrely.di.provider.RetrofitProvider
import live.senya.garrely.model.data.remote.PixabayApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class NetworkModule : Module() {
    
    init {
        bind(OkHttpClient::class.java)
                .toProvider(OkHttpProvider::class.java)
                .singletonInScope()

        bind(Retrofit::class.java)
                .toProvider(RetrofitProvider::class.java)
                .singletonInScope()
        
        bind(PixabayApi::class.java)
                .toProvider(PhotoApiProvider::class.java)
                .singletonInScope()
    }
    
}