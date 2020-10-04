package zalora.assignment.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import zalora.assignment.presentation.utils.DispatchersProvider
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val dispatchers: DispatchersProvider
) : ViewModel(), CoroutineScope {
    val supJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = dispatchers.getMain() + supJob

    fun execute(job: suspend () -> Unit) = launch {
        withContext(dispatchers.getIO()) { job.invoke() }
    }

    override fun onCleared() {
        super.onCleared()
        supJob.cancel()
    }
}