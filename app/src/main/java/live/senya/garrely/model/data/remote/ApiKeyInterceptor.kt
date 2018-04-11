package live.senya.garrely.model.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter(KEY_PARAM_NAME, apiKey)
                .build()

        val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
        
        return chain.proceed(request)
    }
    
    companion object {
        private const val KEY_PARAM_NAME = "key"
    }
}