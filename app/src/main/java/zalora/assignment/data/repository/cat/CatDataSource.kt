package zalora.assignment.data.repository.cat

import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.utils.Result

interface CatDataSource {
    interface Remote {
        suspend fun getCats(page: Int): Result<List<Cat>>
    }
    interface Local : Remote {
        suspend fun saveCats(cats: List<Cat>)
    }
}