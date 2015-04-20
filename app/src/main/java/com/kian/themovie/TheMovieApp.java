package com.kian.themovie;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

/**
 * Created by kiumars on 15-04-19.
 */
public class TheMovieApp extends Application {
    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }

        buildObjectGraphAndInject();
    }

    private void buildObjectGraphAndInject() {
        if (objectGraph == null) {
            objectGraph = ObjectGraph.create(Modules.list(this));
        }
        objectGraph.inject(this);
    }

    public static void inject(Object o) {
        objectGraph.inject(o);
    }
}
