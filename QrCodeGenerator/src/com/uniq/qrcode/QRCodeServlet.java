package com.uniq.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QRCodeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String qrtext = request.getParameter("qrtext");

		String pname=request.getParameter("pname");
		String pdate=request.getParameter("pdate");
		String pquants=request.getParameter("pquants");
		String pprice=request.getParameter("pprice");
		
		String result = pname+";"+pdate+";"+pquants+";"+pprice;
		
		ByteArrayOutputStream out = QRCode.from(qrtext).to(
				ImageType.PNG).stream();
		
		response.setContentType("image/png");
		response.setContentLength(out.size());
		
		OutputStream outStream = response.getOutputStream();

		outStream.write(out.toByteArray());

		outStream.flush();
		outStream.close();
	}
}
