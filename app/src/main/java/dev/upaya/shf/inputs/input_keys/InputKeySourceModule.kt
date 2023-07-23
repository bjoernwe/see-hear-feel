package dev.upaya.shf.inputs.input_keys

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.upaya.shf.background.settings.AccessibilitySettingSource
import dev.upaya.shf.inputs.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InputKeySourceModule {

    @Provides
    @Singleton
    @GlobalKeySource
    fun providesGlobalKeySource(
        @ForegroundKeySource foregroundInputKeySource: IInputKeyRegistrar,
        @BackgroundKeySource backgroundInputKeySource: IInputKeyRegistrar,
        accessibilitySettingSource: AccessibilitySettingSource,
        @DefaultDispatcher dispatcher: CoroutineDispatcher,
    ): IInputKeySource {
        return GlobalInputKeySource(
            foregroundInputKeySource = foregroundInputKeySource,
            backgroundInputKeySource = backgroundInputKeySource,
            accessibilitySettingSource = accessibilitySettingSource,
            dispatcher = dispatcher,
        )
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GlobalKeySource
