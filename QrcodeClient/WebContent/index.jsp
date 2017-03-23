 <HTML>
<HEAD>
<TITLE>HOME</TITLE>

<script type="text/javascript">
 function validate()
 {
  var username=document.form1.user.value;
  var password=document.form1.pass.value;
  if(username=="")
  {
   document.getElementById('lab1').innerHTML="<small><font color='red'>Enter Username!</font></small>";
   return false;
  }
  if(password=="")
  {
   document.getElementById('lab1').innerHTML="<small><font color='red'>Enter Password!</font></small>";
   return false;
  }
 
 return true;
 }
 

</script>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>

<style>
   .field_set
{
 border-color:#00CCFF;
}
</style>
<link href="default.css" rel="stylesheet" type="text/css" />

</HEAD>


<BODY  background="images/1.png" vlink='black' link='blue' onload='noBack();'
    onpageshow='if (event.persisted) noBack();' onunload=''>
    
    <form  method="post" name="form1" action="LoginChecker" onSubmit="return validate()">
         
        <font face="cursive" size=30 color='#00CCFF'><p align ="center" >Qr Code System</p></font>
<hr color=#00CCFF size=5><p></p><p></p><p></p>
<p></p><p></p><p></p><p></p>
<table align=center color='#00CCFF'>
<tr><td></td>
    <td><fieldset class='field_set'>
<legend><font  face="cursive" size=3 color=#00CCFF>Login window</font></legend>

<P align=center style="COLOR: #00CCFF"><b>User Name</b> 
      &nbsp;&nbsp; : &nbsp;

      <input type="text" name="user" style='HEIGHT: 22px; WIDTH: 255px' ></P>
       
<P align=center style="COLOR: #00CCFF"> 
    <b>Password</b>&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;:&nbsp;&nbsp;
    <input type="password"name="pass" style="HEIGHT: 22px; WIDTH: 255px"></P>
     
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
<% String result=request.getParameter("result");
if(result!=null) 
{ 
    if(result.equals("invalid"))
     { 
          out.println("<font color='white'>Invalid user");
     } 
    else 
    { 
        out.println("Valid user");
    } 
} %>
<label id="lab1"></label>
<input type='submit' value='Sign In'  class='button' style='HEIGHT: 35px; WIDTH: 90px'/>
<input type='reset' value='Reset' class='button' style='HEIGHT: 35px; WIDTH: 90px' />

<center>
</fieldset></td></tr></table>


</form>
</BODY>
</HTML>



