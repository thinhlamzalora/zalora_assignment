package zalora.assignment.presentation.di.core.module

import android.content.Context
import androidx.work.Configuration
import dagger.Module
import dagger.Provides
import zalora.assignment.domain.usecase.GetCatsUseCase
import zalora.assignment.presentation.service.UpdateDatabaseWorkerFactory
import zalora.assignment.presentation.utils.DispatchersProvider
import zalora.assignment.presentation.utils.DispatchersProviderImpl
import javax.inject.Singleton

@Module
class AppModule constructor(context: Context) {
    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl()
    }
    @Provides
    fun provideUpdateDatabaseWorkerFactory(getCatsUseCase: GetCatsUseCase): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(UpdateDatabaseWorkerFactory(getCatsUseCase))
            .build()
    }
}