package com.androidexample.usersessions;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// User Session Manager Class
	UserSessionManager session;

	// Button Logout
	Button btnLogout, btnCustomize, btnPass;
	Button btnClosePopup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Session class instance
		session = new UserSessionManager(getApplicationContext());

		TextView lblName = (TextView) findViewById(R.id.lblName);
		TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

		// Button logout
		btnLogout = (Button) findViewById(R.id.btnLogout);

		btnCustomize = (Button) findViewById(R.id.btnCustomize);

		btnPass = (Button) findViewById(R.id.btnPass);
		Toast.makeText(getApplicationContext(),
				"User Login Status: " + session.isUserLoggedIn(),
				Toast.LENGTH_LONG).show();

		// Check user login
		// If User is not logged in , This will redirect user to LoginActivity.
		if (session.checkLogin())
			finish();

		// get user data from session
		HashMap<String, String> user = session.getUserDetails();

		// get name
		String name = user.get(UserSessionManager.KEY_NAME);

		// get email
		String email = user.get(UserSessionManager.KEY_EMAIL);

		// Show user data on activity
		lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
		lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

		btnLogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Clear the User session data
				// and redirect user to LoginActivity
				session.logoutUser();
			}
		});

		btnCustomize.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(MainActivity.this, Customize.class));

			}
		});

		btnPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow();
			}
		});

	}

	private PopupWindow pwindo;

	private void initiatePopupWindow() {
		try {
			// We need to get the instance of the LayoutInflater
			LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.pop_up,
					(ViewGroup) findViewById(R.id.lyt_popUp));
			pwindo = new PopupWindow(layout, 700, 450, true);
			pwindo.showAtLocation(layout, Gravity.TOP, 0, 200);

			btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
			btnClosePopup.setOnClickListener(cancel_button_click_listener);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OnClickListener cancel_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			pwindo.dismiss();

		}
	};

}
