package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

/**
 * Unit Test mocking the dependencies. 
 */
public class CatalogManagerBeanUnitTest {

	CatalogManagerBean catalog;
	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = mock(EntityManager.class);
		catalog = new CatalogManagerBean(em);
	}

	@Test
	public final void testSaveProduct() {
		final String name = "Some name";
		final String description = "Some description";
		final double price = 129.10;

		final Product prod = new Product();
		prod.setName(name);
		prod.setDescription(description);
		prod.setPrice(price);

		catalog.saveProduct(prod);

		verify(em, times(1)).persist(prod);
	}

	@Test
	public final void testRemoveProduct() {
		final Product prod = new Product();
		prod.setId(129);

		catalog.removeProduct(prod);

		verify(em, times(1)).merge(prod);
		verify(em, times(1)).remove(prod);
	}

	@Test
	public final void testGetProductById() {
		final long ID = 1249;

		catalog.getProductById(ID);

		verify(em, times(1)).find(Product.class, ID);
	}

	@Test
	@SuppressWarnings("unchecked")
	public final void testGetAll() {
		TypedQuery<Product> query = mock(TypedQuery.class);
		when(query.getResultList()).thenReturn(new ArrayList<Product>());
		when(em.createNamedQuery(Product.GET_ALL, Product.class)).thenReturn(query);

		Assert.assertNotNull(catalog.getAll());

		verify(em, times(1)).createNamedQuery(Product.GET_ALL, Product.class);
		verify(query, times(1)).getResultList();
	}

	@Test
	public final void testSearchByName() {
		final String NAME = "Batm";
		
		mockQueryCriteria(new ArrayList<Product>());

		List<Product> result = catalog.searchByName(NAME);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());
	}

	@Test
	public final void testSearchByCategory() {
		final String CATEGORY = "Movie";

		mockQueryCriteria(new ArrayList<Product>());
		
		List<Product> result = catalog.searchByCategory(CATEGORY);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());

	}

	@SuppressWarnings("unchecked")
	public void mockQueryCriteria(List<Product> listToReturn) {
		// mock the criteria
		CriteriaBuilder criteria = mock(CriteriaBuilder.class);
		when(em.getCriteriaBuilder()).thenReturn(criteria);

		// mocking the query
		CriteriaQuery<Product> query = mock(CriteriaQuery.class);
		when(criteria.createQuery(Product.class)).thenReturn(query);

		// mocking the root
		Root<Product> product = mock(Root.class);
		when(query.from(Product.class)).thenReturn(product);

		// mocking the typed query
		TypedQuery<Product> typedQuery = mock(TypedQuery.class);
		when(typedQuery.getResultList()).thenReturn(listToReturn);
		when(em.createQuery(query)).thenReturn(typedQuery);
	}
}
