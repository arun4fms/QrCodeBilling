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
<script type="text/javascript">
function checkdata(){
	var val = (document.getElementById("bill").value).length;
	if(val != 0){
		
		return true;
	}
	else{
		alert("Enter Bill No");
		return false;
	}
	
}
</script>
<script>
	javascript: window.history.forward(1);
</script>


</head>
<form action="selectbill.jsp">
<P align=center style="COLOR: #00CCFF">
	<b>Customer Bill no</b> &nbsp;&nbsp; : &nbsp; <input type="text"
		name="user" id="bill" style='HEIGHT: 22px; WIDTH: 255px'>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type='submit' value='Check' class='button' onclick="return checkdata()"
	style='HEIGHT: 35px; WIDTH: 90px' />
	</P>
</form>
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
			 QueryString = "SELECT * from productdetails order by sno ";
			
			rs = statement.executeQuery(QueryString);
	%>
	<TABLE cellpadding="15" border="1" align="center">
		<TR>
			<TD><b>S.NO</b></TD>
			<TD><b>ProductName</b></TD>
			<TD><b>Quantity</b></TD>
			<TD><b>Price</b></TD>

			<%
				while (rs.next()) {
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
	<div id="text"></div>
		<font size="+3" color="red"></b> <%
 	out.println("Unable to connect to database."+ex.toString());
 	}
 %></font>
	</TABLE>
	</font>
</body>
</html>