package zalora.assignment.domain.repository

import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.utils.Result

interface CatRepository {
    suspend fun getCats(page: Int, forceInternet: Boolean): Result<List<Cat>>

}