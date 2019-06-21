<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header_test.jsp"/>

<body>
	<p1>Agent Test Page</p1>
	<div class="container">
	<button onclick="loadagents();">Load Agents</button>
	</div>
	<div id="demo">
	</div>
	<div class="container" id = "fields">
		<button onclick="displayagent();">Display Agent</button>
	</div>
	<div>
		<button onclick="saveagent();">Add</button>
		<button onclick="updateagent();">Update</button>
		<button onclick="deleteagent();">Delete</button>
	
	</div>
</body>
</html>