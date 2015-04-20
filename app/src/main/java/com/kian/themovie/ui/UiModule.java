package com.kian.themovie.ui;

import dagger.Module;

/**
 * Created by kiumars on 15-04-19.
 */
@Module(injects = {
        MainActivity.class,
        SearchFragment.class,
        PosterFragment.class
        },
        complete = false,
        library = true)
public class UiModule {
}
