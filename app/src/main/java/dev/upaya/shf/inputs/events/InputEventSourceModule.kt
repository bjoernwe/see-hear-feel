package dev.upaya.shf.inputs.events

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
abstract class InputEventSourceModule {

    @Binds
    @Singleton
    abstract fun bindInputEventSource(
        inputEventSource: InputEventSource,
    ): IInputEventSource

}
