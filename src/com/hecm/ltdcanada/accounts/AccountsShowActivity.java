package com.hecm.ltdcanada.accounts;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.models.Account;

public class AccountsShowActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_show);
        
        Bundle extras = getIntent().getExtras();
        
        this.setAccount((Account) extras.getSerializable("account"));
    }
    
    public void setAccount(Account account) {
    	getTextView(R.id.firstname).setText(account.getString("FirstName"));
    	getTextView(R.id.lastname).setText(account.getString("LastName"));
    	getTextView(R.id.email).setText(account.getString("Email"));
    	getTextView(R.id.role).setText(account.getString("Role"));
    	
    	Date lastSignedInAt = account.getDate("LastSignedInAt");
    	
    	DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG);
    	formatter.setTimeZone(TimeZone.getDefault());
    	
    	if(lastSignedInAt != null) {
    		getTextView(R.id.lastsignin).setText(formatter.format(lastSignedInAt));
    	} else {
    		getTextView(R.id.lastsignin).setText(R.string.never);
    	}
    }
    
    private TextView getTextView(int res) {
    	return (TextView) this.findViewById(res);
    }
}
