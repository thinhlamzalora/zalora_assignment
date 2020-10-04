package zalora.assignment.presentation.di

import zalora.assignment.presentation.di.list_cat.ListCatSubComponent

interface DaggerInjector {
    fun createListCatComponent(): ListCatSubComponent

}