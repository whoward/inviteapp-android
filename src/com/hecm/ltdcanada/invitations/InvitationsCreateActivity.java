package com.hecm.ltdcanada.invitations;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hecm.ltdcanada.Global;
import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.adapters.SpeakerSpinnerAdapter;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Invitation;

public class InvitationsCreateActivity extends Activity implements OnClickListener {
	private EditText nameView;
	private EditText emailView;
	private Spinner speakerView;
	private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation_create);
        
        this.nameView = (EditText) this.findViewById(R.id.name);
        this.emailView = (EditText) this.findViewById(R.id.email);
        this.speakerView = (Spinner) this.findViewById(R.id.speaker);
        this.submitButton = (Button) this.findViewById(R.id.submit);
        
        try {
			this.speakerView.setAdapter(new SpeakerSpinnerAdapter(this));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.submitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {	
		Invitation invite = new Invitation();
		
		invite.set("Name", this.nameView.getText().toString());
		invite.set("Email", this.emailView.getText().toString());
		invite.set("SpeakerID", this.speakerView.getSelectedItemId());
		
		try {
			Global.getClient().createInvitation(invite);
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
