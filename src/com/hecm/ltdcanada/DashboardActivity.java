package com.hecm.ltdcanada;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

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
    	
    	String text;
    	switch(icon.getStringResource()) {
    		case R.string.invitations:
    			text = "You clicked on invitations";
    			break;
    		case R.string.speakers:
    			text = "You clicked on speakers";
    			break;
    		case R.string.users:
    			text = "You clicked on users";
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
    	Toast.makeText(DashboardActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}