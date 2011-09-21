package com.hecm.ltdcanada.accounts;

import java.io.IOException;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.adapters.AccountListViewAdapter;
import com.hecm.ltdcanada.adapters.AccountView;
import com.hecm.ltdcanada.httpclient.Client;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Account;
import com.hecm.ltdcanada.views.AddItemView;

public class AccountsListActivity extends ListActivity implements OnItemClickListener {
	private List<Account> accounts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			this.accounts = Client.sharedInstance().getAllAccounts();
			
			AddItemView addItem = AddItemView.inflateNew(this);
			addItem.setText(R.string.add_account);
			
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);
			lv.setOnItemClickListener(this);
			lv.addHeaderView(addItem);
			
			this.setListAdapter(new AccountListViewAdapter(this, this.accounts));
			
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
		if(view instanceof AccountView) {
			Account account = ((AccountView) view).getAccount();
			
			intent = new Intent(view.getContext(), AccountsShowActivity.class);
			intent.putExtra("account", account);
		} else if(view instanceof AddItemView) {
			intent = new Intent(view.getContext(), AccountsCreateActivity.class);
		}
		
		if(intent != null) {
			this.startActivity(intent);
		}
		
	}

}
