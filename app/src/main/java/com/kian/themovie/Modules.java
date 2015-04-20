package com.kian.themovie;

/**
 * Created by kiumars on 15-04-19.
 */
final class Modules {
    static Object[] list(TheMovieApp app) {
        return new Object[]{
                new TheMovieModules(app)
        };
    }


    private Modules() {
        // No instances.
    }
}
