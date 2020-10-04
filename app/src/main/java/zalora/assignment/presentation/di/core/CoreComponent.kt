package zalora.assignment.presentation.di.core

import dagger.Component
import zalora.assignment.presentation.App
import zalora.assignment.presentation.di.core.module.AppModule
import zalora.assignment.presentation.di.core.module.DataModule
import zalora.assignment.presentation.di.core.module.DatabaseModule
import zalora.assignment.presentation.di.core.module.NetworkModule
import zalora.assignment.presentation.di.list_cat.ListCatModule
import zalora.assignment.presentation.di.list_cat.ListCatSubComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    DataModule::class
])
interface CoreComponent {
    fun plus(listCatModule: ListCatModule): ListCatSubComponent
    fun inject(app: App)

}