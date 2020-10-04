package zalora.assignment.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import zalora.assignment.presentation.di.DaggerInjector

abstract class BaseActivity<VM : BaseViewModel>(
    @LayoutRes private val resId: Int
) : AppCompatActivity() {

    protected val daggerInjector: DaggerInjector by lazy { application as DaggerInjector }

    protected val viewModel: VM by lazy {
        createViewModel()
    }

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resId)

    }

}