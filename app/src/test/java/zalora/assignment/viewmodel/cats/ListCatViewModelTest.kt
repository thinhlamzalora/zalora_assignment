package zalora.assignment.viewmodel.cats

import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import zalora.assignment.domain.model.Cat
import zalora.assignment.domain.usecase.GetCatsUseCase
import zalora.assignment.presentation.list_cat.ListCatViewModel
import zalora.assignment.base.BaseViewModelTest
import zalora.assignment.utils.runBlockingTest
import zalora.assignment.domain.utils.*
@RunWith(MockitoJUnitRunner::class)
class ListCatViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var getCatsUseCase: GetCatsUseCase

    private lateinit var viewModel: ListCatViewModel

    @Before
    fun setUp() {
        viewModel = ListCatViewModel(getCatsUseCase, coroutineRule.testDispatcherProvider)
    }

    @Test
    fun loadCats_onSuccess_hideLoadingAndShowCats_withPage1() {
        coroutineRule.runBlockingTest {
            val catsObs: Observer<List<Cat>> = mock()
            val showLoadingObs: Observer<Unit> = mock()
            val hideLoadingObs: Observer<Unit> = mock()
            viewModel.catsList.observeForever(catsObs)
            viewModel.showLoadingCenter.observeForever(showLoadingObs)
            viewModel.hideLoadingCenter.observeForever(hideLoadingObs)
            Mockito.`when`(getCatsUseCase.execute(1, false)).thenReturn(Result.Success(mock()))
            viewModel.loadCats(1)
            val inOrder = Mockito.inOrder(showLoadingObs, hideLoadingObs, catsObs)
            inOrder.verify(showLoadingObs).onChanged(Unit)
            inOrder.verify(hideLoadingObs).onChanged(Unit)
            inOrder.verify(catsObs).onChanged(any())
        }
    }

    @Test
    fun loadCats_onSuccess_hideLoadingAndShowCats_withOtherPages() {
        coroutineRule.runBlockingTest {
            val catsObs: Observer<List<Cat>> = mock()
            val showLoadingObs: Observer<Unit> = mock()
            val hideLoadingObs: Observer<Unit> = mock()
            viewModel.catsList.observeForever(catsObs)
            viewModel.showLoadingBottom.observeForever(showLoadingObs)
            viewModel.hideLoadingBottom.observeForever(hideLoadingObs)
            Mockito.`when`(getCatsUseCase.execute(2, false)).thenReturn(Result.Success(mock()))
            viewModel.loadCats(2)
            val inOrder = Mockito.inOrder(showLoadingObs, hideLoadingObs, catsObs)
            inOrder.verify(showLoadingObs).onChanged(Unit)
            inOrder.verify(hideLoadingObs).onChanged(Unit)
            inOrder.verify(catsObs).onChanged(any())
        }
    }

    @Test
    fun loadCats_onFailure_hideLoadingAndShowErrorMessage_withPage1() {
        coroutineRule.runBlockingTest {
            val catsObs: Observer<List<Cat>> = mock()
            val errorObs: Observer<String> = mock()
            val showLoadingObs: Observer<Unit> = mock()
            val hideLoadingObs: Observer<Unit> = mock()
            viewModel.catsList.observeForever(catsObs)
            viewModel.showLoadingCenter.observeForever(showLoadingObs)
            viewModel.showError.observeForever(errorObs)
            viewModel.hideLoadingCenter.observeForever(hideLoadingObs)
            Mockito.`when`(getCatsUseCase.execute(1, false)).thenReturn(Result.Error("error"))
            viewModel.loadCats(1)
            val inOrder = Mockito.inOrder(showLoadingObs, errorObs, hideLoadingObs)
            inOrder.verify(showLoadingObs).onChanged(Unit)
            inOrder.verify(hideLoadingObs).onChanged(Unit)
            inOrder.verify(errorObs).onChanged(any())
            Mockito.verifyZeroInteractions(catsObs)
        }
    }

    @Test
    fun loadCats_onFailure_hideLoadingAndShowErrorMessage_withOtherPages() {
        coroutineRule.runBlockingTest {
            val catsObs: Observer<List<Cat>> = mock()
            val errorObs: Observer<String> = mock()
            val showLoadingObs: Observer<Unit> = mock()
            val hideLoadingObs: Observer<Unit> = mock()
            viewModel.catsList.observeForever(catsObs)
            viewModel.showLoadingBottom.observeForever(showLoadingObs)
            viewModel.showError.observeForever(errorObs)
            viewModel.hideLoadingBottom.observeForever(hideLoadingObs)
            Mockito.`when`(getCatsUseCase.execute(2, false)).thenReturn(Result.Error("error"))
            viewModel.loadCats(2)
            val inOrder = Mockito.inOrder(showLoadingObs, errorObs, hideLoadingObs)
            inOrder.verify(showLoadingObs).onChanged(Unit)
            inOrder.verify(hideLoadingObs).onChanged(Unit)
            inOrder.verify(errorObs).onChanged(any())
            Mockito.verifyZeroInteractions(catsObs)
        }
    }

    @Test
    fun loadCats_onLoading_showLoadingView_withPage1() {
        coroutineRule.runBlockingTest {
            val showLoadingObs: Observer<Unit> = mock()
            viewModel.showLoadingCenter.observeForever(showLoadingObs)
            viewModel.loadCats(1)
            Mockito.verify(showLoadingObs).onChanged(Unit)
        }
    }

    @Test
    fun loadCats_onLoading_showLoadingView_withOtherPages() {
        coroutineRule.runBlockingTest {
            val showLoadingObs: Observer<Unit> = mock()
            viewModel.showLoadingBottom.observeForever(showLoadingObs)
            viewModel.loadCats(2)
            Mockito.verify(showLoadingObs).onChanged(Unit)
        }
    }


}