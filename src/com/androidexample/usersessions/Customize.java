package com.androidexample.usersessions;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Customize extends Activity {

	Button red, green, blue;
	LinearLayout mLayout;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customize);
		red = (Button) findViewById(R.id.red);
		green = (Button) findViewById(R.id.green);
		blue = (Button) findViewById(R.id.blue);

		mLayout = (LinearLayout) findViewById(R.id.lyt_customize);

		red.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mLayout.setBackgroundColor(Color.RED);
			}
		});
		green.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mLayout.setBackgroundColor(Color.GREEN);
			}
		});
		blue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mLayout.setBackgroundColor(Color.BLUE);
			}
		});

	}
}
