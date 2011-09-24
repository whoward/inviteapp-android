package com.hecm.ltdcanada.dashboard;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.hecm.ltdcanada.Global;
import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.accounts.AccountsListActivity;
import com.hecm.ltdcanada.accounts.AccountsShowActivity;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Account;
import com.hecm.ltdcanada.invitations.InvitationsListActivity;
import com.hecm.ltdcanada.settings.SettingsActivity;
import com.hecm.ltdcanada.speakers.SpeakersListActivity;

public class DashboardActivity extends Activity implements OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GridView gridview = (GridView) findViewById(R.id.dashboard);
        gridview.setAdapter(new DashboardAdapter(this));
        gridview.setOnItemClickListener(this);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        
        Global.setPreferences(prefs.getAll());
        
        prefs.registerOnSharedPreferenceChangeListener(this);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	DashboardIcon icon = (DashboardIcon) view;
    	
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
    			Account profile = null;
    			
    			try {
    				profile = Global.getClient().getUserAccount();
    			} catch (ClientException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    			intent = new Intent(view.getContext(), AccountsShowActivity.class);
    			intent.putExtra("account", profile);
    			break;
    			
    		case R.string.settings:
    			intent = new Intent(view.getContext(), SettingsActivity.class);
    			break;
    	}
    	    	
    	if(intent != null) {
    		this.startActivity(intent);
    	}
    	
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		Global.preferenceUpdated(key, prefs.getString(key, null));
	}
}