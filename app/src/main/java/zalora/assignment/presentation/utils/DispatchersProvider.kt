package zalora.assignment.presentation.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider{
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}