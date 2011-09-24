package com.hecm.ltdcanada.adapters;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.hecm.ltdcanada.Global;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Speaker;

public class SpeakerSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
	private Context context;
	private List<Speaker> speakers;
	
	public SpeakerSpinnerAdapter(Context context) throws ClientException, IOException {
		this.context = context;
		this.speakers = Global.getClient().getAllSpeakers();
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
		TextView result = null;
		if(convertView == null) {
			result = new TextView(this.context);
			result.setTextColor(Color.BLACK);
		} else {
			result = (TextView) convertView;
		}
		
		result.setText(this.speakers.get(position).getString("Name"));
		
		return result;
	}

}
