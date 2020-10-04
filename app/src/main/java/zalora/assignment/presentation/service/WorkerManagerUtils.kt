package zalora.assignment.presentation.service

import android.content.Context
import androidx.work.*
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class WorkManagerUtils {
    companion object {
        const val UPDATE_DATABASE = "UPDATE_DATABASE"
        private var singleton: WorkManagerUtils? = null
        private lateinit var workManager: WorkManager
        fun with(context: Context) : WorkManagerUtils {
            if (null == singleton)
                singleton = Builder(context).build()
            return singleton as WorkManagerUtils
        }

    }

    constructor(context: Context) {
        workManager = WorkManager.getInstance(context)
    }

    fun addWork(tag: String, constraints: Constraints, worker: Class<out ListenableWorker?>){
        addTask(tag, constraints, worker)
    }

    private  fun addTask(tag: String, constraints: Constraints, worker: Class<out ListenableWorker?>) {
        if(!isWorkScheduled(tag)){
            val request = PeriodicWorkRequest.Builder(
                worker,
                15,
                TimeUnit.MINUTES
            ).setConstraints(constraints)
                .build()
            workManager.enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.KEEP, request)
        }
    }

    private class Builder(val context: Context) {

        fun build() : WorkManagerUtils {
            return WorkManagerUtils(context)
        }
    }

    private fun isWorkScheduled(tag: String): Boolean {
        val statuses: ListenableFuture<List<WorkInfo>> = workManager.getWorkInfosByTag(tag)
        return try {
            var running = false
            val workInfoList: List<WorkInfo> = statuses.get()
            for (workInfo in workInfoList) {
                val state = workInfo.state
                running = (state == WorkInfo.State.RUNNING || state == WorkInfo.State.ENQUEUED)
            }
            running
        } catch (e: ExecutionException) {
            e.printStackTrace()
            false
        } catch (e: InterruptedException) {
            e.printStackTrace()
            false
        }
    }
}