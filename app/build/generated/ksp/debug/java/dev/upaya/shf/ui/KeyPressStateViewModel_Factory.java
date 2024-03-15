package dev.upaya.shf.ui;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.upaya.shf.data.gamepad.GamepadPressStateDataSource;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class KeyPressStateViewModel_Factory implements Factory<KeyPressStateViewModel> {
  private final Provider<GamepadPressStateDataSource> gamepadPressStateDataSourceProvider;

  public KeyPressStateViewModel_Factory(
      Provider<GamepadPressStateDataSource> gamepadPressStateDataSourceProvider) {
    this.gamepadPressStateDataSourceProvider = gamepadPressStateDataSourceProvider;
  }

  @Override
  public KeyPressStateViewModel get() {
    return newInstance(gamepadPressStateDataSourceProvider.get());
  }

  public static KeyPressStateViewModel_Factory create(
      Provider<GamepadPressStateDataSource> gamepadPressStateDataSourceProvider) {
    return new KeyPressStateViewModel_Factory(gamepadPressStateDataSourceProvider);
  }

  public static KeyPressStateViewModel newInstance(
      GamepadPressStateDataSource gamepadPressStateDataSource) {
    return new KeyPressStateViewModel(gamepadPressStateDataSource);
  }
}
