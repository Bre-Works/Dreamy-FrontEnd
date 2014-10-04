package com.breworks.dreamy.tabpanel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.breworks.dreamy.R;

public class Tab {
	private int resourceIcon;
	private int resourceIconSelected = 0;

	private final Activity context;
	private Intent intent;

	private View view;
	private Button btn;
	private final String tabTag;

	public int preferedHeight = -1;
	private boolean isSelected;
	private Dialog dialog;
	private int requestCode = -1;
	private String btnText;
	private int textColor;
	private int selectedTextColor;
//	private int btnColor;
//	private int selectedBtnColor;
	private GradientDrawable btnGradient;
	private GradientDrawable selectedBtnGradient;
	private float btnTextSize;

	public Tab(Activity context, String tabTag) {
		if (context == null) {
			throw new IllegalStateException("Context can't be null");
		}
		this.tabTag = tabTag;
		this.context = context;
	}

	public void setIcon(int resourceIcon) {
		this.resourceIcon = resourceIcon;
	}

	public void setIconSelected(int resourceIcon) {
		this.resourceIconSelected = resourceIcon;
	}
	
//	public void setBtnColor(int btnColor) {
//		this.btnColor = btnColor;
//	}
//	
//	public void setSelectedBtnColor(int btnColor) {
//		this.selectedBtnColor = btnColor;
//	}
	
	public void setBtnGradient(GradientDrawable btnGradient) {
		this.btnGradient = btnGradient;
	}
	
	public void setSelectedBtnGradient(GradientDrawable btnGradient) {
		this.selectedBtnGradient = btnGradient;
	}
	
	public void setBtnTextColor(int textColor) {
		this.textColor = textColor;
	}
	
	public void setSelectedBtnTextColor(int textColor) {
		this.selectedTextColor = textColor;
	}
	
	public void setBtnTextSize(float btnTextSize) {
		this.btnTextSize = btnTextSize;
	}

	public void setIntent(Intent intent, int requestForResult) {
		this.intent = intent;
		this.requestCode = requestForResult;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Intent getIntent() {
		return intent;
	}

	public String getTag() {
		return tabTag;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	
	public View getView() {
		if (view == null) {
			createView();
		}
		return view;
	}
	
	private void createView() 
	{
		btn = (Button)(context.getLayoutInflater().inflate(R.layout.button, null));

		int iconId = resourceIcon;
//		int btnBackColor = btnColor;
		GradientDrawable btnBackGrad = btnGradient;
		int btnTextColor = textColor;
		if (isSelected && resourceIconSelected != 0) {
			iconId = resourceIconSelected;
//			btnBackColor = selectedBtnColor;
			btnBackGrad = selectedBtnGradient;
			btnTextColor = selectedTextColor;
		}

		btn.setCompoundDrawablesWithIntrinsicBounds(0, iconId, 0, 0);
		btn.setText(btnText);
		btn.setTextColor(btnTextColor);
		btn.setTextSize(btnTextSize);
//		btn.setBackgroundColor(btnBackColor);
		btn.setBackgroundDrawable(btnBackGrad);
		btn.setMinimumHeight(preferedHeight);
		btn.setPadding(0, 15, 0, 0);
		
		bindListeners();
		view = btn;
	}

	private void bindListeners() {
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (intent == null && dialog == null) {
					Toast.makeText(context,
							"Intent or Dialog not set for '" + tabTag + "'",
							Toast.LENGTH_SHORT).show();

				} else if (intent != null && dialog != null) {
					Toast.makeText(
							context,
							" Only ONE can be set Intent or Dialog for '"
									+ tabTag + "'", Toast.LENGTH_SHORT).show();
				} else {
					if (intent != null) {
						if (requestCode != -1) {
							// This will start activity for result
						} else {
							context.startActivity(intent);
							context.overridePendingTransition(0, 0);
							context.finish();
						}
					} else if (dialog != null) {
						dialog.show();
					}
				}
			}
		});

		btn.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent e) {
				if (e.getAction() == MotionEvent.ACTION_DOWN) {
					btn.setBackgroundColor(0x400000FF);
				} else if (e.getAction() == MotionEvent.ACTION_UP) {
					btn.setBackgroundColor(0x00000000);
				}
				return false;
			}
		});
	}
}