package zalora.assignment.presentation.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import zalora.assignment.data.utils.Constant
import zalora.assignment.domain.usecase.GetCatsUseCase

class UpdateDatabaseWorker (context: Context, params: WorkerParameters, private val getCatsUseCase: GetCatsUseCase) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = coroutineScope {

        val jobs = (1 until Constant.MAX_PAGE + 1).map {
            async {
                getCatsUseCase.execute(it, true)
            }
        }
        jobs.awaitAll()
        Result.success()
    }
}