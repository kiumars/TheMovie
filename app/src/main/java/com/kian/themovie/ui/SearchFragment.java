package com.kian.themovie.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.kian.themovie.R;
import com.kian.themovie.TheMovieApp;
import com.kian.themovie.adapters.SearchAdapter;
import com.kian.themovie.data.AppState;
import com.kian.themovie.data.api.MovieService;
import com.kian.themovie.data.api.model.SearchResponse;
import com.kian.themovie.event.MovieDetailUpdateEvent;
import com.kian.themovie.event.MovieListUpdateEvent;
import com.kian.themovie.ui.customviews.ClearableEditText;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kiumars on 15-04-19.
 */
public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();
    @Inject
    MovieService service;
    @Inject
    Bus eventBus;
    @Inject
    AppState appState;

    SearchAdapter adapter;

    @InjectView(R.id.tv_search_text)
    ClearableEditText etSearch;
    @InjectView(R.id.lv_search_result)
    ListView searchListView;

    @OnItemClick(R.id.lv_search_result)
    void onItemSelected(AdapterView<?> parent, View view,
                        int position, long id) {
        Timber.d("Item number %d selected", position);
        Map<String, String> params = new HashMap<>();
        params.put("i", adapter.getItem(position).getImdbID());
        params.put("r", "json");
        Timber.d("The request id is: %s", adapter.getItem(position).getImdbID());
        service.searchMovies(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchResponse>() {
                    @Override
                    public void call(SearchResponse searchResponse) {
                        Timber.d("The Poster fetched");
                        appState.setItemDetail(searchResponse);
                        eventBus.post(new MovieDetailUpdateEvent());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Timber.d("Poster fetch failed!");
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.inject(this, view);
        searchListView.setAdapter(getSearchAdapter());
        return view;
    }

    private ListAdapter getSearchAdapter() {
        adapter = new SearchAdapter(getActivity(), R.layout.row_movie, appState.getItems());
        return adapter;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Timber.tag(TAG);
                Timber.d("Search term: %s", s);
                if (s != null && s.length() > 1) {
                    Map<String, String> params = new HashMap<>();
                    params.put("s", s.toString());
                    params.put("r", "json");
                    service.searchMovies(params)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<SearchResponse>() {
                                @Override
                                public void call(SearchResponse searchResponse) {
                                    Timber.tag(TAG);
                                    Timber.d("Movies Received: %d", searchResponse.getSearch().size());
                                    appState.setItems(searchResponse.getSearch());
                                    eventBus.post(new MovieListUpdateEvent(searchResponse.getSearch()));
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Timber.tag(TAG);
                                    Timber.d("Something went wrong: %s", throwable);
                                }
                            });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TheMovieApp.inject(this);
    }

    @Subscribe
    public void searchResultUpdate(MovieListUpdateEvent event) {
        adapter = new SearchAdapter(getActivity(), R.layout.row_movie, event.getSearchResult());
        searchListView.setAdapter(adapter);
    }
}
