package com.hecm.ltdcanada.speakers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.models.Speaker;

public class SpeakersShowActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_show);
        
        Bundle extras = getIntent().getExtras();
        
        this.setSpeaker((Speaker) extras.getSerializable("speaker"));
    }
    
    public void setSpeaker(Speaker speaker) {
    	getTextView(R.id.name).setText(speaker.getString("Name"));
    	getTextView(R.id.base_url).setText(speaker.getString("BaseURL"));
    }
    
    private TextView getTextView(int res) {
    	return (TextView) this.findViewById(res);
    }
}
