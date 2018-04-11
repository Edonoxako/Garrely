package live.senya.garrely.di.provider

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import live.senya.garrely.di.qualifier.ApiUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
        private val okHttpClient: OkHttpClient,
        @ApiUrl private val apiUrl: String
) : Provider<Retrofit> {

    override fun get(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                        MoshiConverterFactory.create(
                                Moshi.Builder()
                                        .add(KotlinJsonAdapterFactory())
                                        .build()
                        )
                )
                .client(okHttpClient)
                .baseUrl(apiUrl)
                .build()
    }
}
