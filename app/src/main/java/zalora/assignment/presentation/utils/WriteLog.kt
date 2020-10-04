package zalora.assignment.presentation.utils

import android.util.Log
import zalora.assignment.BuildConfig

object WriteLog {
    private val debug = BuildConfig.DEBUG
    fun e(tag: String, msg: String){
        if (debug)
            Log.e(tag, msg)
    }
    fun d(tag: String, msg: String){
        if (debug)
            Log.d(tag, msg)
    }
    fun i(tag: String, msg: String){
        if (debug)
            Log.i(tag, msg)
    }
    fun v(tag: String, msg: String){
        if (debug)
            Log.v(tag, msg)
    }
}