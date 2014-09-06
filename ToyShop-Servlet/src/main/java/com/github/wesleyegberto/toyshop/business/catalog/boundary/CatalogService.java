package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Web Service SOAP 
 */
@WebService(name = "Product Catalog Service", serviceName = "CatalogService", portName = "ProductCatalog")
public class CatalogService implements WSCatalogInterface {
	@Inject
	private ProductCatalog catalog;

	public CatalogService() {
		System.out.println("[SOAP] CatalogService created");
	}

	@WebMethod(operationName = "getProductsInCatalog")
	@WebResult(name = "products")
	@Override
	public List<Product> getCatalog() {
		return catalog.getAll();
	}

	@WebMethod(operationName = "searchProductsByName")
	@WebResult(name = "products")
	@Override
	public List<Product> searchProductsByName(@WebParam(name = "name") String name) {
		return catalog.searchByName(name);
	}

	@WebMethod(operationName = "searchProductsByCategory")
	@WebResult(name = "products")
	@Override
	public List<Product> searchProductsByCategory(@WebParam(name = "category") String category) {
		return catalog.searchByCategory(category);
	}

}
