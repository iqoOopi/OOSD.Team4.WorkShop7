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


</head>
<body>
	<form>
		FirstName:<input type="text" id="agtFirstName" /><br />
		<button onclick="saveagent()">Save</button>
	</form>
	
		<h1>Agent Details 9</h1>
	<select id="agentid" onchange="showagent(this.value)">
		<option value="">Select an agent to display</option>
	</select>
	<div id="agentdetail"></div>
	<script>
		$(document).ready(function(){ loadagents(); });
	</script>
	
</body>
</html>