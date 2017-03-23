<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="qrservlet">
		<%
			String pname = request.getParameter("pname");
			String pdate = request.getParameter("pdate");
			String pquants = request.getParameter("pquants");
			String pprice = request.getParameter("pprice");

			String result = pname + ":" + pdate + ":" + pquants + ":"
					+ pprice;
		%>
		<input type="text" name="qrtext" value="<%=result%>" />
		<p></p>
		<input type="submit" value="Generate qr code" />
	</form>
</body>
</html>