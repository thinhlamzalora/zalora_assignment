package zalora.assignment.presentation.service

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import zalora.assignment.domain.usecase.GetCatsUseCase

class UpdateDatabaseWorkerFactory (private val getCatsUseCase: GetCatsUseCase) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return UpdateDatabaseWorker(appContext, workerParameters, getCatsUseCase)
    }
}