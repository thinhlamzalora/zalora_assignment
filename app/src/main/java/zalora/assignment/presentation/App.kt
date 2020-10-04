package zalora.assignment.presentation

import android.app.Application
import androidx.work.*
import zalora.assignment.presentation.di.DaggerInjector
import zalora.assignment.presentation.di.core.CoreComponent
import zalora.assignment.presentation.di.core.DaggerCoreComponent
import zalora.assignment.presentation.di.core.module.AppModule
import zalora.assignment.presentation.di.core.module.DataModule
import zalora.assignment.presentation.di.core.module.DatabaseModule
import zalora.assignment.presentation.di.core.module.NetworkModule
import zalora.assignment.presentation.di.list_cat.ListCatModule
import zalora.assignment.presentation.di.list_cat.ListCatSubComponent
import zalora.assignment.presentation.service.UpdateDatabaseWorker
import zalora.assignment.presentation.service.WorkManagerUtils
import java.util.*
import javax.inject.Inject

class App : Application(), DaggerInjector, Configuration.Provider {
    private lateinit var coreComponent: CoreComponent
    @Inject
    lateinit var configuration: Configuration
    override fun onCreate() {
        super.onCreate()
        val properties = Properties()
        val inputStream = assets.open("config.properties")
        properties.load(inputStream)
        coreComponent = DaggerCoreComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(properties.getProperty("API_URL")))
            .databaseModule(DatabaseModule())
            .dataModule(DataModule())
            .build()
        coreComponent.inject(this)
        val constraints = Constraints.Builder() // The Worker needs Network connectivity
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()
        WorkManagerUtils.with(this).addWork(WorkManagerUtils.UPDATE_DATABASE, constraints, UpdateDatabaseWorker::class.java)
    }

    override fun createListCatComponent(): ListCatSubComponent {
        return coreComponent.plus(ListCatModule())
    }
    override fun getWorkManagerConfiguration(): Configuration = configuration

}