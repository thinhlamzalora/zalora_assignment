package zalora.assignment.presentation

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import zalora.assignment.presentation.di.DaggerInjector
import zalora.assignment.presentation.di.core.CoreComponent
import zalora.assignment.presentation.di.core.DaggerCoreComponent
import zalora.assignment.presentation.di.core.module.AppModule
import zalora.assignment.presentation.di.core.module.DataModule
import zalora.assignment.presentation.di.core.module.DatabaseModule
import zalora.assignment.presentation.di.core.module.NetworkModule
import zalora.assignment.presentation.di.list_cat.ListCatModule
import zalora.assignment.presentation.di.list_cat.ListCatSubComponent
import java.util.*

class App : Application(), DaggerInjector {
    private lateinit var coreComponent: CoreComponent

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
    }

    override fun createListCatComponent(): ListCatSubComponent {
        return coreComponent.plus(ListCatModule())
    }
}