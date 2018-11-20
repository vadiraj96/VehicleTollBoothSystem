<html>
	<body>
		<center>	
			<h1>WELCOME&nbsp;TO&nbsp;VEHICLE&nbsp;REGISTERATION&nbsp;</h1>
			
			<form action="./VehicleRegister" method="POST" enctype="multipart/form-data">
			
			<h2>Upload&nbsp;Data&nbsp;from&nbsp;Excel&nbsp;</h2>
			<table>
				<tr>
					<td><b>Choose&nbsp;A&nbsp;File</b></td>
					<td><input type="file" name="fName" multiple></td>
				</tr>
				</table>	
				<input type="submit" name="FileRegister">
			</form>	
			
			<form action="./VehicleRegister" method="GET">
			
			<h2> Please&nbsp;Enter&nbsp;All&nbsp;Fields&nbsp;Properly</h2>
				<table width="500" cellpadding="5" cellspacing="5">
					<tr>
						<td><b>Vehicle&nbsp;Type </b></td>
						<td><select id="vehicle" name="vehicleType" onclick="myFunction()">
								<option value="0">Select&nbsp;Vehicle&nbsp;Type</option>
								<option value="2w"> 2&nbsp;Wheeler</option>
								<option value="4w">4&nbsp;Wheeler</option>
								<option value="6w">6&nbsp;Wheeler</option>
								<option value="8w">8&nbsp;Wheeler</option>
						 </select></td>
					</tr>	
					<tr>
						<td><b >Toll-money&nbsp;</b></td>
						<td><input  id="money" name="Money"></td>
					</tr>	
					<tr>
						<td><b>Vehicle&nbsp;No</b></td>
						<td><input  name="VehicleNo"></td>
					</tr>	
					<tr>
						<td><b>Vehicle&nbsp;Model</b></td>
						<td><input  name="VehicleModel"></td>
					</tr>	
					<tr>
						<td><b>Vehicle&nbsp;Capacity</b></td>
						<td><input  name="VehicleCapacity"></td>
					</tr>	
					<tr>
						<td><b>Entry&nbsp;Type</b></td>
						<td><input  name="EntryType"></td>
					</tr>
				</table>
				<input type="submit" name="Register">
			</form>		
		</center>		
		<script>
			function myFunction() {
				var x = document.getElementById("vehicle").selectedIndex;
				var val=document.getElementsByTagName("option")[x].value;
				//alert(val);
				if(val=='0')
					document.getElementById("money").value= 0;
				else if(val=='2w')
					document.getElementById("money").value= 10;
				else if(val=='4w')
					document.getElementById("money").value= 20;
				else if(val=='6w')
					document.getElementById("money").value= 30;
				else if(val=='8w')
					document.getElementById("money").value= 40;
			}
		</script>
	</body>
</html>