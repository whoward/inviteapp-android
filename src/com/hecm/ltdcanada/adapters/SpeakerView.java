package com.hecm.ltdcanada.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.models.Speaker;

public class SpeakerView extends LinearLayout {
	private Speaker model;
	
	public static SpeakerView inflateNew(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return (SpeakerView) inflater.inflate(R.layout.speaker_list_item, null);
	}
	
	public SpeakerView(Context context) {
		super(context);
	}

	public SpeakerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Speaker getSpeaker() {
		return this.model;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.model = speaker;
		this.updateInterface();
	}

	private void updateInterface() {
		TextView nameView = (TextView) this.findViewById(R.id.name);
		
		nameView.setText(this.model.getString("Name"));
	}
}
