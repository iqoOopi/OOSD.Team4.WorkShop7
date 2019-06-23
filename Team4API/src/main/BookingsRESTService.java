	/*
	 ********************************************************************************************
	 * Course: PROJ 217 Threaded Project
	 * Purpose: Workshop 7
	 * Date: June 22, 2019.
	 * Author: Timothy Leslie
	 * Description: This is an Bookings REST service to access database 
	 * information. This service will be called with AJAX from the client-side and used to
	 * populate an booking object.
	 *******************************************************************************************/
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
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Booking;

@Path("/bookings")
public class BookingsRESTService {

	@GET
	@Path("/getallbookings")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Booking> getAllBookings()
	{
		//	http://localhost:8080/Team4API/rest/bookings/getallbookings
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		Query query = em.createQuery("select b from Booking b");
		List<Booking> bookings = query.getResultList();
		//Gson gson = new Gson();
		//Type type = new TypeToken<List<Booking>>() {}.getType();
		//String jsonString = gson.toJson(bookings, type);
		em.close();
		//return jsonString;
		return bookings;
	}
	
	@GET
	@Path("/getbooking/{ bookingid }")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Booking getBooking(@PathParam("bookingid") int bookingId)
	{
		//	http://localhost:8080/Team4API/rest/agents/getagent/5
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();

		Booking booking = em.find(Booking.class, bookingId);
		
		//Query query = em.createQuery("select a from Agent a where a.agentId=" + agentId);
		//Agent agent = (Agent) query.getSingleResult();

		//Gson gson = new Gson();
		//Type type = new TypeToken<Booking>() {}.getType();
		//String jsonString = gson.toJson(booking, type);
		em.close();
		//return jsonString;
		return booking;

	}
	
	@POST
	@Path("/postbooking")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String postBooking(String jsonString)
	{
		System.out.println(jsonString);
		Gson gson = new Gson();
		Type type = new TypeToken<Booking>() {}.getType();

		Booking booking = gson.fromJson(jsonString, type);
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.merge(booking);
		em.getTransaction().commit();
		return "Booking update completed";
	}

	@PUT
	@Path("/putbooking")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String putBooking(String jsonString)
	{
		Gson gson = new Gson();
		Type type = new TypeToken<Booking>() {}.getType();
		String temp = jsonString;
		Booking booking = gson.fromJson(jsonString, type);
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(booking);
		em.getTransaction().commit();
		return "Booking insert completed";
	}
	
	@DELETE
	@Path("/deletebooking/{ bookingid }")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBooking(@PathParam("bookingid") int bookingId)
	{
		EntityManager em =
				Persistence.createEntityManagerFactory("Team4API").createEntityManager();
		
		Booking booking = em.find(Booking.class, bookingId);
		em.getTransaction().begin();
		em.remove(booking);
		em.getTransaction().commit();
		return "Booking deleted";
	}

}
