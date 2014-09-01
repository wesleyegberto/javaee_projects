package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Web Service RESTful 
 */
@Path("catalog")
public class CatalogResource implements WSCatalogInterface {
	@Inject
	private ProductCatalog catalog;
	
	public CatalogResource() {
		System.out.println("[REST] CatalogResource created");
	}

	/**
	 * @see com.github.wesleyegberto.toyshop.business.catalog.boundary.WSCatalogInterface#getCatalog()
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Product> getCatalog() {
		return catalog.getAll();
	}

	/**
	 * @see com.github.wesleyegberto.toyshop.business.catalog.boundary.WSCatalogInterface#searchProductsByName(java.lang.String)
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("name/{name}")
	public List<Product> searchProductsByName(@PathParam("name") String name) {
		return catalog.searchByName(name);
	}

	/**
	 * @see com.github.wesleyegberto.toyshop.business.catalog.boundary.WSCatalogInterface#searchProductsByCategory(java.lang.String)
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("category/{category}")
	public List<Product> searchProductsByCategory(@PathParam("category") String category) {
		return catalog.searchByCategory(category);
	}
	
}
