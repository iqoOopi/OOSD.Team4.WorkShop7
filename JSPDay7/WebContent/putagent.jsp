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
		var url = "http://localhost:8080/JSPDay7/rest/agents/putagent";
		$.ajax({
			url:url,
			method:"put",
			data:{
		        "agentId": 0,
		        "agencyId": 1,
		        "agtBusPhone": "",
		        "agtEmail": "",
		        "agtFirstName": document.getElementById("agtFirstName").value,
		        "agtLastName": "",
		        "agtPosition": "Senior Agent"
		    }
		});
	}
</script>
</head>
<body>
	<form>
		FirstName:<input type="text" id="agtFirstName" /><br />
		<button onclick="saveagent()">Save</button>
	</form>
</body>
</html>