package com.hecm.ltdcanada.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.hecm.ltdcanada.httpclient.models.Speaker;

public class SpeakerListViewAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private List<Speaker> speakers;
	
	public SpeakerListViewAdapter(Context context, List<Speaker> speakers) {
		this.context = context;
		this.speakers = speakers;
	}

	@Override
	public int getCount() {
		return this.speakers.size();
	}

	@Override
	public Object getItem(int position) {
		return this.speakers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.speakers.get(position).getInteger("ID");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SpeakerView view;
		
		if(convertView == null) {
			view = SpeakerView.inflateNew(context);
		} else {
			view = (SpeakerView) convertView;
		}
		
		view.setSpeaker(this.speakers.get(position));
		
		return view;
	}

}
