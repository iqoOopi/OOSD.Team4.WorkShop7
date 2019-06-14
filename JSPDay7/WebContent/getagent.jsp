<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agent Details</title>
	<script src="jquery.js"></script>
	<script>
		function loadagents()
		{
			console.log("in loadagents");
			var agentselect = $("#agentid")[0];
			//var agentselect = document.getElementById("agentid");
			var url = "http://localhost:8080/JSPDay7/rest/agents/getallagents";
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
			var url = "http://localhost:8080/JSPDay7/rest/agents/getagent/" + agentid;
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