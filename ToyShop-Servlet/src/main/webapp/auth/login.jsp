<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<tf:template title="ToyShop - Sign in">
	<form method="POST" action="j_security_check">
		<p>Enter your login:</p>
		<p>Username: <input type="text" class="form-control" name="j_username" /> </p>
		<p>Password: <input type="password" class="form-control" name="j_password" /> </p>
		
		<input type="submit" class="btn btn-success" value="Sign in" />
	</form>
</tf:template>
