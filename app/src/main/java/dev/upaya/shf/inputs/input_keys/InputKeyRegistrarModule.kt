package dev.upaya.shf.inputs.input_keys

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InputKeyRegistrarModule {

    @Provides
    @Singleton
    @ForegroundKeySource
    fun providesForegroundKeySource(
        globalInputRegistrarSwitch: GlobalInputRegistrarSwitch,
    ): IInputKeyRegistrar {
        return InputKeyRegistrar(
            globalInputRegistrarSwitch = globalInputRegistrarSwitch,
        )
    }

    @Provides
    @Singleton
    @BackgroundKeySource
    fun providesBackgroundKeySource(
        globalInputRegistrarSwitch: GlobalInputRegistrarSwitch,
    ): IInputKeyRegistrar {
        return InputKeyRegistrar(
            globalInputRegistrarSwitch = globalInputRegistrarSwitch,
        )
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ForegroundKeySource

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BackgroundKeySource
