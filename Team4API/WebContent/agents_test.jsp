<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header_test.jsp"/>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="./bootstrap/css/grayscale.css" type= "text/css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.7.0/css/all.css"  >
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	
</head>
<body onload="loadSagents()" style="background-image:url('./img/bg-masthead.jpg')">

	<div class="mx-auto">
		<h1 class= "mx-auto text-uppercase">Agent</h1>
	</div>

	<div >
		<button id = "btnLoadAgts" class="btn btn-primary btn-centered" onclick="loadagents(); ">Load Agents</button>
	</div>
	<div id="demo"></div>
	
	<div class="container" id = "fields">
		
		<button class="btn btn-primary" onclick="updateagent();">Update</button>
	
	</div>
	<div class="mx-auto" id = "selected">
	
		<a>Agent Id:</a><input type="text" id = "agentId"/> </br>
		<a>First Name:</a><input type="text" id = "agtFirstName"/> </br>
		<a>Middle Initial:</a><input type="text" id = "agtMiddleInitial"/> </br>
		<a>Last Name:</a><input type ="text" id = "agtLastName"/> </br>
		<a>Bus Phone No:</a><input type = "text" id = "agtBusPhone"/> </br>
		<a>Email:</a><input type = "text" id = "agtEmail"/> </br>
		<a>Position:</a><input type = "text" id = "agtPosition"/> </br>
		<a>Agency Id:</a><input type = "text" id = "agencyId"/> </br>
		
	</div>
	<footer>
	
	
	</footer>
	<script>
	
	
	
	
	setTimeout(function(){
		var btnContainer = document.getElementById("fields");
		var tbl = document.getElementById("agenttable"), rIndex;
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
					
					rIndex = this.rowIndex;
					document.getElementById("agentId").value = this.cells[1].innerHTML;
					document.getElementById("agtFirstName").value = this.cells[4].innerHTML;
					//document.getElementById("agtmiddleInitial").value = this.cells[6].innerHTML;
					document.getElementById("agtLastName").value = this.cells[5].innerHTML;
					document.getElementById("agtBusPhone").value = this.cells[2].innerHTML;
					document.getElementById("agtEmail").value = this.cells[3].innerHTML;
					document.getElementById("agtPosition").value = this.cells[6].innerHTML;
					document.getElementById("agencyId").value = this.cells[0].innerHTML;
					
				}
				
			}
		 
		}, 2000);
	
				
	</script>	
	
	

</body>
</html>