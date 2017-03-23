<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%> 

<html>
<head>
<title>Products</title>
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
		<span style="color: lightseagreen;">Product Information</span>
	</h2>
	<%
		try {
			int sum = 0;
			String connectionURL = "jdbc:mysql://localhost:3306/qrcode";
			Connection connection = null;
			Statement statement = null;
			ResultSet rs = null;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root",
					"root");
			
			statement = connection.createStatement();
			String QueryString = "SELECT * from productdetails order by sno";
			rs = statement.executeQuery(QueryString);
	%>
	<TABLE cellpadding="15" border="1" align="center">
		<TR>
			<TD><b>S.NO</b></TD>
			<TD><b>Product Name</b></TD>
			<TD><b>Product Quantity</b></TD>
			<TD><b>Product Price</b></TD>
			<TD><b>Product BillNo</b></TD>
			<TD><b>Payment Type</b></TD>
			<TD><b>Paid Status</b></TD>

			<%
				while (rs.next()) {
			%>
		
		<TR>
			<TD><%=rs.getInt(1)%></TD>
			<TD><%=rs.getString(2)%></TD>
			<TD><%=rs.getString(3)%></TD>
			<TD><%=rs.getString(4)%></TD>
			<TD><%=rs.getString(5)%></TD>
			<TD><%=rs.getString(7)%></TD>
			<TD><%=rs.getString(8)%></TD>
		</TR>

		<%
			}
				rs = statement
						.executeQuery("SELECT SUM(productprice) FROM productdetails");
				while (rs.next()) {
					int c = rs.getInt(1);
					sum = sum + c;
				}
		%>

		<%-- <TABLE cellpadding="15" border="2" align="center" >
			<TR>
				<TD><b>Result</b></TD>

				<TD><%=sum%></TD>
			</TR>

		</TABLE> --%>
		<%
			rs.close();
				statement.close();
				connection.close();
			} catch (Exception ex) {
		%>

		<font size="+3" color="red"></b> <%
 	out.println("Unable to connect to database.");
 	}
 %></font>
	</TABLE>
	</font>
</body>
</html>