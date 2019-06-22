n<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

<title>Travel Experts</title>
<script type="text/javascript">
function loadagents()
{
	// obtain references to the html selection elements CustProv and CustCountry
	var agentselect = document.getElementById("agenttable");
	
	// define a url for the REST service
	var url = "http://localhost:8080/Team4API/rest/agents/getallagents";
	var req = new XMLHttpRequest();
	var tempAgent;
	
	// AJAX call to get agent information from REST service
	req.open("get", url);
	req.setRequestHeader("Content-type", "application/json");
//	req.setRequestHeader("Content-type", "application/json");
//	req.setRequestHeader("Accept", "application/xml");
	req.setRequestHeader("Accept", "application/json");

	req.onreadystatechange = function()
	{
		var txt;
		if (req.readyState == 4)
		{
			// parse returned agent information into JSON array
		    json = JSON.parse(this.responseText);
			
			console.log(json.length);
			console.log(Object.keys(json[0]).length);
			console.log(Object.keys(json[0]));			
			
		    txt += "<table id='agenttable' border='1'>"
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

function saveagent()
{
	console.log("Starting saveagent()");
	// define a url for the REST service
	var url = "http://localhost:8080/Team4API/rest/agents/putagent";
	var req = new XMLHttpRequest();
	var tempAgent;
	
	// AJAX call to put agent information to the REST service
	req.open("put", url);
	req.setRequestHeader("Content-type", "application/json");
	req.setRequestHeader("Accept", "application/json")
	req.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status == 200) {
			//alert(this.responseText);
		}		
	};
	var txt ="";
    txt += '{"agentId" : 0, ';
    txt += '"agtFirstName" : "' + document.getElementById("agtFirstName").value +'", ';
    txt += '"agtMiddleInitial" : "' + document.getElementById("agtMiddleInitial").value +'", ';
    txt += '"agtLastName" : "' + document.getElementById("agtLastName").value +'", ';
    txt += '"agtBusPhone" : "' + document.getElementById("agtBusPhone").value +'", ';
    txt += '"agtEmail" : "' + document.getElementById("agtEmail").value +'", ';
    txt += '"agtPosition" : "' + document.getElementById("agtPosition").value +'", ';
    txt += '"agencyId" : ' + document.getElementById("agencyId").value +'}';
    console.log(txt);
    var jsonObject = JSON.parse(txt);
    console.log(jsonObject);
	req.send(txt);
}

function updateagent()
{
	console.log("Starting updateagent()");
	
	// define a url for the REST service
	var url = "http://localhost:8080/Team4API/rest/agents/postagent";
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
    txt += '{"agentId" : ' + document.getElementById("agentId").value +', ';
    txt += '"agtFirstName" : "' + document.getElementById("agtFirstName").value +'", ';
    txt += '"agtMiddleInitial" : "' + document.getElementById("agtMiddleInitial").value +'", ';
    txt += '"agtLastName" : "' + document.getElementById("agtLastName").value +'", ';
    txt += '"agtBusPhone" : "' + document.getElementById("agtBusPhone").value +'", ';
    txt += '"agtEmail" : "' + document.getElementById("agtEmail").value +'", ';
    txt += '"agtPosition" : "' + document.getElementById("agtPosition").value +'", ';
    txt += '"agencyId" : ' + document.getElementById("agencyId").value +'}';
    console.log(txt);
    var jsonObject = JSON.parse(txt);
    console.log(jsonObject);
	req.send(txt);
}

function deleteagent()
{
	console.log("Starting deleteagent()");
	var agentId = document.getElementById("agentId").value;
	// define a url for the REST service
	var url = "http://localhost:8080/Team4API/rest/agents/deleteagent/" + agentId ;
	var req = new XMLHttpRequest();
	var tempAgent;
	
	// AJAX call to put agent information to the REST service
	req.open("delete", url);
	req.setRequestHeader("Content-type", "application/json");
	req.onreadystatechange = function()
	{
		if (this.readyState == 4 && this.status == 200) {
			//alert(this.responseText);
		}		
	};

	req.send();
}

function displayagent()
{
	
	// define a url for the REST service
	var url = "http://localhost:8080/Team4API/rest/agents/getagent/4";
	var req = new XMLHttpRequest();
	var tempAgent;
	
	var table = document.getElementById('agenttable');
	var rIndex;
	
	
	// the code below works but perhaps a better solution would be to extract
	// the table row index, and request an agent based on the id to populate a
	// json object and loop through the population of the text fields.
	for (var i = 0; i < table.rows.length; i++)
	{
		table.rows[i].onclick=function(){
			rIndex = this.rowIndex;
			console.log(rIndex);
			document.getElementById("agentId").value = this.cells[0].innerHTML;
			document.getElementById("agtFirstName").value = this.cells[1].innerHTML;
			document.getElementById("agtMiddleInitial").value = this.cells[2].innerHTML;
			document.getElementById("agtLastName").value = this.cells[3].innerHTML;
			document.getElementById("agtBusPhone").value = this.cells[4].innerHTML;
			document.getElementById("agtEmail").value = this.cells[5].innerHTML;
			document.getElementById("agtPosition").value = this.cells[6].innerHTML;
			document.getElementById("agencyId").value = this.cells[7].innerHTML;
		}	
		
	}

	
	// AJAX call to get agent information from REST service
	req.open("get", url);
	req.onreadystatechange = function()
	{
		var txt;
		if (req.readyState == 4)
		{
			// parse returned province/state information into JSON array
		    json = JSON.parse(this.responseText);
			
			console.log(json.length);
			//console.log(Object.keys(json[0]).length);
			console.log(Object.keys(json));
			
			
		    txt += "<form><table>"
		    var keys = Object.keys(json);
		    console.log(keys);
		    console.log("json: ", json);

		      
		      for (j = 0; j < keys.length; j++)
		      {
		    	  txt += "<tr>";
		    	  //console.log(json);
		    	  console.log(keys[j]);
		    	  console.log(json[keys[j]]);
		    	  txt += "<td><label>" + keys[j] + ":</label></td>"
		          txt += "<td><input type='text' name='" + keys[j] + "' id='" + keys[j] + "' value='" + json[keys[j]] + "' ></td>";
		    	  txt += "</tr><br />";
		      }	      
	    }
	    txt += "</table></form>" 
	    document.getElementById("fields").innerHTML = txt;
	};
	req.send();
}
</script>
</head>
