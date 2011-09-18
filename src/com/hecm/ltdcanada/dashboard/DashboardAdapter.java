package com.hecm.ltdcanada.dashboard;

import com.hecm.ltdcanada.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class DashboardAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	
	private Integer[] icons = {
		R.drawable.email,
		R.drawable.speaker,
		R.drawable.users,
		R.drawable.profile,
		R.drawable.settings
	};
	
	private Integer[] labels = {
		R.string.invitations,
		R.string.speakers,
		R.string.users,
		R.string.profile,
		R.string.settings
	};
	
	public DashboardAdapter(Context c) {
		this.context = c;
	}
	
	@Override
	public int getCount() {
		return icons.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        DashboardIcon iconView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	iconView = new DashboardIcon(context);
        } else {
            iconView = (DashboardIcon) convertView;
        }

        iconView.setIconResource(icons[position]);
        iconView.setStringResource(labels[position]);
        
        return iconView;
	}

}
