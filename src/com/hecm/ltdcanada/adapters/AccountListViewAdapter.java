package com.hecm.ltdcanada.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.hecm.ltdcanada.httpclient.models.Account;

public class AccountListViewAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private List<Account> accounts;
	
	public AccountListViewAdapter(Context context, List<Account> invitations) {
		this.context = context;
		this.accounts = invitations;
	}

	@Override
	public int getCount() {
		return this.accounts.size();
	}

	@Override
	public Object getItem(int position) {
		return this.accounts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.accounts.get(position).getInteger("ID");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AccountView view;
		
		if(convertView == null) {
			view = AccountView.inflateNew(context);
		} else {
			view = (AccountView) convertView;
		}
		
		view.setAccount(this.accounts.get(position));
		
		return view;
	}
}
