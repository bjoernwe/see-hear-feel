package dev.upaya.shf.inputs.input_keys

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.upaya.shf.inputs.permissions.accessibility.AccessibilityPermissionSource
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
        accessibilityPermissionSource: AccessibilityPermissionSource,
        @DefaultDispatcher dispatcher: CoroutineDispatcher,
    ): IInputKeySource {
        return GlobalInputKeySource(
            foregroundInputKeySource = foregroundInputKeySource,
            backgroundInputKeySource = backgroundInputKeySource,
            accessibilityPermissionSource = accessibilityPermissionSource,
            dispatcher = dispatcher,
        )
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GlobalKeySource
