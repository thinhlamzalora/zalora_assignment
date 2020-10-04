package zalora.assignment.presentation.list_cat

import androidx.lifecycle.MutableLiveData
import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.usecase.GetCatsUseCase
import zalora.assignment.presentation.base.BaseViewModel
import zalora.assignment.presentation.utils.DispatchersProvider
import zalora.assignment.domain.utils.Result

class ListCatViewModel internal constructor(
    private val getCatsUseCase: GetCatsUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    private val catsLiveData: MutableLiveData<List<Cat>> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val showErrorLiveData: MutableLiveData<String> = MutableLiveData()

    val catsList get() = catsLiveData
    val showLoading get() = showLoadingLiveData
    val hideLoading get() = hideLoadingLiveData
    val showError get() = showErrorLiveData

    fun loadCats(page: Int) {
        getCats(page)
    }

    private fun getCats(page: Int) {
        execute {
            showLoadingLiveData.postValue(Unit)
            when (val result = getCatsUseCase.execute(page, false)) {
                is Result.Success -> {
                    catsLiveData.postValue(result.data)
                }

                is Result.Error -> {
                    showErrorLiveData.postValue(result.error)
                }
            }
            hideLoadingLiveData.postValue(Unit)
        }
    }



}