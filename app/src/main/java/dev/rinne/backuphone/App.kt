package dev.rinne.backuphone

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        var context: Application? = null
        fun getContext(): Context {
            return context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
