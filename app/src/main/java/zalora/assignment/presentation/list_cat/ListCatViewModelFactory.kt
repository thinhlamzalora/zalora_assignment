package zalora.assignment.presentation.list_cat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zalora.assignment.domain.usecase.GetCatsUseCase
import zalora.assignment.presentation.utils.DispatchersProvider

class ListCatViewModelFactory (
    private val getCatsUseCase: GetCatsUseCase,
    private val dispatchersProvider: DispatchersProvider
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListCatViewModel(getCatsUseCase, dispatchersProvider) as T
    }

}