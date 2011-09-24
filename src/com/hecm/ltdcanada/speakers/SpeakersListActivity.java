package com.hecm.ltdcanada.speakers;

import java.io.IOException;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hecm.ltdcanada.Global;
import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.adapters.SpeakerListViewAdapter;
import com.hecm.ltdcanada.adapters.SpeakerView;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Speaker;
import com.hecm.ltdcanada.views.AddItemView;

public class SpeakersListActivity extends ListActivity implements OnItemClickListener {
	private List<Speaker> speakers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			this.speakers = Global.getClient().getAllSpeakers();
			
			AddItemView addItem = AddItemView.inflateNew(this);
			addItem.setText(R.string.add_speaker);
			
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(this);
			lv.addHeaderView(addItem);
			
			this.setListAdapter(new SpeakerListViewAdapter(this, this.speakers));
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Intent intent = null;
		if(view instanceof SpeakerView) {
			Speaker speaker = ((SpeakerView) view).getSpeaker();
			
			intent = new Intent(view.getContext(), SpeakersShowActivity.class);
			intent.putExtra("speaker", speaker);
		} else if(view instanceof AddItemView) {
			intent = new Intent(view.getContext(), SpeakersCreateActivity.class);
		}
		
		if(intent != null) {
			this.startActivity(intent);
		}
		
	}
}
