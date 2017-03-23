package com.example.qrcode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

public class Productlist extends Activity {

	private ListView list_products;
	private ProgressDialog pd;
	private CrimeListAdapter crimeListAdapter = null;
	double sum = 0;
	SharedPreferences sharedPref;
	String email, billno;
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<Model> productlist = new ArrayList<Model>();
	Model model;
	String j, k;
	ArrayList<String> array;
	ArrayList<String> as = new ArrayList<String>();
	ArrayList<String> myArrayList = new ArrayList<String>();
	ArrayList<String> myQuantsList = new ArrayList<String>();
	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	String ownerphno = "8807624894", ph;
	int i1;
	// set to PaymentActivity.ENVIRONMENT_PRODUCTION to move real money.
	// set to PaymentActivity.ENVIRONMENT_SANDBOX to use your test credentials
	// from https://developer.paypal.com
	// set to PaymentActivity.ENVIRONMENT_NO_NETWORK to kick the tires without
	// communicating to PayPal's servers.
	private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_SANDBOX;

	// note that these credentials will differ between live & sandbox
	// environments.
	private static final String CONFIG_CLIENT_ID = "AYNEjxCZKCrJkJ1PTSHiwqNwrS38eA8stFgxKFV2E9S4pmjbLIeTXd8YdPsa";
	// when testing in sandbox, this is likely the -facilitator email address.
	private static final String CONFIG_RECEIVER_EMAIL = "mafiamoori-facilitator@gmail.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productlist);

		Intent intent = new Intent(this, PayPalService.class);

		intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
				CONFIG_ENVIRONMENT);
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
				CONFIG_RECEIVER_EMAIL);

		startService(intent);

		SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
		email = sharedPref.getString("name", "");
		ph = sharedPref.getString("phonenumber", "");
		Log.i("Email And Number", "" + email + ph);

		SharedPreferences sPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		ArrayList<String> myAList = new ArrayList<String>();
		ArrayList<String> myPList = new ArrayList<String>();
		ArrayList<String> myQList = new ArrayList<String>();

		int size = sPrefs.getInt("size", 0);
		int size1 = sPrefs.getInt("size1", 0);
		int size2 = sPrefs.getInt("size2", 0);

		for (int j = 0; j < size; j++) {
			myAList.add(sPrefs.getString("pname" + j, email));
		}

		for (int j = 0; j < size1; j++) {
			myPList.add(sPrefs.getString("price" + j, email));
		}

		for (int j = 0; j < size2; j++) {
			myQList.add(sPrefs.getString("Quantity" + j, email));
		}
		Log.i("Product list", "" + myAList + myPList + myQList);

		for (int i = 0, k = 0, j = 0; i < myAList.size(); i++, k++, j++) {
			model = new Model(myAList.get(i), myPList.get(k), myQList.get(j),
					false);
			productlist.add(model);
		}

		crimeListAdapter = new CrimeListAdapter(this, R.layout.listofproduct,
				productlist);
		list_products = (ListView) findViewById(R.id.listView1);
		list_products.setAdapter(crimeListAdapter);
		crimeListAdapter.notifyDataSetChanged();

	}

	public class CrimeListAdapter extends ArrayAdapter<Model> {

		private ArrayList<Model> productlist;

		public CrimeListAdapter(Context context, int textViewResourceId,
				ArrayList<Model> productlist) {
			super(context, textViewResourceId, productlist);
			this.productlist = new ArrayList<Model>();
			this.productlist.addAll(productlist);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(Productlist.this).inflate(
						R.layout.listofproduct, parent, false);
				viewHolder.txt_product = (TextView) convertView
						.findViewById(R.id.txt_product);
				viewHolder.txt_price = (TextView) convertView
						.findViewById(R.id.txt_price);
				viewHolder.txt_quants = (TextView) convertView
						.findViewById(R.id.txt_quants);
				viewHolder.txt_check = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(viewHolder);
				viewHolder.txt_check.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CheckBox cb = (CheckBox) v;
						Model model = (Model) cb.getTag();

						model.setSelected(cb.isChecked());
					}
				});
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			Model model = productlist.get(position);
			viewHolder.txt_product.setText(model.getName());
			viewHolder.txt_price.setText("Price: " + model.getPrice());
			viewHolder.txt_quants.setText("Quantity: " + model.getQuants());

			viewHolder.txt_check.setChecked(model.isSelected());
			viewHolder.txt_check.setTag(model);

			return convertView;
		}

	}

	private class ViewHolder {
		TextView txt_product, txt_price, txt_quants;
		CheckBox txt_check;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.productlist, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	public void DirectPay(View v) {

		Checkproductlist();

		int min = 1000;
		int max = 3000;

		Random r = new Random();

		i1 = r.nextInt(max - min + 1) + min;

		SharedPreferences sPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor se = sPrefs.edit();

		se.putString("billno", "" + i1);
		se.commit();

		Log.i("RESULT_OK", "Direct Payment");

		new Thread() {
			public void run() {
				try {

					for (int i = 0, k = 0, j = 0; i < myArrayList.size(); i++, k++, j++) {

						httpClient = new DefaultHttpClient();
						httpPost = new HttpPost(Ipaddress.URL);
						List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

						nameValuePair.add(new BasicNameValuePair("flag", "0"));

						nameValuePair.add(new BasicNameValuePair("paymenttype",
								"Direct"));
						nameValuePair.add(new BasicNameValuePair("status",
								"UnPaid"));

						nameValuePair
								.add(new BasicNameValuePair("email", email));

						nameValuePair.add(new BasicNameValuePair("name",
								myArrayList.get(i)));

						Log.i("", "" + myArrayList.get(i));
						nameValuePair.add(new BasicNameValuePair("quantity",
								myQuantsList.get(j)));

						Log.i("", "" + myQuantsList.get(j));
						nameValuePair.add(new BasicNameValuePair("price", ""
								+ as.get(k)));

						Log.i("", "" + as.get(k));

						nameValuePair.add(new BasicNameValuePair("Billno", ""
								+ i1));

						Log.i("Check", "working");

						httpPost.setEntity(new UrlEncodedFormEntity(
								nameValuePair));
						httpResponse = httpClient.execute(httpPost);

					}

				} catch (Exception e) {
					// handler.sendEmptyMessage(3);
					Log.d("Connection status error", "Connection" + e);
				}
			}
		}.start();

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(ownerphno, null, "Bill no: " + i1
				+ "Total Amount: " + sum, null, null);
		sms.sendTextMessage(ph, null,
				"Thanks for your shopping from our store receive you product based on bill no"
						+ i1 + "Total Amount: " + sum, null, null);

	}

	public void SelectProduct(View v) {

		Checkproductlist();

		Toast.makeText(this, "" + sum, Toast.LENGTH_LONG).show();
		Log.i("check", "working");

		PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal(sum),
				"USD", "Total Amount :");

		Intent intent = new Intent(this, PaymentActivity.class);

		intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
				CONFIG_ENVIRONMENT);
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
				CONFIG_RECEIVER_EMAIL);

		// It's important to repeat the clientId here so that the SDK has it if
		// Android restarts your
		// app midway through the payment UI flow.

		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID,
				CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_PAYER_ID,
				CONFIG_RECEIVER_EMAIL);
		intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

		startActivityForResult(intent, 0);

		Log.i("check", "working");
		
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			PaymentConfirmation confirm = data
					.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
			if (confirm != null) {
				try {

					int min = 1000;
					int max = 3000;

					Random r = new Random();

					i1 = r.nextInt(max - min + 1) + min;

					SharedPreferences sPrefs = PreferenceManager
							.getDefaultSharedPreferences(this);
					SharedPreferences.Editor se = sPrefs.edit();

					se.putString("billno", "" + i1);
					se.commit();

					Log.i("paymentExample", confirm.toJSONObject().toString(4));

					Log.i("RESULT_OK", "paypal");

					new Thread() {
						public void run() {
							try {
								System.out.println(myArrayList);
								
								for (int i = 0, k = 0, j = 0; i < myArrayList
										.size(); i++, k++, j++) {
									
									Log.i("Server", "working");
									httpClient = new DefaultHttpClient();
									httpPost = new HttpPost(Ipaddress.URL);
									List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

									nameValuePair.add(new BasicNameValuePair(
											"flag", "0"));

									nameValuePair.add(new BasicNameValuePair(
											"paymenttype", "PayPal"));
									
									nameValuePair.add(new BasicNameValuePair(
											"status", "Paid"));

									nameValuePair.add(new BasicNameValuePair(
											"email", email));

									nameValuePair.add(new BasicNameValuePair(
											"name", myArrayList.get(i)));
									Log.i("", "" + myArrayList.get(i));
									
									nameValuePair.add(new BasicNameValuePair(
											"quantity", myQuantsList.get(j)));

									Log.i("", "" + myQuantsList.get(j));
									nameValuePair.add(new BasicNameValuePair(
											"price", "" + as.get(k)));

									Log.i("", "" + as.get(k));

									nameValuePair.add(new BasicNameValuePair(
											"Billno", "" + i1));

									httpPost.setEntity(new UrlEncodedFormEntity(
											nameValuePair));
									httpResponse = httpClient.execute(httpPost);

								}

							} catch (Exception e) {
								// handler.sendEmptyMessage(3);
								Log.d("Connection status error", "Connection"
										+ e);
							}
						}
					}.start();

					SmsManager sms = SmsManager.getDefault();
					sms.sendTextMessage(ownerphno, null, "Bill no:" + i1, null,
							null);
					sms.sendTextMessage(ph, null,
							"Thanks for your shopping from our store receive you product based on bill no"
									+ i1 + "Visit Again", null, null);
					
					startActivity(new Intent(this, Login.class));
					
				} catch (JSONException e) {
					Log.e("paymentExample",
							"an extremely unlikely failure occurred: ", e);
				}
			}
		} else if (resultCode == Activity.RESULT_CANCELED) {
			int q=0;
			Log.i("RESULT_CANCELED", "cancel payment");
			Log.i("paymentExample", "The user canceled.");
			System.out.println(as.size());
			sum=0;
			
			
		} else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
			sum=0;
			Log.i("RESULT_PAYMENT_INVALID", "working");
			Log.i("paymentExample",
					"An invalid payment was submitted. Please see the docs.");
		}
	}

	@Override
	public void onDestroy() {
		stopService(new Intent(this, PayPalService.class));
		super.onDestroy();
	}

	public void Checkproductlist() {

		ArrayList<Model> productlist = crimeListAdapter.productlist;
		List<Double> newList = new ArrayList<Double>();
		for (int i = 0; i < productlist.size(); i++) {
			Model model = productlist.get(i);
			if (model.isSelected()) {

				as.add(model.getPrice());
				myArrayList.add(model.getName());
				myQuantsList.add(model.getQuants());
				Log.i("Checked product price", "" + as);

			}
		}
	
		for (String myInt : as) {
			newList.add(Double.valueOf(myInt));
			Log.i("selected product ", "" + newList);
		}

		for (int j = 0; j < newList.size(); j++) {
			System.out.println(newList.size());
			sum = sum + newList.get(j);
			Log.i("Total Amount1=", "" + sum);
		}

	}

}
