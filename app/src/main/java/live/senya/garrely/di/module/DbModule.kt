package live.senya.garrely.di.module

import live.senya.garrely.di.provider.DbProvider
import live.senya.garrely.model.data.local.db.GarrelyDb
import toothpick.config.Module

class DbModule : Module() {
    
    init {
        bind(GarrelyDb::class.java)
                .toProvider(DbProvider::class.java)
                .providesSingletonInScope()
    }
}