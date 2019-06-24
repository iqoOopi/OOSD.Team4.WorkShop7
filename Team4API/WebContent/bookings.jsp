<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
function loadBookings()
{
	console.log("in bookings");
	var agentselect = $("#bookingid")[0];
	var req = new XMLHttpRequest();
	var tempAgent;
	
	// AJAX call to get province/state information from REST service
	req.open("get", url);
	req.onreadystatechange = function()
	{
		var txt;
		if (req.readyState == 4)
		{
			// parse returned province/state information into JSON array
		    json = JSON.parse(this.responseText);
			
			console.log(json.length);
			console.log(Object.keys(json[0]).length);
			console.log(Object.keys(json[0]));			
			
		    txt += "<table class='table table-striped table-dark' id='tblbookings' border='1'>"
		    var keys = Object.keys(json[0]);
		    console.log(keys);
		    console.log("json: ", json);
		    
		    for (i = 0; i < json.length; i++)
		    {
		      txt += "<tr>";
		      for (j = 0; j < keys.length; j++)
		      {
		    	  console.log(json[j]);		    	  
		          txt += "<td>" + (json[i])[keys[j]] + "</td>";
		      }
		      txt += "</tr>";		      
		    }
		    txt += "</table>" 
		    document.getElementById("demo").innerHTML = txt;
		}
	};
	req.send();
	 
}

function showbookings(bookingid))
{
	var url = "http://localhost:8080/Team4API/rest/bookings/getbooking/" + agentid;
	$.get(url, function(json){
		var mystring = json.agentId + "|"
		+ json.agtFirstName + "|"
		+ json.agtLastName + "|"
		+ json.agtBusPhone + "|"
		+ json.agtEmail;
		$("#agentdetail").text(mystring);
		}, "json");
}</script>
</head>
<body>
	<h1>Bookings</h1>
	<div>		
		<div class="container">
			///bookings tables go in here
		</div>
	</div>
</body>
</html>