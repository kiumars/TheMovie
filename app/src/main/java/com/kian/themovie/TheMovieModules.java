package com.kian.themovie;

import android.app.Application;

import com.kian.themovie.data.DataModule;
import com.kian.themovie.ui.UiModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kiumars on 15-04-19.
 */
@Module(
        includes = {
                UiModule.class,
                DataModule.class
        },
        injects = {TheMovieApp.class},
        library = true
)
public class TheMovieModules {
    private final TheMovieApp app;

    public TheMovieModules(TheMovieApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}
