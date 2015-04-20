package com.kian.themovie.event;

import com.kian.themovie.data.api.model.Search;

import java.util.List;

/**
 * Created by kiumars on 15-04-19.
 */
public class MovieListUpdateEvent {
    List<Search> searchResult;

    public MovieListUpdateEvent(List<Search> search) {
        this.searchResult = search;
    }

    public List<Search> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<Search> searchResult) {
        this.searchResult = searchResult;
    }
}
