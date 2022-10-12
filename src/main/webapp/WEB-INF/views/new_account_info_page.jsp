<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
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
	width: 250px;
	margin: 0 auto;
	margin-top: 30px;
}

button {
	background-color: transparent;
	position: absolute;
	left: 47%;
	border-radius: 5px;
	height: 50px;
	width: 75px;
	margin-top: 35px; font-size : 15px;
	font-weight: 700;
	border-width: 3px;
	border-color: #000;
	font-size: 15px;
}
</style>
</head>
<body bgcolor="#f9a427">
<h2 align="center">*** Welcome to your account ***</h2>
	<%
		String name = (String) request.getAttribute("name");
		String login = (String) request.getAttribute("login");
		String password = (String) request.getAttribute("password");
	%>
	<div align="center">
	<p align="center">Name : <%=name%></p> <br>
	<p align="center">Login : <%=login%></p> <br>
	<p align="center">Password : <%=password%></p> <br></div>
	<form action="sign-in-page">
		<button type="submit">Sign in</button>
	</form>
</body>
</html>