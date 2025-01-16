package br.thiago.githubapp

import android.app.Application
import br.thiago.githubapp.core.di.useCases
import br.thiago.githubapp.core.di.viewModels
import br.thiago.githubapp.pullrequest_feature.di.remoteModulePullRequest
import br.thiago.githubapp.repositories_feature.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = applicationContext)
            modules(
                viewModels,
                useCases,
                remoteModule,
                remoteModulePullRequest,
            )
        }
    }
}
