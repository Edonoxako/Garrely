package live.senya.garrely.di.provider

import android.arch.persistence.room.Room
import android.content.Context
import live.senya.garrely.model.data.local.db.GarrelyDb
import javax.inject.Inject
import javax.inject.Provider

class DbProvider @Inject constructor(
        private val context: Context
) : Provider<GarrelyDb> {
    
    override fun get(): GarrelyDb {
        return Room.databaseBuilder(context, GarrelyDb::class.java, GarrelyDb.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}