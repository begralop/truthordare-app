package com.bgralop.truthordare

import android.app.Application
import com.bgralop.truthordare.di.baseModule
import com.bgralop.truthordare.di.truthOrDareQuestionsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TruthOrDareApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TruthOrDareApplication)
            modules(listOf(baseModule, truthOrDareQuestionsModule)).allowOverride(true)
        }
    }
}