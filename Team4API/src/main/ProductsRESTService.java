/*
 ********************************************************************************************
 * Course: PROJ 217 Threaded Project
 * Purpose: Workshop 7
 * Date: June 21, 2019.
 * Author: Timothy Leslie
 * Description: This is a Products REST service to access database 
 * information. This service will be called with AJAX from the client-side and used to
 * populate an product object.
 ********************************************************************************************
 */
package main;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Product;

@Path("/products")
public class ProductsRESTService {

	@GET
	@Path("/getallproducts")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String getAllProducts()
	public List<Product> getAllProducts()
	{
		//	http://localhost:8080/Team4API/rest/agents/getallproducts
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select p from Product p");
		List<Product> products = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Product>>() {}.getType();
		//String jsonString = gson.toJson(products, type);
		em.close();
		//return jsonString;
		return products;
	}
	
	@GET
	@Path("/getproduct/{ productid }")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProduct(@PathParam("productid") int productId)
	{
		//	http://localhost:8080/Team4API/rest/products/getproduct/5
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Product product = em.find(Product.class, productId);
		
		Gson gson = new Gson();
		Type type = new TypeToken<Product>() {}.getType();
		String jsonString = gson.toJson(product, type);
		em.close();
		return jsonString;

	}
	
	@POST
	@Path("/postproduct")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String postProduct(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Product>() {}.getType();

		Product product = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();
		return "Product update completed";
	}
	
	
	@PUT
	@Path("/putproduct")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String putProduct(String jsonString)
	{
		Gson gson = new Gson();
		Type type = new TypeToken<Product>() {}.getType();
		String temp = jsonString;
		Product product = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		return "Product insert completed";
	}
	
	@DELETE
	@Path("/deleteproduct/{ productid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(@PathParam("productid") int productId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Product product = em.find(Product.class, productId);
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
		return "Product deleted";
	}
}
