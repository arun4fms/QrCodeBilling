<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%> 
<% boolean flag=false; %>

<html>
<head>
<title>Select Customer</title>
<style>
body {
	background-position: center;
	background-position: inherit;
}
</style>

<script>
	javascript: window.history.forward(1);
</script>


</head>

<body border='0'>
	<h2 align="center">
		<span style="color: lightseagreen;">Customer Information</span>
	</h2>
	<%
		try {

			String connectionURL = "jdbc:mysql://localhost:3306/qrcode";
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");

			statement = connection.createStatement();String QueryString = null;
			
			 String id = request.getParameter("user").toString();  
			int billno = Integer.parseInt(id);
			//request.setAttribute("billno", id);
			 QueryString = "SELECT * from productdetails where Billno="+billno+" order by sno";
		
			rs = statement.executeQuery(QueryString);
			if(rs.first()){
				if(rs.getString(8).equals("Unpaid")){
					%>
					<div id="text" align="center">
					<font size="+3" color="red">
					<%	
					out.print(rs.getString(8)+": have to pay by "+rs.getString(7));
					%>
					</font>
					
					<form name="pay" action="payServlet">
					<input type='submit' value='Pay' class='button' 
					style='HEIGHT: 35px; WIDTH: 90px' />
					<%
					
					request.getSession().setAttribute("billno", id);
					%>
					</form>
					
					<%
				}
				else{
					
				
			%>
			
			<font size="+3" color="green">
			<%	
			out.print(rs.getString(8)+"    by      "+rs.getString(7));
			}
			}
			rs.close();
			rs = statement.executeQuery(QueryString);
	%>
	</font>
	</div>
	<TABLE cellpadding="15" border="1" align="center">
		<TR>
			<TD><b>S.NO</b></TD>
			<TD><b>ProductName</b></TD>
			<TD><b>Quantity</b></TD>
			<TD><b>Price</b></TD>

			<% while (rs.next()) {

			
			%>
		
		<TR>
			<TD><%=rs.getInt(1)%></TD>
			<TD><%=rs.getString(2)%></TD>
			<TD><%=rs.getString(3)%></TD>
			<TD><%=rs.getString(4)%></TD>
		</TR>
		<%
			}
		%>
		<%
			rs.close();
				statement.close();
				connection.close();
			} catch (Exception ex) {
		%>
	<div id="text">
		<font size="+3" color="red"></b> <%
 	out.println("Unable to connect to database."+ex.toString());
 	}
 %></font>
 </div>
	</TABLE>
	</font>
	<form action="selectcustomer.jsp">
<P align=center style="COLOR: #00CCFF">
	
<input type='submit' value='back' class='button' 
	style='HEIGHT: 35px; WIDTH: 90px' />
	</P>
	</form>
</body>
</html>