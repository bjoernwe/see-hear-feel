package dev.upaya.shf.inputs

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class InputKeySourceModule {

    @Binds
    abstract fun bindInputKeySource(
        inputKeySource: InputKeySource,
    ): IInputKeySource
}
