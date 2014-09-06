package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import javax.ejb.Local;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Session Bean implementation class ProductCatalog
 */
@Local
public interface ProductCatalog {

    public Product getProductById(long id);

    public List<Product> getAll();

    public List<Product> searchByName(String name);
    
    public List<Product> searchByCategory(String category);
}
