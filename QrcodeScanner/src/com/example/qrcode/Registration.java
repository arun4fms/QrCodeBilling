package com.example.qrcode;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity {

	EditText phoneno, email, name;
	Button register;
	TextView loginhere;
	String ph, mail, custname;
	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	private ProgressDialog pd;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		phoneno = (EditText) findViewById(R.id.reg_fullname);
		email = (EditText) findViewById(R.id.reg_email);
		name = (EditText) findViewById(R.id.reg_password);
		loginhere = (TextView) findViewById(R.id.link_to_login);

	}

	public void Register(View v) {

		ph = phoneno.getText().toString();
		mail = email.getText().toString();
		custname = name.getText().toString();

		pd = ProgressDialog.show(Registration.this, "",
				"Registering you in...", false, false);

		new Thread() {
			public void run() {
				try {
					httpClient = new DefaultHttpClient();
					httpPost = new HttpPost(Ipaddress.URL);
					List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
					nameValuePair.add(new BasicNameValuePair("flag", "2"));
					nameValuePair.add(new BasicNameValuePair("phone", ph));
					nameValuePair.add(new BasicNameValuePair("email", mail));
					nameValuePair.add(new BasicNameValuePair("custname",
							custname));
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
					httpResponse = httpClient.execute(httpPost);
					Log.d("Connection success", "Connection created");
					handler.sendEmptyMessage(1);

				}

				catch (Exception e) {
					// handler.sendEmptyMessage(3);
					Log.d("Connection status error", "Connection" + e);
					handler.sendEmptyMessage(3);
				}
			}
		}.start();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				pd.dismiss();
				Log.i("login response", "login success");

				startActivity(new Intent(Registration.this, Login.class));

				break;
			case 2:
				pd.dismiss();
				Toast.makeText(Registration.this,
						"Your username and password is wrong !",
						Toast.LENGTH_LONG).show();
				break;
			case 3:
				pd.dismiss();
				Toast.makeText(Registration.this,
						"Please check your internet connection or URL!",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
}
