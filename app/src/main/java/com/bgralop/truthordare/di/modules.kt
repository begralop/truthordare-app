package com.bgralop.truthordare.di

import com.bgralop.truthordare.data.local.MemoryCache
import com.bgralop.truthordare.data.remote.ApiClient
import com.bgralop.truthordare.data.remote.TruthOrDareService
import com.bgralop.truthordare.data.truthOrDareQuestions.TruthOrDareDataImpl
import com.bgralop.truthordare.data.truthOrDareQuestions.local.TruthOrDareLocalImpl
import com.bgralop.truthordare.data.truthOrDareQuestions.remote.TruthOrDareRemoteImpl
import com.bgralop.truthordare.domain.TruthOrDareRepository
import com.bgralop.truthordare.domain.usecase.GetDareQuestionsUseCase
import com.bgralop.truthordare.domain.usecase.GetNeverHaveIEverQuestionUseCase
import com.bgralop.truthordare.domain.usecase.GetTruthQuestionUseCase
import com.bgralop.truthordare.presentation.ViewModel.NeverHaveIEverViewModel
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    single { MemoryCache() }
    single<TruthOrDareService> { ApiClient.retrofit.create(TruthOrDareService::class.java) }
}

val truthOrDareQuestionsModule = module {
    factory { TruthOrDareLocalImpl(get()) }
    factory { TruthOrDareRemoteImpl(get()) }
    factory<TruthOrDareRepository> { TruthOrDareDataImpl(get(), get())  }

    factory { GetTruthQuestionUseCase(get()) }
    factory { GetDareQuestionsUseCase(get()) }
    factory { GetNeverHaveIEverQuestionUseCase(get()) }

    viewModel { TruthOrDareViewModel(get(), get()) }
    viewModel { NeverHaveIEverViewModel(get()) }

}