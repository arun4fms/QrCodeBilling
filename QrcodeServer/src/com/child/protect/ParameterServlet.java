package com.child.protect;

import com.child.dao.UpdateDB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParameterServlet
 */

public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UpdateDB updb = new UpdateDB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParameterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag").toString();

		int flag_value = Integer.parseInt(flag);

		if (flag_value == 0) {
			// for co-ordinates
			System.out.println(request.getParameter("name").toString());
			String productname = request.getParameter("name").toString();

			System.out.println(request.getParameter("quantity").toString());
			String productquantity = request.getParameter("quantity")
					.toString();
			System.out.println(request.getParameter("price").toString());
			String productprice = request.getParameter("price").toString();
			System.out.println(request.getParameter("Billno").toString());
			String productbillno = request.getParameter("Billno").toString();
			System.out.println(request.getParameter("email").toString());
			String useremail = request.getParameter("email").toString();
			String paymenttype = request.getParameter("paymenttype").toString();
			String status = request.getParameter("status").toString();

			updb.updateLoc(productname, productquantity, productprice,
					productbillno, useremail,paymenttype,status);
		} else if (flag_value == 2) {

			System.out.println(request.getParameter("phone").toString());
			String customerphone = request.getParameter("phone").toString();
			System.out.println(request.getParameter("email").toString());
			String customeremail = request.getParameter("email").toString();
			System.out.println(request.getParameter("custname").toString());
			String customerpassword = request.getParameter("custname").toString();

			updb.Registration(customerphone, customeremail, customerpassword);
		} else if (flag_value == 3) {
			if (updb.login(request.getParameter("Email").toString(), request
					.getParameter("Phoneno").toString())) {
				response.getWriter().write("yes");
			} else {
				response.getWriter().write("no");
			}

		}
	}
}
