package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import javax.ejb.Local;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

@Local
public interface CatalogManager extends ProductCatalog {

    public void saveProduct(Product prod);

    public void removeProduct(Product prod);
}
