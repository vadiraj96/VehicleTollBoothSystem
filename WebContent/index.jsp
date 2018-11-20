<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
 
<html>
  <head>
  </head>
  <body>
    <br/><br/>
    <form method="post" name="frm" action="./Report">
      <table border="0" width="300" align="center" bgcolor="#e9f">
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Search User</h3></td></tr>
        <tr><td ><b>UserName</b></td>
          <td>: <input  type="text" name="username" id="username">
        </td></tr> 
        <tr><td ><b>From Date</b></td>
          <td>: <input  type="date" name="s_date" id="s_date">
        </td></tr> 
        <tr><td ><b>To Date</b></td>
          <td>: <input  type="date" name="e_date" id="e_date">
        </td></tr> 
         <tr><td ><b>Vehical Number</b></td>
          <td>: <input  type="text" name="vehical_number" id="vehical_number">
        </td></tr>        
				    <label for="vehicle_type">Vehical Type</label>
				    <div><input type="checkbox" name="vehical_type" value="2w"> Two Wheeler</div>
				   	<div><input type="checkbox" name="vehical_type" value="4w"> Four Wheeler</div>
				    <div><input type="checkbox" name="vehical_type" value="6w"> Six Wheeler</div>
				    <div> <input type="checkbox" name="vehical_type" value="8w"> Twelve Wheeler</div>
				   
        <tr><td colspan=2 align="center">
        <input  type="submit" name="submit" value="Search"></td></tr>
      </table>
    </form>
  </body>
</html>