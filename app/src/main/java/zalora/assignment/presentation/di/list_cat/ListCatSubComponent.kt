package zalora.assignment.presentation.di.list_cat

import dagger.Subcomponent
import zalora.assignment.presentation.list_cat.ListCatActivity

@Subcomponent(modules = [ListCatModule::class])
interface ListCatSubComponent {
    fun inject(listCatActivity: ListCatActivity)
}