package com.opensajux.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PhoneNumber;

@PersistenceCapable
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 3367768351189122600L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;

	@Persistent
	private String fullName;

	@Persistent
	private Date dob;

	@Persistent
	private Email email;

	@Persistent
	private String address;

	@Persistent
	private PhoneNumber phone;

	@Persistent
	private String coverLetter;

	public Key getKey() {
		return key;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
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
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * @return the phone
	 */
	public PhoneNumber getPhone() {
		return phone;
	}

	/**
	 * @param coverLetter
	 *            the coverLetter to set
	 */
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	/**
	 * @return the coverLetter
	 */
	public String getCoverLetter() {
		return coverLetter;
	}

}
