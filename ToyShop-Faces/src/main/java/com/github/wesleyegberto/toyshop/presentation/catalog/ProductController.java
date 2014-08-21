package com.github.wesleyegberto.toyshop.presentation.catalog;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.toyshop.business.catalog.boundary.ProductCatalog;
import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

@Named
@RequestScoped
public class ProductController {
	private long productId;
	private Product product;
	
	@Inject
	private ProductCatalog catalog;

	public ProductController() {
		System.out.println("[MB] CatalogController created");
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public Product getProduct() {
		return product;
	}

	public void loadProduct() {
		product = catalog.getProductById(productId);
		if(product == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch(IOException e) {
			}
		}
	}
}
