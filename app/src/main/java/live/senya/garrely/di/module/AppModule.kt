package live.senya.garrely.di.module

import android.content.Context
import live.senya.garrely.BuildConfig
import live.senya.garrely.R
import live.senya.garrely.di.qualifier.ApiKey
import live.senya.garrely.di.qualifier.ApiUrl
import toothpick.config.Module

class AppModule(context: Context) : Module() {

    init {
        bind(Context::class.java).toInstance(context)

        bind(String::class.java)
                .withName(ApiUrl::class.java)
                .toInstance(BuildConfig.API_URL)
        
        bind(String::class.java)
                .withName(ApiKey::class.java)
                .toInstance(
                        context.getString(R.string.api_key)
                )
    }
}