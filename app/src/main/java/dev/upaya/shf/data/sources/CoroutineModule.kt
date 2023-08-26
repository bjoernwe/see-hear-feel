package dev.upaya.shf.data.sources

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @ApplicationCoroutineScope
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationCoroutineScope
