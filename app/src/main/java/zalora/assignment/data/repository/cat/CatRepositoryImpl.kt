package zalora.assignment.data.repository.cat

import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.repository.CatRepository
import zalora.assignment.domain.utils.Result

class CatRepositoryImpl constructor(
    private val catRemote: CatDataSource.Remote,
    private val catLocal: CatDataSource.Local
): CatRepository {
    override suspend fun getCats(page: Int, forceInternet: Boolean): Result<List<Cat>> {
        if (forceInternet) {
            return getCatsFromRemoteDataSource(page)
        }
        return getCatsFromLocalDataSource(page)
    }
    private suspend fun getCatsFromLocalDataSource(page: Int): Result<List<Cat>>
    {
        return when (val result = catLocal.getCats(page)) {
            is Result.Success -> {
                result
            }
            is Result.Error -> {
                getCatsFromRemoteDataSource(page)
            }
        }
    }
    private suspend fun getCatsFromRemoteDataSource(page: Int): Result<List<Cat>>
    {
        val result = catRemote.getCats(page)
        if (result is Result.Success) {
            saveCats(result.data)
        }
        return result
    }
    private suspend fun saveCats(cats: List<Cat>){
        catLocal.saveCats(cats)
    }
}