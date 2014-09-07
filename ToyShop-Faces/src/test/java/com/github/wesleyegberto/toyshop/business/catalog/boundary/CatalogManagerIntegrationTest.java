package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Unit Test using the Arquillian to bring the Container. 
 */
@RunWith(Arquillian.class)
public class CatalogManagerIntegrationTest {

	@Inject // we can now inject in a test
	private CatalogManager catalog;

	// must be static and return the archifact to deploy
	@Deployment
	public static JavaArchive createWarTest() {
		return ShrinkWrap.create(JavaArchive.class, "CatalogManager_Test.jar")
			.addClasses(Product.class, CatalogManager.class, ProductCatalog.class, CatalogManagerBean.class)
			.addClass(CatalogManagerIntegrationTest.class)
			.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
			.addAsManifestResource("WEB-INF/test-persistence.xml", "persistence.xml");
	}
	
	@Test
	public void testIsDeployed() {
		Assert.assertNotNull(catalog);
	}
	
	@Test
	public void testConnection() {
		List<Product> list = catalog.getAll();
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void testCRUDProduct() {
		Product prod = new Product();
		prod.setName("Product 1");
		prod.setDescription("Product 1");
		prod.setCategory("Category 1");
		prod.setPicture("");
		prod.setPrice(1);
		prod.setStockQnty(2);
		
		catalog.saveProduct(prod);
		
		// verify if it was saved
		List<Product> list = catalog.getAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());

		final int ID = 1;
		prod = catalog.getProductById(ID);
		
		Assert.assertNotNull("Product is null", prod);
		Assert.assertEquals(prod.getId(), ID);
		
		catalog.removeProduct(prod);
		// assert the db is empty the remove
		list = catalog.getAll();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}
}
