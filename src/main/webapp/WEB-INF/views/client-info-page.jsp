<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Count</title>
<style type="text/css">
p {
	font-size: 20px;
	font-weight: 600;
	margin: 7px;
}

h2 {
	font-weight: 20px;
	margin-top: 25px;
}

div {
	border: 3px solid black;
	border-radius: 5px;
	width: 400px;
	margin: 0 auto;
	margin-top: 30px;
}
</style>
</head>
<body bgcolor="#f9a427">
	<h2 align="center">*** Welcome to your count ***</h2>
	<%
		String name = (String) request.getAttribute("name");
	String count_num = (String) request.getAttribute("count_num");
	String amount = (String) request.getAttribute("amount");
	String currency = (String) request.getAttribute("currency");
	String card_num = (String) request.getAttribute("card_num");
	%>
	<div align="center">
		<p align="center">
			Name :
			<%=name%>
		</p>
		<br>
		<p align="center">
			Count Number :
			<%=count_num%>
		</p>
		<br>
		<p align="center">
			Amount :
			<%=amount%>
		</p>
		<br>
		<p align="center">
			Card Number :
			<%=card_num%>
		</p>
		<br>
	</div>
</body>
</html>