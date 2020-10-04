package zalora.assignment.data.repository.cat

import zalora.assignment.data.api.CatAPI
import zalora.assignment.data.mapper.CatMapper
import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.utils.Result
import java.lang.Exception

class CatRemoteDataSource (private val catAPI: CatAPI): CatDataSource.Remote{
    override suspend fun getCats(page: Int): Result<List<Cat>> {
        return try {
            val result = catAPI.getCats(page = page)
            Result.Success(
                result.map {
                    CatMapper.toDomain(it)
                }
            )
        }catch (ex: Exception){
            Result.Error(ex.toString())
        }
    }

}