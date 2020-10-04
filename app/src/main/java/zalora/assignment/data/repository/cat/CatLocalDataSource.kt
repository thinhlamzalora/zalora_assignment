package zalora.assignment.data.repository.cat

import zalora.assignment.data.db.CatDao
import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.utils.Result

class CatLocalDataSource (private val catDao: CatDao): CatDataSource.Local{
    override suspend fun saveCats(cats: List<Cat>) {
        catDao.insert(cats)
    }

    override suspend fun getCats(page: Int): Result<List<Cat>> {
        val cats = catDao.getCats(pageIndex = page)
        return if(cats.isEmpty()){
            Result.Error("")
        }else
            Result.Success(cats)
    }


}