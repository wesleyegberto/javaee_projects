package com.github.wesleyegberto.toyshop.business.catalog.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;

@Stateless
public class CatalogManagerBean implements CatalogManager {
	@PersistenceContext
	private EntityManager em;
	
    public CatalogManagerBean() {
		System.out.println("[EJB] CatalogManagerBean created");
    }

    public CatalogManagerBean(EntityManager em) {
    	this.em = em;
	}

	public void saveProduct(Product prod) {
    	em.persist(prod);
    }
    
    public void removeProduct(Product prod) {
    	em.merge(prod);
    	em.remove(prod);
    }

    public Product getProductById(long id) {
    	return em.find(Product.class, id);
    }
    
    public List<Product> getAll() {
    	TypedQuery<Product> query = em.createNamedQuery(Product.GET_ALL, Product.class);
    	return query.getResultList();
    }

    public List<Product> searchByName(String name) {
    	CriteriaBuilder criteria = em.getCriteriaBuilder();
    	CriteriaQuery<Product> query = criteria.createQuery(Product.class);
    	
    	Root<Product> product = query.from(Product.class);
        query.select(product);

        if(name != null && name.length() > 0) {
            query.where(criteria.like(product.<String>get("name"), name + "%"));
        }
        
    	return em.createQuery(query).getResultList();
    }
    
    public List<Product> searchByCategory(String category) {
    	CriteriaBuilder criteria = em.getCriteriaBuilder();
    	CriteriaQuery<Product> query = criteria.createQuery(Product.class);
    	
    	Root<Product> product = query.from(Product.class);
        query.select(product);

        if(category != null && category.length() > 0) {
            query.where(criteria.like(product.<String>get("category"), category + "%"));
        }
        
    	return em.createQuery(query).getResultList();
    }
}
