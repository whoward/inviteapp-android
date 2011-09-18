package com.hecm.ltdcanada.invitations;

import java.io.IOException;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.adapters.InvitationListViewAdapter;
import com.hecm.ltdcanada.adapters.InvitationView;
import com.hecm.ltdcanada.httpclient.Client;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Invitation;
import com.hecm.ltdcanada.views.AddItemView;

public class InvitationsListActivity extends ListActivity implements OnItemClickListener {
	
	private List<Invitation> invitations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			this.invitations = Client.sharedInstance().getAllInvitations();
			
			AddItemView addItem = AddItemView.inflateNew(this);
			addItem.setText(R.string.add_invitation);
			
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(this);
			lv.addHeaderView(addItem);
			
			this.setListAdapter(new InvitationListViewAdapter(this, this.invitations));
			
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
		String text;
		if(view instanceof InvitationView) {
			text = ((InvitationView) view).getInvitation().getString("Name");
		} else if(view instanceof AddItemView) {
			text = "adding a new invitation";
		} else {
			text = "wtf?";
		}
		
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}
}
