package com.kian.themovie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kian.themovie.R;
import com.kian.themovie.data.api.model.Search;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kiumars on 15-04-19.
 */
public class SearchAdapter extends ArrayAdapter<Search> {
    private List<Search> searchResult;
    private final Context context;
    private final LayoutInflater inflater;

    public SearchAdapter(Context context, int resource, List<Search> objects) {
        super(context, resource, objects);
        this.searchResult = objects;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.row_movie, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.movieName.setText(searchResult.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_movie_name)
        TextView movieName;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
