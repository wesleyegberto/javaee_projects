<%@ tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="false" description="Title of page" %>

<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<link rel="stylesheet" type="text/css" href="/ToyShop-Servlet/resources/css/bootstrap.min.css" />
	<style>
		.navbar-nav.navbar-right:last-child { margin-right:0px !important; }
		.content-body { padding: 5px 10px; }
	</style>
</head>
<body>
<div class="container">
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand" href="/ToyShop-Servlet/">ToyShop</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="/ToyShop-Servlet/">Home</a></li>
				<li><a href="#">Catalog</a></li>
				<li><a href="#">Basket</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" >
				<li><a href="/ToyShop-Servlet/adm/">Admin</a></li>
				<li><a href="/ToyShop-Servlet/auth/login.jsp">Sign in</a></li>
			</ul>
		</div>
	</nav>
	
	<div class="content-body">
		<jsp:doBody />
	</div>
</div>
<div class="container hidden-print">
	<hr />
	<footer><p>© 2014 ToyShop, Todos os direitos reservados - Powered by Wesley Egberto</p></footer>
</div>
</body>
</html>