<html>
<head>
<link rel="stylesheet" type="text/css" href="sdmenu/sdmenu.css" />
	<script type="text/javascript" src="sdmenu/sdmenu.js">
	</script>
	<script type="text/javascript">
	
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
		myMenu.speed = 3; 
                myMenu.expandAll();
                myMenu.markCurrent=true;
	};
	
	</script>
        <style>
            a
            {
             text-decoration: none;   
            }
        </style>
</head>
<body>
<div style="float: left" id="my_menu" class="sdmenu">
      <div>
        <span>Product Details</span>
        <a href='productinformation.jsp' target=f2 >Product Customer ordered</a>
         <a href='selectcustomer.jsp' target=f2 >Select Individual Customer</a>
      
      </div>
      <div>   
       <div>
        <span>Logout Here</span>
       <a href="index.jsp" target="_parent" >LogOut</a>
      </div>
    </div>
    
</body>
</html>