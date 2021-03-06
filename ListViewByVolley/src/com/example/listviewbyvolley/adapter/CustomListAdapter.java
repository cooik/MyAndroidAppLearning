package com.example.listviewbyvolley.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.listviewbyvolley.R;
import com.example.listviewbyvolley.app.AppController;
import com.example.listviewbyvolley.model.Movie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.movieItems = movieItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movieItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return movieItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);
		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

		Movie m = movieItems.get(position);

		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		title.setText(m.getTitle());
		rating.setText("Rating:" + String.valueOf(m.getRating()));

		String genreStr = "";

		for (String str : m.getGenre()) {
			genreStr += str + ",";
		}

		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
		genre.setText(genreStr);

		year.setText(String.valueOf(m.getYear()));

		return convertView;
	}

}
