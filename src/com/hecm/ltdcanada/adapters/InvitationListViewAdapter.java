package com.hecm.ltdcanada.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.hecm.ltdcanada.httpclient.models.Invitation;

public class InvitationListViewAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	private List<Invitation> invitations;
	
	public InvitationListViewAdapter(Context context, List<Invitation> invitations) {
		this.context = context;
		this.invitations = invitations;
	}

	@Override
	public int getCount() {
		return this.invitations.size();
	}

	@Override
	public Object getItem(int position) {
		return this.invitations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.invitations.get(position).getInteger("ID");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		InvitationView view;
		
		if(convertView == null) {
			view = InvitationView.inflateNew(context);
		} else {
			view = (InvitationView) convertView;
		}
		
		view.setInvitation(this.invitations.get(position));
		
		return view;
	}

}
