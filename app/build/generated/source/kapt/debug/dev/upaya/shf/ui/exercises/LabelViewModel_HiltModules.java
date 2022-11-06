package dev.upaya.shf.ui.exercises;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap;
import dagger.hilt.codegen.OriginatingElement;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;
import dev.upaya.shf.ui.input.LabelViewModel;

import java.lang.String;

@OriginatingElement(
    topLevelClass = LabelViewModel.class
)
public final class LabelViewModel_HiltModules {
  private LabelViewModel_HiltModules() {
  }

  @Module
  @InstallIn(ViewModelComponent.class)
  public abstract static class BindsModule {
    private BindsModule() {
    }

    @Binds
    @IntoMap
    @StringKey("dev.upaya.shf.ui.input.LabelViewModel")
    @HiltViewModelMap
    public abstract ViewModel binds(LabelViewModel vm);
  }

  @Module
  @InstallIn(ActivityRetainedComponent.class)
  public static final class KeyModule {
    private KeyModule() {
    }

    @Provides
    @IntoSet
    @HiltViewModelMap.KeySet
    public static String provide() {
      return "dev.upaya.shf.ui.input.LabelViewModel";
    }
  }
}
