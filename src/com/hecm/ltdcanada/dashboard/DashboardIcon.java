package com.hecm.ltdcanada.dashboard;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardIcon extends LinearLayout {
	private ImageView image;
	private TextView label;
	private Integer iconResource;
	private Integer stringResource;
	
	public DashboardIcon(Context context) {
		super(context);
		
		this.setOrientation(LinearLayout.VERTICAL);
		this.setLayoutParams(new GridView.LayoutParams(128, 128));
		
		this.image = new ImageView(context);
		this.image.setMaxHeight(100);
		this.image.setMaxWidth(100);
		this.image.setAdjustViewBounds(true);
        
        this.label = new TextView(context);
        this.label.setGravity(Gravity.CENTER);
        
        this.addView(this.image);
        this.addView(this.label);
	}
	
	public void setIconResource(Integer iconResource) {
		this.image.setImageResource(iconResource);
		this.iconResource = iconResource;
	}
	
	public Integer getIconResource() {
		return this.iconResource;
	}
	
	public void setStringResource(Integer stringResource) {
		this.label.setText(stringResource);
		this.stringResource = stringResource;
	}
	
	public Integer getStringResource() {
		return this.stringResource;
	}

}
