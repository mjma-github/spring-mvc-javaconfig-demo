package com.mj.springdemo.mvc.form.model;

import java.util.Arrays;

//For testing form binding only
public class Student {

	private String firstName;
	private String LastName;
	private String country;
	private String countryDescription;
	private String favoriteLanguage;
	private String[] operatingSystems;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryDescription() {
		return countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public String[] getOperatingSystems() {
		return operatingSystems;
	}
	
	public void setOperatingSystems(String[] operatingSystems) {
		this.operatingSystems = operatingSystems;
	}
	
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", LastName=" + LastName + ", country=" + country
				+ ", countryDescription=" + countryDescription + ", favoriteLanguage=" + favoriteLanguage
				+ ", operatingSystems=" + Arrays.toString(operatingSystems) + "]";
	}
}
