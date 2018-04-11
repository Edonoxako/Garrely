package live.senya.garrely.di.provider

import com.facebook.stetho.okhttp3.StethoInterceptor
import live.senya.garrely.BuildConfig
import live.senya.garrely.di.qualifier.ApiKey
import live.senya.garrely.model.data.remote.ApiKeyInterceptor
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Provider

class OkHttpProvider @Inject constructor(
        @ApiKey private val apiKey: String
) : Provider<OkHttpClient> {
    
    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(
                        ApiKeyInterceptor(apiKey)
                )
                .apply { 
                    if (BuildConfig.DEBUG) addNetworkInterceptor(StethoInterceptor())
                }
                .build()
    }
}