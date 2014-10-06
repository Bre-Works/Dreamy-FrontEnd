package com.breworks.dreamy.tabpanel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import com.breworks.dreamy.Main;
import com.breworks.dreamy.ToDoList;
import com.breworks.dreamy.Notes;
import com.breworks.dreamy.FreeTime;
import com.breworks.dreamy.R;

/**
 * Created by aidifauzan on 04/10/2014.
 */

public class MyTabHostProvider extends TabHostProvider 
{
	private Tab homeTab;
	private Tab todoTab;
	private Tab notesTab;
	private Tab freetimeTab;
	
	private TabView tabView;
	private GradientDrawable gradientDrawable, transGradientDrawable;

	public MyTabHostProvider(Activity context) {
		super(context);
	}

	@Override
	public TabView getTabHost(String category) 
	{
		tabView = new TabView(context);
		tabView.setOrientation(TabView.Orientation.BOTTOM);
		
		gradientDrawable = new GradientDrawable(
	            GradientDrawable.Orientation.TOP_BOTTOM,
	            new int[] {0x00000000, 0x00000000});
	    gradientDrawable.setCornerRadius(0f);
	    gradientDrawable.setDither(true);
	    
	    transGradientDrawable = new GradientDrawable(
	            GradientDrawable.Orientation.TOP_BOTTOM,
	            new int[] {0x00000000, 0x00000000});
	    transGradientDrawable.setCornerRadius(0f);
	    transGradientDrawable.setDither(true);

		homeTab = new Tab(context, category);
		homeTab.setIcon(R.drawable.homeidle);
		homeTab.setIconSelected(R.drawable.homeactive);
        homeTab.setBtnText("Home");
        homeTab.setBtnTextColor(0x00000000);
        homeTab.setSelectedBtnTextColor(0x00000000);
		homeTab.setBtnGradient(transGradientDrawable);
		homeTab.setSelectedBtnGradient(gradientDrawable);
		homeTab.setIntent(new Intent(context, Main.class));

		todoTab = new Tab(context, category);
        todoTab.setIcon(R.drawable.todoidle);
        todoTab.setIconSelected(R.drawable.todoactive);
        todoTab.setBtnText("Todo");
        todoTab.setBtnTextColor(0x00000000);
        todoTab.setSelectedBtnTextColor(0x00000000);
        todoTab.setBtnGradient(transGradientDrawable);
        todoTab.setSelectedBtnGradient(gradientDrawable);
        todoTab.setIntent(new Intent(context, ToDoList.class));

        notesTab = new Tab(context, category);
        notesTab.setIcon(R.drawable.notesidle);
        notesTab.setIconSelected(R.drawable.notesactive);
        notesTab.setBtnText("Notes");
        notesTab.setBtnTextColor(0x00000000);
        notesTab.setSelectedBtnTextColor(0x00000000);
        notesTab.setBtnGradient(transGradientDrawable);
        notesTab.setSelectedBtnGradient(gradientDrawable);
        notesTab.setIntent(new Intent(context, Notes.class));

        freetimeTab = new Tab(context, category);
        freetimeTab.setIcon(R.drawable.freetimeidle);
        freetimeTab.setIconSelected(R.drawable.freetimeactive);
        freetimeTab.setBtnText("FreeTime");
        freetimeTab.setBtnTextColor(0x00000000);
        freetimeTab.setSelectedBtnTextColor(0x00000000);
        freetimeTab.setBtnGradient(transGradientDrawable);
        freetimeTab.setSelectedBtnGradient(gradientDrawable);
        freetimeTab.setIntent(new Intent(context, FreeTime.class));

		tabView.addTab(homeTab);
		tabView.addTab(todoTab);
		tabView.addTab(notesTab);
		tabView.addTab(freetimeTab);

		return tabView;
	}
}