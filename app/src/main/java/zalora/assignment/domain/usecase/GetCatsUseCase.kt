package zalora.assignment.domain.usecase

import zalora.assignment.domain.repository.CatRepository

class GetCatsUseCase (private val catRepository: CatRepository){
    suspend fun execute(page: Int, forceInternet: Boolean) = catRepository.getCats(page, forceInternet)
}