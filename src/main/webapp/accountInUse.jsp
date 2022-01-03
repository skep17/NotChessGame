<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>

<form action="createAccount" method="post">
	<h1>The Name <%= request.getAttribute("userName") %>  is Already In Use</h1> <br> <br>
	<h4>Please enter another name and password.</h4>  <br> <br>
	<h4>User Name:</h4>
	<input type="text" name="usernameInput"> <br> <br>	
	<h4>Password:</h4>

	<input type="text" name="passwordInput">
	<input type="submit" value="Login"> <br> <br>
	
	
</form>


</body>
</html>