package com.hecm.ltdcanada.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.models.Account;

public class AccountView extends LinearLayout {
	private Account model;
	
	public static AccountView inflateNew(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return (AccountView) inflater.inflate(R.layout.account_list_item, null);
	}
	
	public AccountView(Context context) {
		super(context);
	}

	public AccountView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Account getAccount() {
		return this.model;
	}
	
	public void setAccount(Account i) {
		this.model = i;
		this.updateInterface();
	}

	private void updateInterface() {
		TextView nameView = (TextView) this.findViewById(R.id.name);
		TextView roleView = (TextView) this.findViewById(R.id.role);
		
		nameView.setText(this.model.getIdentifyingName());
		roleView.setText(this.model.getString("Role"));
	}
}
