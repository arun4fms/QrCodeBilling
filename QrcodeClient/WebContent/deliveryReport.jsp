<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.uniq.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String bill=request.getParameter("bill");
if(!((bill.isEmpty())||(bill==null)))
{
	Connection con=ConnectionManager.getConnection();
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from productdetails where Billno='"+bill+"'");
	out.println("<table><tr><td>Product Name</td><td>Product Quntitiy</td><td>Product Price</td></tr>");
	while(rs.next())
	{
		%>
		<tr>
		<td><%=rs.getString("") %></td>
		<td><%=rs.getString("") %></td>
		<td><%=rs.getString("") %></td>
		</tr>
		
		<%
	}
}
else
{
	
}
%>
</body>
</html>