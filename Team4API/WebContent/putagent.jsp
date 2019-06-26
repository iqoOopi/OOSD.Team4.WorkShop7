<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert new agent</title>
<script src="jquery.js"></script>
<script>
	function saveagent()
	{
		//test
		var url = 'http://localhost:8080/Team4API/rest/agents/putagent';
		var firstName=document.getElementById("agtFirstName").value;
		console.log("im here to debug");
		console.log(firstName);
		$.ajax({
			url:url,
			type:"put",
            contentType : "application/json",
            dataType:"text",

			data:JSON.stringify({
		        "agentId": 0,
		        "agencyId": 1,
		        "agtBusPhone": "",
		        "agtEmail": "",
		        "agtFirstName": firstName,
		        "agtLastName": "",
		        "agtPosition": "Senior Agent"
		    }),
            error : function(data, status, er) {
            	console.log(data);
                alert("Load Data: " + data + ", Estatus: " + status + ", Error: "+ er);
            }
		});
	}
</script>

<script>
		function loadagents()
		{
			console.log("in loadagents");
			var agentselect = $("#agentid")[0];
			//var agentselect = document.getElementById("agentid");
			var url = "http://localhost:8080/Team4API/rest/agents/getallagents";
			$.get(url, function(json){
				for (i=0; i<json.length; i++)
				{
					console.log("in loop - agentfirstname=" + json[i].agtFirstName);
					var option = document.createElement("option");
					option.text = json[i].agtFirstName + " " + json[i].agtLastName;
					option.value = json[i].agentId;
					agentselect.add(option);
				}
				
			}, "json");
		}
		
		function showagent(agentid)
		{
			var url = "http://localhost:8080/Team4API/rest/agents/getagent/" + agentid;
			$.get(url, function(json){
				var mystring = json.agentId + "|"
				+ json.agtFirstName + "|"
				+ json.agtLastName + "|"
				+ json.agtBusPhone + "|"
				+ json.agtEmail;
				$("#agentdetail").text(mystring);
				}, "json");
		}
	</script>
	<link rel="stylesheet" href="./bootstrap/css/grayscale.css" type= "text/css">
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.7.0/css/all.css"  >
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

</head>
<body style="background-image:url('./img/demo-image-01.jpg')">
	<div class="mx-auto">
		<h1 class= "heading mx-auto my-0 text-uppercase">Agent Details </h1>
	</div>
	<div class="container">
		<form>
		<div class="head2">
			<a class="fname">FirstName:</a> 
			<input type="text" id="agtFirstName" /><br />
		</div>			
			<button class="btn btn-primary btnsave" onclick="saveagent()">Save</button>
		</form>	
		<select id="agentid" onchange="showagent(this.value)">
			<option value="">Select an agent to display</option>
		</select>
		<div id="agentdetail">
		</div>
	</div>
	
	<script>
		$(document).ready(function(){ loadagents(); });
	</script>
	
</body>
</html>