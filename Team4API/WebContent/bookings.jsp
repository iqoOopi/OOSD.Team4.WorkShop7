<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page = "bookings_fn.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./bootstrap/css/grayscale.css" type= "text/css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.7.0/css/all.css"  >
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
</head>
<body onload="loadSbookings()" style="background-image:url('./img/demo-image-01.jpg')">
	<div class="mx-auto">
		<h1 class= "mx-auto text-uppercase">Bookings</h1>
	</div>
	<div >
		<button id = "btnLoadAgts" class="btn btn-primary btn-centered" onclick="loadBookingsk(); ">Load Booking History</button>
	</div>
	<div class="small" id="demo" style="display: block;height: 300px;overflow: auto;"></div>
	
	<div class="container" id = "fields">
		
		<button class="btn btn-primary" onclick="updatebookings();">Update</button>
	
	</div>
	
	<div class="mx-auto" id="selected">
		<a>Booking Date:</a><input type ="text" id="bkDate"></br>
		<a>Booking Id:</a><input type = "text" id="bkID"></br>
		<a>Booking No:</a><input type = "text" id="bkNo"></br>
		<a>Package Id:</a><input type = "text" id="pkgId"></br>
		<a>Traveler Count:</a><input type = "text" id= "travelerCount"></br>
		<a>Customer Id:</a><input type = "text" id= "customerId"></br>
		<a>Trip Type:</a><input type = "text" id = "tripType"></br>
	</div>
	<footer>
	</footer>
	
	<script>
	
	setTimeout(function(){
		var btnContainer = document.getElementById("fields");
		var tbl = document.getElementById("tblbookings"), rIndex;
		var selected = document.getElementById("selected");
		for (var i = 0;i<tbl.rows.length;i++)
			{
				
				tbl.rows[i].onclick = function()
				{
					if (selected.style.display=="none")
						{
							selected.style.display="block";
							btnContainer.style.display = "block";
							
						}
					console.log("should display");	
					rIndex = this.rowIndex;
					document.getElementById("bkID").value = this.cells[1].innerHTML;
					document.getElementById("pkgId").value = this.cells[4].innerHTML;
					//document.getElementById("agtmiddleInitial").value = this.cells[6].innerHTML;
					document.getElementById("travelerCount").value = this.cells[5].innerHTML;
					document.getElementById("bkNo").value = this.cells[2].innerHTML;
					document.getElementById("customerId").value = this.cells[3].innerHTML;
					document.getElementById("tripType").value = this.cells[6].innerHTML;
					document.getElementById("bkDate").value = this.cells[0].innerHTML;
					
				}
				
			}
		 
		}, 3000);
	</script>
	
</body>
</html>
