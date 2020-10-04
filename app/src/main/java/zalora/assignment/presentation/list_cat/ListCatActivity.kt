package zalora.assignment.presentation.list_cat

import androidx.lifecycle.ViewModelProvider
import zalora.assignment.R
import zalora.assignment.presentation.base.BaseActivity
import javax.inject.Inject

class ListCatActivity : BaseActivity<ListCatViewModel>(R.layout.activity_list_cat) {
    @Inject
    lateinit var listCatViewModelFactory: ListCatViewModelFactory

    override fun createViewModel(): ListCatViewModel {
        return ViewModelProvider(this, listCatViewModelFactory).get(ListCatViewModel::class.java)
    }

}