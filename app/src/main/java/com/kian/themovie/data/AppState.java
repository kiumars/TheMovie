package com.kian.themovie.data;

import com.kian.themovie.data.api.model.Search;
import com.kian.themovie.data.api.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kiumars on 15-04-19.
 */
@Singleton
public class AppState {
    private List<Search> items;
    private SearchResponse itemDetail;
    @Inject
    public AppState() {
        items = new ArrayList<>();
        itemDetail = null;
    }

    public List<Search> getItems() {
        return items;
    }

    public void setItems(List<Search> items) {
        this.items = items;
    }

    public SearchResponse getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(SearchResponse itemDetail) {
        this.itemDetail = itemDetail;
    }
}
