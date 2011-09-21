package com.hecm.ltdcanada.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.accounts.AccountsListActivity;
import com.hecm.ltdcanada.invitations.InvitationsListActivity;
import com.hecm.ltdcanada.speakers.SpeakersListActivity;

public class DashboardActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GridView gridview = (GridView) findViewById(R.id.dashboard);
        gridview.setAdapter(new DashboardAdapter(this));
        gridview.setOnItemClickListener(this);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	DashboardIcon icon = (DashboardIcon) view;
    	
    	String text = null;
    	Intent intent = null;
    	switch(icon.getStringResource()) {
    		case R.string.invitations:
    			intent = new Intent(view.getContext(), InvitationsListActivity.class);
    			break;
    		case R.string.speakers:
    			intent = new Intent(view.getContext(), SpeakersListActivity.class);
    			break;
    		case R.string.users:
    			intent = new Intent(view.getContext(), AccountsListActivity.class);
    			break;
    		case R.string.profile:
    			text = "You clicked on profile";
    			break;
    		case R.string.settings:
    			text = "You clicked on settings";
    			break;
    		default:
    			text = "You clicked on...something - WTF";
    			break;
    	}
    	
    	if(text != null) {
    		Toast.makeText(DashboardActivity.this, text, Toast.LENGTH_SHORT).show();
    	}
    	
    	if(intent != null) {
    		this.startActivity(intent);
    	}
    	
    }
}