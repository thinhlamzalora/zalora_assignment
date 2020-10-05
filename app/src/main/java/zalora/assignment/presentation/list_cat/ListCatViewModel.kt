package zalora.assignment.presentation.list_cat

import androidx.lifecycle.MutableLiveData
import zalora.assignment.data.utils.Constant
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
    private val showLoadingCenterLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val hideLoadingCenterLiveData: MutableLiveData<Unit> = MutableLiveData()

    private val showLoadingBottomLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val hideLoadingBottomLiveData: MutableLiveData<Unit> = MutableLiveData()

    private val showErrorLiveData: MutableLiveData<String> = MutableLiveData()

    val catsList get() = catsLiveData
    val showLoadingCenter get() = showLoadingCenterLiveData
    val hideLoadingCenter get() = hideLoadingCenterLiveData
    val showLoadingBottom get() = showLoadingBottomLiveData
    val hideLoadingBottom get() = hideLoadingBottomLiveData
    val showError get() = showErrorLiveData

    fun loadCats(page: Int) {
        getCats(page)
    }


    private fun getCats(page: Int) {
        execute {
           loadingProcess(true, page)
            when (val result = getCatsUseCase.execute(page, false)) {
                is Result.Success -> {
                    loadingProcess(false, page)
                    catsLiveData.postValue(result.data)
                }
                is Result.Error -> {
                    loadingProcess(false, page)
                    showErrorLiveData.postValue(result.error)
                }
            }
        }
    }
    private fun loadingProcess(loading: Boolean, page: Int){
        if (loading) {
            if (page == Constant.START_PAGE)
                showLoadingCenterLiveData.postValue(Unit)
            else
                showLoadingBottomLiveData.postValue(Unit)
        }else{
            if (page == Constant.START_PAGE)
                hideLoadingCenterLiveData.postValue(Unit)
            else
                hideLoadingBottomLiveData.postValue(Unit)
        }
    }



}