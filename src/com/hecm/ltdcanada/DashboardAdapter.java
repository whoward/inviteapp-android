package com.hecm.ltdcanada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class DashboardAdapter extends BaseAdapter implements ListAdapter {
	private Context context;
	
	private Integer[] icons = {
		android.R.drawable.ic_dialog_email
	};
	
	private Integer[] labels = {
		R.string.invitations
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
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(icons[position]);
        return imageView;
	}

}
