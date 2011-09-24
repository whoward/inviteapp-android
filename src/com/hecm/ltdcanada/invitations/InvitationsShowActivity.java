package com.hecm.ltdcanada.invitations;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hecm.ltdcanada.Global;
import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Invitation;
import com.hecm.ltdcanada.httpclient.models.Speaker;

public class InvitationsShowActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation_show);
        
        Bundle extras = getIntent().getExtras();
        
        this.setInvitation((Invitation) extras.getSerializable("invitation"));
    }
    
    public void setInvitation(Invitation invite) {
    	getTextView(R.id.name).setText(invite.getString("Name"));
    	
    	getTextView(R.id.email).setText(invite.getString("Email"));
    	
    	getTextView(R.id.status).setText(invite.getString("Status"));
    	getTextView(R.id.status).setTextColor(invite.getStatusColor());
    	
    	getTextView(R.id.views).setText(invite.getViewsString());
    	
    	
		try {
			Speaker speaker = Global.getClient().getSpeaker(invite.getInteger("SpeakerID").toString());
			getTextView(R.id.speaker).setText(speaker.getString("Name"));
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	getTextView(R.id.link).setText(invite.getString("Token"));
    	
    	Date sentAt = invite.getDate("SentAt");
    	Date expiresAt = invite.getDate("ExpiresAt");
    	Date viewedAt = invite.getDate("ViewedAt");
    	
    	DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG);
    	formatter.setTimeZone(TimeZone.getDefault());
    	
    	
    	if(sentAt != null) {
    		getTextView(R.id.sent_at).setText(formatter.format(sentAt));
    	} else {
    		getTextView(R.id.sent_at).setText(R.string.never);
    	}
    	
    	if(expiresAt != null) {
    		getTextView(R.id.expires_at).setText(formatter.format(expiresAt));
    	} else {
    		getTextView(R.id.expires_at).setText(R.string.never);
    	}
    	
    	if(viewedAt != null) {
    		getTextView(R.id.last_viewed_at).setText(formatter.format(viewedAt));
    	} else {
    		getTextView(R.id.last_viewed_at).setText(R.string.never);
    	}
    }
    
    private TextView getTextView(int res) {
    	return (TextView) this.findViewById(res);
    }
}
