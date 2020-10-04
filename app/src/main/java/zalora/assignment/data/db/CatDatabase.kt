package zalora.assignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import zalora.assignment.domain.model.Cat

@Database(entities = [Cat::class], version = 1, exportSchema = false)
abstract class CatDatabase : RoomDatabase(){
    abstract fun catDao(): CatDao
}