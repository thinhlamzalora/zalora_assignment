package zalora.assignment.presentation.di.core.module

import dagger.Module
import dagger.Provides
import zalora.assignment.data.api.CatAPI
import zalora.assignment.data.db.CatDao
import zalora.assignment.data.repository.cat.CatDataSource
import zalora.assignment.data.repository.cat.CatLocalDataSource
import zalora.assignment.data.repository.cat.CatRemoteDataSource
import zalora.assignment.data.repository.cat.CatRepositoryImpl
import zalora.assignment.domain.repository.CatRepository
import zalora.assignment.domain.usecase.GetCatsUseCase
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideCatRepository(catRemote: CatDataSource.Remote,
                             catLocal: CatDataSource.Local
    ): CatRepository {
        return CatRepositoryImpl(catRemote, catLocal)
    }

    @Provides
    @Singleton
    fun provideCatLocalDataSource(
        catDao: CatDao
    ): CatDataSource.Local {
        return CatLocalDataSource(catDao)
    }


    @Provides
    @Singleton
    fun provideCatRemoveDataSource(catAPI: CatAPI): CatDataSource.Remote {
        return CatRemoteDataSource(catAPI)
    }

    @Provides
    fun provideGetCatsUseCase(catRepository: CatRepository): GetCatsUseCase {
        return GetCatsUseCase(catRepository)
    }
}