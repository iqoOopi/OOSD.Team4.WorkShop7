package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BookingForPackage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int bookingId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookingDate;
	
	private String bookingNo;
	private int customerId;
	private double travelerCount;
	private String tripTypeId;
	private int packageId;
	private String pkgName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgEndDate;
	
	private String pkgDesc;
	private BigDecimal pkgBasePrice;
	private BigDecimal pkgAgencyCommission;
	
	public BookingWPackage(){
		
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getTravelerCount() {
		return travelerCount;
	}
	public void setTravelerCount(double travelerCount) {
		this.travelerCount = travelerCount;
	}
	public String getTripTypeId() {
		return tripTypeId;
	}
	public void setTripTypeId(String tripTypeId) {
		this.tripTypeId = tripTypeId;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public Date getPkgStartDate() {
		return pkgStartDate;
	}
	public void setPkgStartDate(Date pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}
	public Date getPkgEndDate() {
		return pkgEndDate;
	}
	public void setPkgEndDate(Date pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}
	public String getPkgDesc() {
		return pkgDesc;
	}
	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}
	public BigDecimal getPkgBasePrice() {
		return pkgBasePrice;
	}
	public void setPkgBasePrice(BigDecimal pkgBasePrice) {
		this.pkgBasePrice = pkgBasePrice;
	}
	public BigDecimal getPkgAgencyCommission() {
		return pkgAgencyCommission;
	}
	public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
		this.pkgAgencyCommission = pkgAgencyCommission;
	}
}
