package com.child.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateDB {

	Connection conn = ConnectionManager.getConnection();
	int l;

	public void updateLoc(String productname, String productquantity,
			String productprice, String productbillno, String email, String paymenttype, String status) {
		try {
			Statement st = conn.createStatement();
			st.execute("INSERT INTO productdetails(productname,productquantity,productprice,Billno,Emailid,PaymentType,Status) values('"
					+ productname + "','"

					+ productquantity + "','" + productprice

					+ "','" + productbillno

					+ "','" + email
					
					+ "','" + paymenttype
					
					+ "','" + status

					+ "')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean login(String email, String phone) {
		boolean success = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT customerphone FROM registration WHERE customeremail = '"
							+ email + "'");
			while (rs.next()) {
				if (rs.getString("customerphone").equals(phone)) {
					success = true;
				} else {
					success = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

	public void Registration(String customerphone, String customeremail,
			String customername) {
		try {
			Statement st = conn.createStatement();
			st.execute("INSERT INTO registration(customerphone,customeremail,customername) values('"
					+ customerphone
					+ "','"
					+ customeremail
					+ "','"
					+ customername + "')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
