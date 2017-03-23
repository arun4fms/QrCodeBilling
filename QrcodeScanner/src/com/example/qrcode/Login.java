package com.example.qrcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	EditText email, phoneno;
	Button login;
	TextView register;
	String mail, pswd;
	private ProgressDialog pd;
	private Thread thread;
	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	private InputStream stream = null;
	private StringBuilder stringBuilder;
	private String error_response;
	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login1);

		email = (EditText) findViewById(R.id.email);
		phoneno = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.btnLogin);
		register = (TextView) findViewById(R.id.link_to_register);
		// Create object of SharedPreferences.
		sharedPref = getSharedPreferences("mypref", 0);
		// now get Editor
		editor = sharedPref.edit();

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Login.this, Registration.class);
				startActivity(i);
			}
		});
	}

	public void Login(View v) {

		// put your value
		editor.putString("name", email.getText().toString());
		editor.putString("phonenumber", phoneno.getText().toString());
		// commits your edits
		editor.commit();

		if (TextUtils.isEmpty(email.getText().toString())
				|| TextUtils.isEmpty(phoneno.getText().toString())) {
			Toast.makeText(Login.this, "Email or Password field is empty",
					Toast.LENGTH_SHORT).show();
		} else {
			pd = ProgressDialog.show(Login.this, "", "Loggin you in...", false,
					false);

			thread = new Thread() {
				public void run() {

					try {

						httpClient = new DefaultHttpClient();
						httpPost = new HttpPost(Ipaddress.URL);
						List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
						nameValuePair.add(new BasicNameValuePair("flag", "3"));
						nameValuePair.add(new BasicNameValuePair("Email", email
								.getText().toString()));
						nameValuePair.add(new BasicNameValuePair("Phoneno",
								phoneno.getText().toString()));

						httpPost.setEntity(new UrlEncodedFormEntity(
								nameValuePair));
						httpResponse = httpClient.execute(httpPost);
						InputStream inputStream = httpResponse.getEntity()
								.getContent();

						InputStreamReader inputStreamReader = new InputStreamReader(
								inputStream);

						BufferedReader bufferedReader = new BufferedReader(
								inputStreamReader);

						stringBuilder = new StringBuilder();

						String bufferedStrChunk = null;

						while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
							stringBuilder.append(bufferedStrChunk);
						}
						System.out
								.println("Login :" + stringBuilder.toString());
						if (stringBuilder.toString().trim().equals("yes")) {
							handler.sendEmptyMessage(1);
						} else {
							handler.sendEmptyMessage(2);
						}

					} catch (Exception e) {
						handler.sendEmptyMessage(3);
					}
				}
			};

			thread.start();
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				pd.dismiss();
				Log.i("login response", "login success");

				startActivity(new Intent(Login.this, MainActivity.class));

				break;
			case 2:
				pd.dismiss();
				Toast.makeText(Login.this,
						"Your username and password is wrong !",
						Toast.LENGTH_LONG).show();
				break;
			case 3:
				pd.dismiss();
				Toast.makeText(Login.this,
						"Please check your internet connection or URL!",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

}