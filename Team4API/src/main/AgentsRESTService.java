	/*
	 ********************************************************************************************
	 * Course: PROJ 217 Threaded Project
	 * Purpose: Workshop 7
	 * Date: June 12, 2019.
	 * Author: Timothy Leslie
	 * Description: This is an Agents REST service to access database 
	 * information. This service will be called with AJAX from the client-side and used to
	 * populate an agent object.
	 *******************************************************************************************/
package main;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import model.Agent;

@Path("/agents")
public class AgentsRESTService {

	@GET
	@Path("/getallagents")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Agent> getAllAgents()
	{
		System.out.println("Starting getallagents.");
		//	http://localhost:8080/Team4API/rest/agents/getallagents
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select a from Agent a");
		List<Agent> agents = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Agent>>() {}.getType();
		//String jsonString = gson.toJson(agents, type);
		em.close();
		//return jsonString;
		return agents;
	}
	
	@GET
	@Path("/getagent/{ agentid }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Agent getAgent(@PathParam("agentid") int agentId)
	{
		//	http://localhost:8080/Team4API/rest/agents/getagent/5
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Agent agent = em.find(Agent.class, agentId);
		
		//Query query = em.createQuery("select a from Agent a where a.agentId=" + agentId);
		//Agent agent = (Agent) query.getSingleResult();

		//Gson gson = new Gson();
		//Type type = new TypeToken<Agent>() {}.getType();
		//String jsonString = gson.toJson(agent, type);
		em.close();
		//return jsonString;
		return agent;
	}
	
	@GET
	@Path("/getagentemail/{ email }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getAgentE(@PathParam("email") String email)
	{
		System.out.println(email);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		System.out.println("Starting getagente");
		Query query = em.createQuery("select a from Agent a where a.agtEmail='" + email + "'");
		Agent agent = (Agent) query.getSingleResult();
		em.close();
		
		System.out.println(agent.toString());
				
		Gson gson = new Gson();
		Type type = new TypeToken<Agent>() {}.getType();
		String jsonString = gson.toJson(agent, type);
		//em.close();
		
		//return jsonString;		
		return jsonString;
		//return agent;
	}


//	@GET
//	@Path("/getagente/{ email }")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String getAgentE(@PathParam("email") String email)
//	{
//		System.out.println(email);
//		EntityManager em =
//				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
//		
//		System.out.println("Starting getagente");
//		Query query = em.createQuery("select a from Agent a");
//		List<Agent> agents = query.getResultList();
//		em.close();
//		Agent tempAgent = new Agent();
//		for (Agent agt : agents)
//		{ 
//			if (agt.getAgtEmail().equals(email)) {
//				System.out.println(agt.getAgtEmail() + " " + email);
//				tempAgent = agt;
//			}
//		}
//				
//		Gson gson = new Gson();
//		Type type = new TypeToken<Agent>() {}.getType();
//		String jsonString = gson.toJson(tempAgent, type);
//		//em.close();
//		
//		//return jsonString;		
//		return jsonString;
//		//return agent;
//	}

	
	@POST
	@Path("/postagent")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String postAgent(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Agent>() {}.getType();

		Agent agent = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		//Agent agent = em.find(Agent.class, jsonString["agentId"]);
		em.getTransaction().begin();
		em.merge(agent);
		em.getTransaction().commit();
		return "Agent update completed";
	}
	
//	@POST
//	@Path("/postagent")
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Produces(MediaType.TEXT_PLAIN)
//	public String postAgent(String jsonString)
//	{
//		System.out.println("starting postagent");
//		System.out.println(jsonString);
//		JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
//		System.out.println(json);
//		EntityManagerFactory factory =
//				Persistence.createEntityManagerFactory("Team4API");
//		EntityManager em = factory.createEntityManager();
//		
//		//Agent agent = em.find(Agent.class, json.get("agentId").getAsInt());
//		
//		Agent agent = new Agent();
//		agent.setAgentId(json.get("agentId").getAsInt());
//		agent.setAgtFirstName(json.get("agtFirstName").getAsString());
//		agent.setAgtMiddleInitial(json.get("agtMiddleInitial").getAsString());
//		agent.setAgtLastName(json.get("agtLastName").getAsString());
//		agent.setAgtBusPhone(json.get("agtBusPhone").getAsString());
//		agent.setAgtEmail(json.get("agtEmail").getAsString());
//		agent.setAgtPosition(json.get("agtPosition").getAsString());
//		agent.setAgencyId(json.get("agencyId").getAsInt());
//		em.getTransaction().begin();
//		em.merge(agent);
//		em.getTransaction().commit();
//		em.close();
//		factory.close();
//		return "Agent Updated";
//	}
	
	@PUT
	@Path("/putagent")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String putAgent(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Agent>() {}.getType();
		Agent agent = gson.fromJson(jsonString, type);
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(agent);
		em.getTransaction().commit();
		return "Agent insert completed";
	}
	
	@DELETE
	@Path("/deleteagent/{ agentid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAgent(@PathParam("agentid") int agentId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Agent agent = em.find(Agent.class, agentId);
		em.getTransaction().begin();
		em.remove(agent);
		em.getTransaction().commit();
		return "Agent deleted";
	}
}
