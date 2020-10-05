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
    suspend fun insertCats(cats: List<Cat>)

    @Query("SELECT * FROM cats LIMIT :limit OFFSET :offset")
    suspend fun getCats(offset: Int, limit: Int = Constant.PAGE_COUNT): List<Cat>

    @Query("DELETE FROM cats")
    suspend fun deleteAllCat()
}