package zalora.assignment.presentation.di.core.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import zalora.assignment.data.db.CatDao
import zalora.assignment.data.db.CatDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCatDatabase(context: Context): CatDatabase {
        return Room.databaseBuilder(context, CatDatabase::class.java, "cat.db").build()
    }

    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao {
        return catDatabase.catDao()
    }
}