package com.github.wesleyegberto.toyshop.presentation.catalog;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.toyshop.business.catalog.boundary.ProductCatalog;
import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

@Named
@RequestScoped
public class CatalogController {
	private List<Product> products;
	@Inject
	private ProductCatalog catalog;
	private String searchKey;

	public CatalogController() {
		System.out.println("[MB] CatalogController created");
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		System.out.println("Key: " + searchKey);
		this.searchKey = searchKey;
	}

	public List<Product> getProducts() {
		if(products == null) {
			if(searchKey == null || searchKey.length() == 0) {
				products = catalog.getAll();
			} else {
				products = catalog.searchByName(searchKey);
			}
		}
		return products;
	}

}
