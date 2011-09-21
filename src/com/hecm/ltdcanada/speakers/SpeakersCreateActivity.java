package com.hecm.ltdcanada.speakers;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.Client;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Speaker;

public class SpeakersCreateActivity extends Activity implements OnClickListener {
	private EditText nameView;
	private EditText baseUrlView;
	private Button submitButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_create);
        
        this.nameView = (EditText) this.findViewById(R.id.name);
        this.baseUrlView = (EditText) this.findViewById(R.id.base_url);
        this.submitButton = (Button) this.findViewById(R.id.submit);

        this.submitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {	
		Speaker speaker = new Speaker();
		
		speaker.set("Name", this.nameView.getText().toString());
		speaker.set("BaseURL", this.baseUrlView.getText().toString());
		
		try {
			Client.sharedInstance().createSpeaker(speaker);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// and we're done with the activity
		this.finish();
	}
}
