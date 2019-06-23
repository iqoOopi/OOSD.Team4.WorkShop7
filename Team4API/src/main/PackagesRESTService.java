	/*
	 ********************************************************************************************
	 * Course: PROJ 217 Threaded Project
	 * Purpose: Workshop 7
	 * Date: June 21, 2019.
	 * Author: Timothy Leslie
	 * Description: This is a Packages REST service to access database 
	 * information. This service will be called with AJAX from the client-side and used to
	 * populate an pkg object.
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

import model.Package;

@Path("/packages")
public class PackagesRESTService {

	@GET
	@Path("/getallpackages")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Package> getAllPackages()
	{
		//	http://localhost:8080/Team4API/rest/agents/getallagents
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select p from Package p");
		List<Package> packages = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Package>>() {}.getType();
		//String jsonString = gson.toJson(packages, type);
		em.close();
		//return jsonString;
		return packages;
	}
	
	@GET
	@Path("/getpackage/{ packageid }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Package getPackage(@PathParam("packageid") int packageId)
	{
		//	http://localhost:8080/Team4API/rest/agents/getagent/5
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Package pkg = em.find(Package.class, packageId);
		
		//Query query = em.createQuery("select a from Agent a where a.agentId=" + agentId);
		//Agent agent = (Agent) query.getSingleResult();

		//Gson gson = new Gson();
		//Type type = new TypeToken<Package>() {}.getType();
		//String jsonString = gson.toJson(pkg, type);
		em.close();
		//return jsonString;
		return pkg;

	}
	
	@POST
	@Path("/postpackage")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String postPackage(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Package>() {}.getType();

		Package pkg = gson.fromJson(jsonString, type);
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		//Agent agent = em.find(Agent.class, jsonString["agentId"]);
		em.getTransaction().begin();
		em.merge(pkg);
		em.getTransaction().commit();
		return "Package update completed";
	}
	
	
	@PUT
	@Path("/putpackage")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String putPackage(String jsonString)
	{
		Gson gson = new Gson();
		Type type = new TypeToken<Package>() {}.getType();
		String temp = jsonString;
		Package pkg = gson.fromJson(jsonString, type);
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(pkg);
		em.getTransaction().commit();
		return "Package insert completed";
	}
	
	@DELETE
	@Path("/deletepackage/{ packageid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePackage(@PathParam("packageid") int packageId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Package pkg = em.find(Package.class, packageId);
		em.getTransaction().begin();
		em.remove(pkg);
		em.getTransaction().commit();
		return "Package deleted";
	}

}
