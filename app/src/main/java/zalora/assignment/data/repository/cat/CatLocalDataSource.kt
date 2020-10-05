package zalora.assignment.data.repository.cat

import zalora.assignment.data.db.CatDao
import zalora.assignment.data.utils.Constant
import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.utils.Result

class CatLocalDataSource (private val catDao: CatDao): CatDataSource.Local{
    override suspend fun saveCats(cats: List<Cat>) {
        catDao.insertCats(cats)
    }

    override suspend fun deleteCats() {
        catDao.deleteAllCat()
    }

    override suspend fun getCats(page: Int): Result<List<Cat>> {
        val cats = catDao.getCats(offset = (page-1) * Constant.PAGE_COUNT)
        return if(cats.isEmpty()){
            Result.Error("")
        }else
            Result.Success(cats)
    }


}