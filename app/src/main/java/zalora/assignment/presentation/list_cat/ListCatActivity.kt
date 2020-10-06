package zalora.assignment.presentation.list_cat
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_cat.*
import zalora.assignment.R
import zalora.assignment.data.utils.Constant
import zalora.assignment.data.utils.Constant.PICASO_TAG
import zalora.assignment.presentation.base.BaseActivity
import zalora.assignment.presentation.utils.EndlessRecyclerViewScrollListener
import zalora.assignment.presentation.utils.SpacesItemDecoration
import javax.inject.Inject

class ListCatActivity : BaseActivity<ListCatViewModel>(R.layout.activity_list_cat) {
    @Inject
    lateinit var listCatViewModelFactory: ListCatViewModelFactory
    private val catAdapter: CatAdapter by lazy {
        CatAdapter(resources.getDimension(R.dimen.item_space).toInt())
    }
    override fun createViewModel(): ListCatViewModel {
        return ViewModelProvider(this, listCatViewModelFactory).get(ListCatViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        daggerInjector.createListCatComponent().inject(this)
        super.onCreate(savedInstanceState)
        initView()
        observeViewModel()
        viewModel.loadCats(Constant.START_PAGE)
    }
    private fun observeViewModel() {
        viewModel.catsList.observe(this, Observer {
            catAdapter.addDataSource(it)
        })
        viewModel.showLoadingCenter.observe(this, Observer {
            progressBar.visibility = View.VISIBLE
        })
        viewModel.showLoadingBottom.observe(this, Observer {
            progressBarBottom.visibility = View.VISIBLE

        })
        viewModel.hideLoadingCenter.observe(this, Observer {
            progressBar.visibility = View.GONE
        })
        viewModel.hideLoadingBottom.observe(this, Observer {
            progressBarBottom.visibility = View.GONE

        })
        viewModel.showError.observe(this, Observer {
            Snackbar
                .make(listCat, it, Snackbar.LENGTH_INDEFINITE)
                .setAction(resources.getString(R.string.ok)) {
                }.show()
        })
    }

    private fun initView() {

        listCat.apply {
            itemAnimator = null;
            setHasFixedSize(true);
            setItemViewCacheSize(Constant.PAGE_COUNT);
            adapter = catAdapter
            addItemDecoration(SpacesItemDecoration(resources.getDimension(R.dimen.item_space).toInt()))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        Picasso.get().resumeTag(PICASO_TAG)
                    }else
                    {
                        Picasso.get().pauseTag(PICASO_TAG)
                    }
                }
            })
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as StaggeredGridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int) {
                    viewModel.loadCats(page)
                }
            })
        }

    }
}