package zalora.assignment.presentation.di.list_cat

import dagger.Module
import dagger.Provides
import zalora.assignment.domain.usecase.GetCatsUseCase
import zalora.assignment.presentation.list_cat.ListCatViewModelFactory
import zalora.assignment.presentation.utils.DispatchersProvider

@Module
class ListCatModule {

    @Provides
    fun provideListCatViewModelFactory(getCatsUseCase: GetCatsUseCase, dispatchersProvider: DispatchersProvider): ListCatViewModelFactory {
        return ListCatViewModelFactory(getCatsUseCase, dispatchersProvider)
    }
}