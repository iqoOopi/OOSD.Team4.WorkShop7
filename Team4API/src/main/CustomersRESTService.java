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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Agent;
import model.Booking;
import model.BookingForPackage;
import model.Customer;
import model.Package;
import sun.misc.BASE64Decoder;


//import org.apache.commons.codec.binary.Base64;//

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
		Gson gson = new Gson();
		Type type = new TypeToken<Customer>() {}.getType();
		String temp = jsonString;
		Customer customer = gson.fromJson(jsonString, type);
		EntityManager em =
//				Persistence.createEntityManagerFactory("OOSD.Team4.Workshop7.Team4API").createEntityManager();
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
	
	
	/*Eric Addtional Codes Starts*/
	private class LoginCredentials{

		private String CustEmail;
		private String CustPassword;
		
		public LoginCredentials(){
		}

		public String getCustEmail() {
			return CustEmail;
		}

		public void setCustEmail(String custEmail) {
			this.CustEmail = custEmail;
		}

		public String getCustPassword() {
			return CustPassword;
		}

		public void setCustPassword(String custPassword) {
			this.CustPassword = custPassword;
		}
	}
	
	private enum letterNotation {A,B,C,D,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,U,V,W,X,Y,Z}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public Response postLogin(String request) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		try{
			///
			// Request comes in as Base64 encoded
			///
			System.out.println(request);
			System.out.println("*************************************************************");
			String test = request.toString();
			System.out.println(test);
			
			BASE64Decoder decoder = new BASE64Decoder();
			
			
//			request=new String(Base64.getDecoder().decode(request.toString()));
			try {
				request = new String(decoder.decodeBuffer(request));
				System.out.println("*************************************************************");
				System.out.println(request);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new Gson();
			LoginCredentials jsonIN = gson.fromJson(request,LoginCredentials.class);
		
			String query = "SELECT c FROM Customer c WHERE c.custEmail=:requestEMAIL";
			Query q = em.createQuery(query);
			
			q.setParameter("requestEMAIL", jsonIN.getCustEmail());
			
			System.out.println("*************************************************************");
			System.out.println(q.toString());
			
			String jsonOUT = gson.toJson(q.getSingleResult());
			Customer customer = gson.fromJson(jsonOUT, Customer.class);
			
			String passwordIN = jsonIN.getCustPassword();
			
			if(passwordIN.equals(customer.getPassword())){
				///
				// SUCCESSFUL LOGIN
				// TOKEN GENERATION
				///
				String RANDOMTIME = String.valueOf(Calendar.getInstance().getTimeInMillis());
				RANDOMTIME = RANDOMTIME.substring(RANDOMTIME.length()-3,RANDOMTIME.length());
				StringBuilder sb = new StringBuilder("");
				sb.append(RANDOMTIME);
				sb.append(customer.getCustEmail());
				sb.append(RANDOMTIME);
				String strTOKEN = sb.toString();
				byte[] byteTOKEN = Base64.getEncoder().encode(strTOKEN.getBytes());
				String TOKEN = new String(byteTOKEN);
				
				///
				// IF TOKEN EXISTS, UPDATE IT
				///
				String selectQuery = "SELECT c FROM Customerstoken c WHERE c.customerId=:CUSTOMERID";
				
				Query q1 = em.createQuery(selectQuery);
				q1.setParameter("CUSTOMERID", customer.getCustomerId());
				if(q1.getResultList().size()>0){
					em.getTransaction().begin();
					String deleteQuery = "DELETE FROM customerstokens WHERE CustomerId = ?";
					Query q2 = em.createNativeQuery(deleteQuery);
					q2.setParameter(1, customer.getCustomerId());
					q2.executeUpdate();
					em.getTransaction().commit();
				}
				///
				// Hand out new token
				///
				em.getTransaction().begin();
				String insertQuery = "INSERT INTO customerstokens (CustomerId,TOKEN,TOKENCREATED) VALUES (?,?,?)";
				Query q3 = em.createNativeQuery(insertQuery);
				q3.setParameter(1, customer.getCustomerId());
				q3.setParameter(2, TOKEN);
				q3.setParameter(3, Calendar.getInstance().getTime());
				q3.executeUpdate();
				em.getTransaction().commit();
				em.close();
				emfactory.close();
				///
				// FINALLY RETURN
				///
				
				return Response.ok(gson.toJson(customer),MediaType.APPLICATION_JSON).header("AUTHTOKEN", TOKEN).build();
			}else{
				return Response.status(Status.UNAUTHORIZED).build();
			}
			
		}catch(NoResultException e){
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	@POST
	@Path("/assignagent2cust")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
	public Response postAssignAgent(String custJSON){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		Type type = new TypeToken<Customer>(){}.getType();
		Customer c = new Gson().fromJson(custJSON, type);
		
		em.getTransaction().begin();
		Query q = em.createNativeQuery("UPDATE customer SET AgentId = ? WHERE CustomerId = ?");
		int agentid = GenerateRandomAgent();
		q.setParameter(1, agentid);
		q.setParameter(2, c.getCustomerId());
		
		if(q.executeUpdate()==1){
			em.getTransaction().commit();
			em.close();
			emfactory.close();
			return Response.status(Status.OK).header("AGENTID", agentid).build();
		}else{
			em.close();
			emfactory.close();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	private int GenerateRandomAgent(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		Query q = em.createQuery("SELECT a FROM Agent a");
		
		List<Agent> agents = q.getResultList();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(int i=0;i<agents.size();i++){
			ids.add(agents.get(i).getAgentId());
		}
		
		Random random = new Random();
		
		return ids.get(random.nextInt(ids.size()));
	}
	
	
	@GET
	@Path("/get_my_bookings/{custid}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getMyBookingForPackage(@PathParam("custid") int custid) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		Query qBookings = em.createQuery("SELECT b FROM Booking b WHERE b.customerId=:CUSTID");
		qBookings.setParameter("CUSTID", custid);
		System.out.println(custid);
		System.out.println("*******************8");
		List<Booking> bookings = qBookings.getResultList();
		
		Query qPackage;
		List<Package> packages__bookings = new ArrayList<Package>();
		for(int i=0;i<bookings.size();i++){
			qPackage = em.createQuery("SELECT p FROM Package p WHERE p.packageId=:PACKAGEID");
			System.out.println(bookings.get(i).getPackageId());
			qPackage.setParameter("PACKAGEID", bookings.get(i).getPackageId());
			packages__bookings.add((Package)qPackage.getSingleResult());
		}
		
		List<BookingForPackage> bookingForPackagesList = new ArrayList<BookingForPackage>();
		BookingForPackage bookingforpackage;
		for(int i=0;i<bookings.size();i++){
			bookingforpackage = new BookingForPackage();
			bookingforpackage.setBookingId(bookings.get(i).getBookingId());
			bookingforpackage.setBookingDate(bookings.get(i).getBookingDate());
			bookingforpackage.setBookingNo(bookings.get(i).getBookingNo());
			bookingforpackage.setCustomerId(bookings.get(i).getCustomerId());
			bookingforpackage.setTravelerCount(bookings.get(i).getTravelerCount());
			bookingforpackage.setTripTypeId(bookings.get(i).getTripTypeId());
			bookingforpackage.setPackageId(bookings.get(i).getPackageId());
			bookingforpackage.setPkgName(packages__bookings.get(i).getPkgName());
			bookingforpackage.setPkgStartDate(packages__bookings.get(i).getPkgStartDate());
			bookingforpackage.setPkgEndDate(packages__bookings.get(i).getPkgEndDate());
			bookingforpackage.setPkgDesc(packages__bookings.get(i).getPkgDesc());
			bookingforpackage.setPkgBasePrice(packages__bookings.get(i).getPkgBasePrice());
			bookingforpackage.setPkgAgencyCommission(packages__bookings.get(i).getPkgAgencyCommission());			
			
			bookingForPackagesList.add(bookingforpackage);
		}
		
		String json = new Gson().toJson(bookingForPackagesList);
		
		em.close();
		emfactory.close();			
		return json;
	}
	
	
	@POST
	@Path("/create_booking")
    @Produces(MediaType.TEXT_PLAIN)
	public Response postBooking(String request) {
		
		//to convert JSON data to entity		
		
		Type type = new TypeToken<Booking>(){}.getType();
		Booking b = new Gson().fromJson(request, type);
		String bookingNo = generateBookingNo();
		b.setBookingNo(bookingNo);
		
		// pass the entity to persistence framework
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		//won't work without transaction, throws exception
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
		
		return Response.ok().build(); 	
	}

	private String generateBookingNo(){
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		Query q = em.createQuery("SELECT b FROM Booking b");
		
		List<Booking> bookings = q.getResultList();
		
		List<String> bookingNos = new ArrayList<String>();
		for(int i=0;i<bookings.size();i++){
			bookingNos.add(bookings.get(i).getBookingNo());
		}
		
		String bookingno = "";
		boolean keeplooking = true;
		Random random = new Random();
		while(keeplooking){
			keeplooking=false;
			bookingno = "";
			for(int i=0;i<4;i++){
				bookingno+=letterNotation.values()[random.nextInt(24)];
			}
			for(int i=0;i<2;i++){
				bookingno+=random.nextInt(10);
			}
			for(int i=0;i<bookingNos.size();i++){
				if(bookingno==bookingNos.get(i)){
					keeplooking=true;
				}
			}
		}
		
		em.close();
		emfactory.close();
		return bookingno;
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postUpdateAccount(String custJSON){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		Type type = new TypeToken<Customer>(){}.getType();
		Customer c = new Gson().fromJson(custJSON, type);
		
		em.getTransaction().begin();
		Query q = em.createNativeQuery("UPDATE Customer SET "+
		"CustFirstName = ?,CustLastName = ?,CustAddress = ?,CustCity = ?,CustProv = ?,CustPostal = ?,CustCountry = ?,CustHomePhone = ?,CustBusPhone = ?,CustEmail = ? "+
				"WHERE CustomerId = ?");
		q.setParameter(1, c.getCustFirstName());
		q.setParameter(2, c.getCustLastName());
		q.setParameter(3, c.getCustAddress());
		q.setParameter(4, c.getCustCity());
		q.setParameter(5, c.getCustProv());
		q.setParameter(6, c.getCustPostal());
		q.setParameter(7, c.getCustCountry());
		q.setParameter(8, c.getCustHomePhone());
		q.setParameter(9, c.getCustBusPhone());
		q.setParameter(10, c.getCustEmail());
		q.setParameter(11, c.getCustomerId());
		
		if(q.executeUpdate()==1){
			em.getTransaction().commit();
			em.close();
			emfactory.close();
			return Response.status(Status.OK).build();
		}else{
			em.close();
			emfactory.close();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/register_customer")
    @Produces(MediaType.TEXT_PLAIN)
	public Response postCustomer(String request) {
		///
		// Customer is passed to service as JSON
		///
		Type type = new TypeToken<Customer>(){}.getType();
		Customer c= new Gson().fromJson(request, type);
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Team4API");
		EntityManager em = emfactory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		
		return Response.status(Status.OK).build();
	}
	

}
