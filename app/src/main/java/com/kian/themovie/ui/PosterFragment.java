package com.kian.themovie.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kian.themovie.R;
import com.kian.themovie.TheMovieApp;
import com.kian.themovie.data.AppState;
import com.kian.themovie.data.api.MovieService;
import com.kian.themovie.event.MovieDetailUpdateEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

/**
 * Created by kiumars on 15-04-19.
 */
public class PosterFragment extends Fragment {
    private static final String TAG = PosterFragment.class.getSimpleName();
    @Inject
    MovieService service;
    @Inject
    Bus eventBus;
    @Inject
    AppState appState;

    @InjectView(R.id.iv_poster_image)
    ImageView ivPosterImage;
    @InjectView(R.id.tv_poster_title)
    TextView tvImageTitle;
    @InjectView(R.id.tv_poster_description)
    TextView tvImageDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        ButterKnife.inject(this, view);
        if (appState.getItemDetail() != null)
            updateView(new MovieDetailUpdateEvent());
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TheMovieApp.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    @Subscribe
    public void updateView(MovieDetailUpdateEvent event) {
        Timber.tag(TAG);
        Timber.d("Poster url: (%s) %s", appState.getItemDetail().getTitle(), appState.getItemDetail().getPoster());
        Picasso.with(getActivity()).load(appState.getItemDetail().getPoster()).into(ivPosterImage);
        if (appState.getItemDetail().getTitle() != null) tvImageTitle.setText(appState.getItemDetail().getTitle());
        if (appState.getItemDetail().getGenre() != null) {
            String description = String.format("(" + appState.getItemDetail().getGenre() + ", " + appState.getItemDetail().getReleased() + ")\n" + appState.getItemDetail().getPlot());
            tvImageDescription.setText(description);
        }
    }
}

