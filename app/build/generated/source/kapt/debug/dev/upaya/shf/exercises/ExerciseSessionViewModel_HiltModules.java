package dev.upaya.shf.exercises;

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
import dev.upaya.shf.ui.exercises.SessionViewModel;

import java.lang.String;

@OriginatingElement(
    topLevelClass = SessionViewModel.class
)
public final class ExerciseSessionViewModel_HiltModules {
  private ExerciseSessionViewModel_HiltModules() {
  }

  @Module
  @InstallIn(ViewModelComponent.class)
  public abstract static class BindsModule {
    private BindsModule() {
    }

    @Binds
    @IntoMap
    @StringKey("dev.upaya.shf.ui.exercises.ExerciseSessionViewModel")
    @HiltViewModelMap
    public abstract ViewModel binds(SessionViewModel vm);
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
      return "dev.upaya.shf.ui.exercises.ExerciseSessionViewModel";
    }
  }
}
