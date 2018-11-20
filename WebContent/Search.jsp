<%@ page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
<style type="text/css">
body {
    font-family: 'Open Sans', sans-serif;
    background: -webkit-radial-gradient(closest-corner, rgba(16, 47, 70, 0) 60%, rgba(16, 47, 70, 0.26)), -webkit-linear-gradient(108deg, #0a4867, #2196F3 90%);
    background: -moz-radial-gradient(closest-corner, rgba(16, 47, 70, 0) 60%, rgba(16, 47, 70, 0.26)), -moz-linear-gradient(108deg, #0a4867, #2196F3 90%);
    background: -o-radial-gradient(closest-corner, rgba(16, 47, 70, 0) 60%, rgba(16, 47, 70, 0.26)), -o-linear-gradient(108deg, #0a4867, #2196F3 90%);
    background: -ms-radial-gradient(closest-corner, rgba(16, 47, 70, 0) 60%, rgba(16, 47, 70, 0.26)), -ms-linear-gradient(108deg, #0a4867, #2196F3 90%);
    background-attachment: fixed;	 
}</style>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

 <script>
  function goBack() {
       window.history.back();
   }
 </script>
</head>
<body>
<div class="w3-row" >
<div class=" w3-col l12 w3-bar w3-border w3-light-grey"  onclick=goBack();>
<a class="w3-bar-item w3-button w3-green">Go To Report Generation Page</a>
<a href="welcome.html" class="w3-bar-item w3-button w3-green w3-right">Go To Home</a>
</div>
    </div>
 
	<div class="w3-row-padding w3-panel"> 
	
		<div class="w3-center w3-col l12 w3-card" style="background-color: teal"><h2 style="  color: #fff;">Vehicle Record</h2></div>
			<table class="w3-table-all w3-small w3-center  w3-col l12 w3-card-4">
				<tr style="background-color: lightgrey;">
					<td><b>Serial Number</b></td>
					<td><b>User name</b></td>
					<td><b>Vehicle Type</b></td>
					<td><b>Charge</b></td>
					<td><b>Vehicle Number</b></td>
					<td><b>Model</b></td>
					<td><b>capacity</b></td>
					<td><b>Direction</b></td>
					<td><b>Date</b></td>
				</tr>
				<%
                int count = 0;
                String color = "#F9EBB3";
                if (request.getAttribute("piList") != null) {
                    ArrayList al = (ArrayList) request.getAttribute("piList");
                    //System.out.println(al);
                    Iterator itr = al.iterator();
                    while (itr.hasNext()) {
 
                        if ((count % 2) == 0) {
                            color = "#eeffee";
                        }
                        count++;
                        ArrayList pList = (ArrayList) itr.next();
            %>
				<tr
					<%-- style="background-color:<%=color%>;" --%> class="w3-hoverable">
					<td><%=count%></td>
					<td><%=pList.get(0)%></td>
					<td><%=pList.get(1)%></td>
					<td><%=pList.get(2)%></td>
					<td><%=pList.get(3)%></td>
					<td><%=pList.get(4)%></td>
					<td><%=pList.get(5)%></td>
					<td><%=pList.get(6)%></td>
					<td><%=pList.get(7)%></td>
				</tr>
				<%
                    }
                }
                if (count == 0) {
            %>
				<tr>
					<td colspan=9 align="center" style="background-color: #eeffee"><b>No
							Record Found..</b></td>
				</tr>
				<%  } %>
			</table>
		</div>
		

		
</body>
</html>