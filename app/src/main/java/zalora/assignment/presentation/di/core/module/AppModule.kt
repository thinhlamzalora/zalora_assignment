package zalora.assignment.presentation.di.core.module

import android.content.Context
import dagger.Module
import dagger.Provides
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
}