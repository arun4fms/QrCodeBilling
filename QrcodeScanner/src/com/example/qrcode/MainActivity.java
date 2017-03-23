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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	double uprice;
	String s;
	String email;

	ArrayList<String> arg;
	ArrayList<String> myArrayList = new ArrayList<String>();
	ArrayList<String> myPriceList = new ArrayList<String>();
	ArrayList<String> myQuantsList = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
		email = sharedPref.getString("name", "");

		Log.i("EmailID", "" + email);

		Button scanBtn = (Button) findViewById(R.id.btnScan);

		scanBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {

					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1)
							.show();

				}

			}
		});

	}

	// In the same activity you’ll need the following to retrieve the results:
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {

				final String result = intent.getStringExtra("SCAN_RESULT");
				arg = new ArrayList<String>();
				for (String ob : result.split(":")) {
					arg.add(ob.trim());
				}
				Log.d("Arraylist", "" + arg);

				Toast.makeText(getBaseContext(), "name : " + arg.get(1),
						Toast.LENGTH_LONG).show();

				AlertDialog.Builder alert = new AlertDialog.Builder(
						MainActivity.this);
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				// this is what I did to added the layout to the alert dialog
				View layout = inflater.inflate(R.layout.productdetail, null);
				alert.setView(layout);
				final TextView productName = (TextView) layout
						.findViewById(R.id.textView1);
				final TextView actualprice = (TextView) layout
						.findViewById(R.id.textView2);
				final TextView updatedprice = (TextView) layout
						.findViewById(R.id.textView3);
				final TextView expirydate = (TextView) layout
						.findViewById(R.id.textView5);
				final EditText usernameInput = (EditText) layout
						.findViewById(R.id.quantity);

				productName.setText(arg.get(0));
				actualprice.setText("Product price : " + arg.get(3));
				expirydate.setText("Expiry Date : " + arg.get(1));
				
				alert.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Log.i("quantity", ""
										+ usernameInput.getText().toString());
								s = usernameInput.getText().toString();
								double i = Double.parseDouble(s);
								int i1 = Integer.parseInt(arg.get(3));
								uprice = i * i1;
								Log.i("updated price", "" + uprice);
								Log.i("Check", "working");

								Productname pna = new Productname();

								pna.setPname(arg.get(0));
								pna.setPprice("" + uprice);
								pna.setPquants(s);
								Log.i("NewData", "" + pna.getPname());

								myArrayList.add(pna.getPname());
								myPriceList.add(pna.getPprice());
								myQuantsList.add(pna.getPquants());

							}
						});
				alert.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// Canceled.
								dialog.cancel();
							}
						});

				alert.show();

			}

		} else if (resultCode == RESULT_CANCELED) {

			Toast.makeText(this, "Scan cancelled...", Toast.LENGTH_LONG).show();
		}
	}

	public void viewproducts(View v) {

		SharedPreferences sPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor sEdit = sPrefs.edit();

		for (int i = 0; i < myArrayList.size(); i++) {
			sEdit.putString("pname" + i, myArrayList.get(i));

		}
		for (int i = 0; i < myPriceList.size(); i++) {
			sEdit.putString("price" + i, myPriceList.get(i));
			Log.i("price list", "" + myPriceList.get(i));
		}
		for (int i = 0; i < myQuantsList.size(); i++) {
			sEdit.putString("Quantity" + i, myQuantsList.get(i));

		}
		sEdit.putInt("size", myArrayList.size());
		sEdit.putInt("size1", myPriceList.size());
		sEdit.putInt("size2", myQuantsList.size());
		sEdit.commit();

		Intent i = new Intent(MainActivity.this, Productlist.class);

		startActivity(i);

	}
}
