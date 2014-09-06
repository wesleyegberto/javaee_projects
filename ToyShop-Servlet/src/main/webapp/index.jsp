<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<tf:template title="ToyShop">

<form class="form-horizontal">
	<p>Welcome to ToyShop!</p>

	<p>${productView.message}</p>
	<p>${productView.hello("Wesley")}</p>
	<input type="submit" class="btn btn-success" value="Salvar" />
</form>

</tf:template>
