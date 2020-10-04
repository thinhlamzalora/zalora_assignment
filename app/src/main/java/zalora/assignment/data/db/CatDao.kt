package zalora.assignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zalora.assignment.data.utils.Constant
import zalora.assignment.domain.model.Cat

@Dao
interface CatDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cats: List<Cat>)

    @Query("SELECT * FROM cats LIMIT :limit OFFSET :pageIndex")
    suspend fun getCats(pageIndex: Int, limit: Int = Constant.PAGE_COUNT): List<Cat>
}