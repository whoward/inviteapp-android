package com.hecm.ltdcanada.accounts;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.Client;
import com.hecm.ltdcanada.httpclient.ClientException;
import com.hecm.ltdcanada.httpclient.models.Account;

public class AccountsCreateActivity extends Activity implements OnClickListener {
	private EditText emailView;
	private Spinner roleView;
	private Button submitButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_create);
        
        this.emailView = (EditText) this.findViewById(R.id.email);
        this.roleView = (Spinner) this.findViewById(R.id.role);
        this.submitButton = (Button) this.findViewById(R.id.submit);
        
        this.roleView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Account.ROLES));

        this.submitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {	
		Account account = new Account();
		
		account.set("Email", this.emailView.getText().toString());
		account.set("Role", this.roleView.getSelectedItem());
		
		try {
			Client.sharedInstance().createAccount(account);
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
