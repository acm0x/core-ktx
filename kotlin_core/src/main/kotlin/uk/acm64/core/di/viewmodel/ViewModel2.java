package uk.acm64.core.di.viewmodel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

@MapKey
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModel2 {
    Class<? extends ViewModel> value();
}
