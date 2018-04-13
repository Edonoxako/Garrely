package live.senya.garrely

import android.support.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import live.senya.garrely.di.Scopes
import live.senya.garrely.di.module.AppModule
import live.senya.garrely.di.module.InteractorModule
import live.senya.garrely.di.module.NetworkModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initStetho()
        initToothpick()
        initAppScope()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
            FactoryRegistryLocator.setRootRegistry(live.senya.garrely.FactoryRegistry())
            MemberInjectorRegistryLocator.setRootRegistry(live.senya.garrely.MemberInjectorRegistry())
        }
    }

    private fun initAppScope() {
        Toothpick.openScope(Scopes.APP_SCOPE)
                .installModules(
                        AppModule(this),
                        InteractorModule(),
                        NetworkModule()
                )
    }
}