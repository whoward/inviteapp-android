package com.hecm.ltdcanada.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hecm.ltdcanada.R;

public class AddItemView extends RelativeLayout {

	public static AddItemView inflateNew(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return (AddItemView) inflater.inflate(R.layout.add_item, null);
	}
	
	public AddItemView(Context context) {
		super(context);
	}
	
	public AddItemView(Context context, AttributeSet attrSet) {
		super(context, attrSet);
	}
	
	public void setText(int stringResource) {	
		((TextView) this.findViewById(R.id.text)).setText(stringResource);
	}

}
