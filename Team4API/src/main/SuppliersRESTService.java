/*
 ********************************************************************************************
 * Course: PROJ 217 Threaded Project
 * Purpose: Workshop 7
 * Date: June 21, 2019.
 * Author: Timothy Leslie
 * Description: This is a Suppliers REST service to access database 
 * information. This service will be called with AJAX from the client-side and used to
 * populate an supplier object.
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


import model.Supplier;

@Path("/suppliers")
public class SuppliersRESTService {

	@GET
	@Path("/getallsuppliers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Supplier> getAllSuppliers()
	{
		//	http://localhost:8080/Team4API/rest/agents/getallproducts
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select s from Supplier s");
		List<Supplier> suppliers = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Supplier>>() {}.getType();
		//String jsonString = gson.toJson(suppliers, type);
		em.close();
		//return jsonString;
		return suppliers;
	}
	
	@GET
	@Path("/getsupplier/{ supplierid }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Supplier getSupplier(@PathParam("supplierid") int supplierId)
	{
		//	http://localhost:8080/Team4API/rest/products/getproduct/5
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Supplier supplier = em.find(Supplier.class, supplierId);
		
		//Gson gson = new Gson();
		//Type type = new TypeToken<Supplier>() {}.getType();
		//String jsonString = gson.toJson(supplier, type);
		em.close();
		//return jsonString;
		return supplier;

	}
	
	@POST
	@Path("/postsupplier")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String postSupplier(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Supplier>() {}.getType();

		Supplier supplier = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.merge(supplier);
		em.getTransaction().commit();
		return "Supplier update completed";
	}
	
	
	@PUT
	@Path("/putsupplier")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String putSupplier(String jsonString)
	{
		Gson gson = new Gson();
		Type type = new TypeToken<Supplier>() {}.getType();
		String temp = jsonString;
		Supplier supplier = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(supplier);
		em.getTransaction().commit();
		return "Supplier insert completed";
	}
	
	@DELETE
	@Path("/deletesupplier/{ supplierid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSupplier(@PathParam("supplierid") int supplierId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Supplier supplier = em.find(Supplier.class, supplierId);
		em.getTransaction().begin();
		em.remove(supplier);
		em.getTransaction().commit();
		return "Supplier deleted";
	}

}
