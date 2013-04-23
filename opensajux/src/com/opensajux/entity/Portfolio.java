package com.opensajux.entity;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class Portfolio extends BaseEntity {
	private static final long serialVersionUID = 3367768351189122600L;

	@Persistent
	private String id;

	@Persistent
	private String firstName;

	@Persistent
	private String middleName;

	@Persistent
	private String lastName;

	@Persistent
	private Date dob;

	@Persistent
	private Email email;

	@Persistent
	private Text address;

	@Persistent
	private Date updatedDate;

	@Persistent
	private Text headline;

	@Persistent
	private Text summary;

	@Persistent
	private String industry;

	@Persistent
	private String interests;

	@Persistent
	private String location;

	@Persistent(mappedBy = "portfolio")
	@Element(dependent = "true")
	private List<Position> positions;

	@Persistent(mappedBy = "portfolio")
	@Element(dependent = "true")
	private List<Education> educations;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return (firstName != null ? firstName : "") + " " + (middleName != null ? middleName : "") + " "
				+ (lastName != null ? lastName : "");
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Text address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public Text getAddress() {
		return address;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the headline
	 */
	public Text getHeadline() {
		return headline;
	}

	/**
	 * @param headline
	 *            the headline to set
	 */
	public void setHeadline(Text headline) {
		this.headline = headline;
	}

	/**
	 * @return the summary
	 */
	public Text getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(Text summary) {
		this.summary = summary;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the interests
	 */
	public String getInterests() {
		return interests;
	}

	/**
	 * @param interests
	 *            the interests to set
	 */
	public void setInterests(String interests) {
		this.interests = interests;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions
	 *            the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * @return the educations
	 */
	public List<Education> getEducations() {
		return educations;
	}

	/**
	 * @param educations
	 *            the educations to set
	 */
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

}
