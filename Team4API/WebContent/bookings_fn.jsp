<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel= "stylesheet" href= "bootstrap/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function loadBookings()
{
	console.log("in bookings");
	var url = "http://localhost:8080/Team4API/rest/bookings/getallbookings";
	var req = new XMLHttpRequest();
	var tbl = document.getElementById("demo");
	
	if (tbl.style.display=="none")
		{
			tbl.style.display="block";
		}
	
		
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
			
		    txt += "<table class='table table-striped table-dark' id='tblbookings' border='1'>";
		    txt += "<tr><th>Booking Date</th> <th>Booking Id</th><th>Booking No</th><th>Customer Id</th><th>Package Id</th><th>Traveler Count</th><th> Trip Type</th></tr>";
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

function loadSbookings()
{
	var selectedt = document.getElementById("selected");
	selectedt.style.display = "none";
	var tbl = document.getElementById("demo");
	tbl.style.display = "none";
	
	var tblContainer = document.getElementById("demo");
		
		// obtain references to the html selection elements CustProv and CustCountry
		
		var bookingselect = document.getElementById("bookingstable");
		if (bookingselect == null)
			{
			var url = "http://localhost:8080/Team4API/rest/bookings/getallbookingss";
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
					
				    txt += "<table class='table table-striped table-dark' id='tblbookings' border='1' cursor:'Pointer'>"
				    	txt += "<tr><th>Booking Date</th> <th>Booking Id</th><th>Booking No</th><th>Customer Id</th><th>Package Id</th><th>Traveler Count</th><th> Trip Type</th></tr>";
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
				    tblContainer.innerHTML = txt;
				}
			};
			req.send();
			}else ///in order to refresh table
				{
				var url = "http://localhost:8080/Team4API/rest/bookings/getallbookings";
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
						
					    //txt += "<table class='table table-striped table-dark' id='agenttable' border='1' cursor:'Pointer'>"
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
					    
					    document.getElementById("tblbookings").innerHTML = txt;
					}
				};
				req.send();
					
			}		
						
}

function updatebookings()
{


	var url = "http://localhost:8080/Team4API/rest/bookings/postbooking";
	var req = new XMLHttpRequest();

	
	// AJAX call to post agent information to the REST service
	req.open("post", url);
	req.setRequestHeader("Content-type", "application/json");
	req.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status == 200) {
			//alert(this.responseText);
		}		
	};
	var txt ='';
    txt += '{"bookingId" : ' + document.getElementById("bkID").value +', ';
    txt += '"bookingDate" : "' + document.getElementById("bkDate").value +'", ';
    txt += '"customerId" : "' + document.getElementById("customerId").value +'", ';
    txt += '"packageId" : "' + document.getElementById("pkgId").value +'", ';
    txt += '"travelerCount" : "' + document.getElementById("travelerCount").value +'", ';
    txt += '"tripTypeId" : "' + document.getElementById("tripType").value +'", ';
    txt += '"bookingNo" : "' + document.getElementById("bkNo").value + '"'+'}';
   
    console.log(txt);
    var jsonObject = JSON.parse(txt);
    console.log(jsonObject);
    
    //clear table and refresh it
    req.send(txt);
    location.reload();
    
    
}
	

</script>
</head>
<body>

</body>
</html>