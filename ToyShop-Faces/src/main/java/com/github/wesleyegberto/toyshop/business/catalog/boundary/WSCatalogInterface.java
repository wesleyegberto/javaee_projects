package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Interface to define a default contract to the WS. 
 */
public interface WSCatalogInterface {

	public abstract List<Product> getCatalog();

	public abstract List<Product> searchProductsByName(String name);

	public abstract List<Product> searchProductsByCategory(String category);

}
