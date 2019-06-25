	/*
	 ********************************************************************************************
	 * Course: PROJ 217 Threaded Project
	 * Purpose: Workshop 7
	 * Date: June 12, 2019.
	 * Author: Timothy Leslie
	 * Description: This is a Customers REST service to access database 
	 * information. This service will be called with AJAX from the client-side and used to
	 * populate an customer object.
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

import model.Customer;

@Path("/customers")
public class CustomersRESTService {

	@GET
	@Path("/getallcustomers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Customer> getAllCustomers()
	{
		//	http://localhost:8080/Team4API/rest/customers/getallcustomers
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select c from Customer c");
		List<Customer> customers = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Customer>>() {}.getType();
		//String jsonString = gson.toJson(agents, type);
		em.close();
		//return jsonString;
		return customers;
	}
	
	@GET
	@Path("/getcustomer/{ customerid }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Customer getCustomer(@PathParam("customerid") int customerId)
	{
		//	http://localhost:8080/Team4API/rest/customers/getcustomer/5
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Customer customer = em.find(Customer.class, customerId);
		
		//Gson gson = new Gson();
		//Type type = new TypeToken<Customer>() {}.getType();
		//String jsonString = gson.toJson(customer, type);
		em.close();
		//return jsonString;
		return customer;

	}
	
	
	@GET
	@Path("/getcustomerByName/{ userName }")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCustomer(@PathParam("userName") String userName)
	{
		//	http://localhost:8080/Team4API/rest/agents/getagent/5
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		//Customer customer = em.find(Customer.class, customerId);
		
		Query query = em.createQuery("select c from Customer c where c.userName='" + userName+"'");
		Customer customer = (Customer) query.getSingleResult();

		Gson gson = new Gson();
		Type type = new TypeToken<Customer>() {}.getType();
		String jsonString = gson.toJson(customer, type);
		em.close();
		return jsonString;

	}
	
	
	
	@POST
	@Path("/postcustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String postAgent(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Customer>() {}.getType();

		Customer customer = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		//Agent agent = em.find(Agent.class, jsonString["agentId"]);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		return "Customer update completed";
	}
		
	@PUT
	@Path("/putcustomer")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String putAgent(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Customer>() {}.getType();

		Customer customer = gson.fromJson(jsonString, type);
		
		// could possibly manage null customer properties here
		//if (customer.getAgentId() == -1) {
		//	System.out.println(customer.getAgentId());
		//	customer.setAgentId(null);
		//}
		
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		return "Customer insert completed";
	}
	
	@DELETE
	@Path("/deletecustomer/{ customerid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAgent(@PathParam("customerid") int customerId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Customer customer = em.find(Customer.class, customerId);
		em.getTransaction().begin();
		em.remove(customer);
		em.getTransaction().commit();
		return "Customer deleted";
	}

}
