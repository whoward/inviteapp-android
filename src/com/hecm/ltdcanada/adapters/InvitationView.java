package com.hecm.ltdcanada.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hecm.ltdcanada.R;
import com.hecm.ltdcanada.httpclient.models.Invitation;

public class InvitationView extends TableLayout {
	private Invitation model;
	
	public static InvitationView inflateNew(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return (InvitationView) inflater.inflate(R.layout.invitation_list_item, null);
	}
	
	public InvitationView(Context context) {
		super(context);
	}

	public InvitationView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Invitation getInvitation() {
		return this.model;
	}
	
	public void setInvitation(Invitation i) {
		this.model = i;
		this.updateInterface();
	}

	private void updateInterface() {
		TextView nameView = (TextView) this.findViewById(R.id.name);
		TextView viewsView = (TextView) this.findViewById(R.id.views);
		TextView emailView = (TextView) this.findViewById(R.id.email);
		TextView statusView = (TextView) this.findViewById(R.id.status);
		
		nameView.setText(this.model.getString("Name"));
		viewsView.setText(this.model.getViewsString());
		emailView.setText(this.model.getString("Email"));
		statusView.setText(this.model.getString("Status"));
		
		statusView.setTextColor(this.model.getStatusColor());
	}

}
