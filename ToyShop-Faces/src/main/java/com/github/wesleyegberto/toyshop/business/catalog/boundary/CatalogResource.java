package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

@Path("catalog")
public class CatalogResource {
	@Inject
	private ProductCatalog catalog;
	
	public CatalogResource() {
		System.out.println("[REST] CatalogResource created");
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Product> getCatalog() {
		return catalog.getAll();
	}
	
	@Path("name/{name}")
	public List<Product> searchProductsByName(@PathParam("name") String name) {
		return catalog.searchByName(name);
	}

	@Path("category/{category}")
	public List<Product> searchProductsByCategory(@PathParam("category") String category) {
		return catalog.searchByCategory(category);
	}
	
}
