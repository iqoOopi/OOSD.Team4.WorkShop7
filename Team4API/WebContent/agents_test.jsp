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
<body style="background-image:url('./img/bg-masthead.jpg')">
<div>
<div class="mx-auto">
	<h1 class= "mx-auto my-0 text-uppercase">Agent Test Page</h1>
</div>
<div >
	<div >
		<button class="btn btn-primary btn-centered" onclick="loadagents();">Load Agents</button>
	</div>
	<div id="demo">
	<table id= "agenttable"></table>
	</div>
	
	<div class="container" id = "fields">
		<button class="btn btn-primary" onclick="displayagent();">Display Agent</button>
	
		<button class="btn btn-primary" onclick="saveagent();">Add</button>
		<button class="btn btn-primary" onclick="updateagent();">Update</button>
		<button class="btn btn-primary" onclick="deleteagent();">Delete</button>
	
	</div>
	
</div>
</div>
</body>
</html>