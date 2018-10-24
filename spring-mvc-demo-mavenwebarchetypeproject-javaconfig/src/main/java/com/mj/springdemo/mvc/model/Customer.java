package com.mj.springdemo.mvc.model;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.mj.springdemo.mvc.validation.CourseCode;
import com.mj.springdemo.mvc.validation.Range;
import com.mj.springdemo.mvc.validation.ValidCustomer;

/*
 * Note: as an alternative to annotating fields individually with validation,
 * an element type/class annotation can be created to validate the object
 * & can be used in combination with field level annotations!
 * see here: https://stackoverflow.com/questions/2781771/how-can-i-validate-two-or-more-fields-in-combination?noredirect=1&lq=1 
 */

@ValidCustomer
public class Customer {
	private String firstName;

	@NotNull(message = "is required")
	// @NotBlank(message="must not be blank")
	@Size(min = 1, message = "is required")
	private String lastName;

	// @NotNull(message = "is required")
	// @Min(value = 0, message = "must be greater than or equal to 0")
	// @Max(value = 10, message = "must be less than or equal to 10")
	// private Integer freePasses;

	// @Range(min=0, max=10) // not part of the standard Bean validation
	// specification (JSR 303).
	// private int freePasses; //will not work with @NotNull since null cannot be
	// assigned to a primitive

	// using custom validation annotation
	@NotNull(message = "is required")
	@Range(min = 1, max = 5, message = "must be between 1 to 5")
	private Integer freePasses;

	@Email(message = "invalid email address format")
	private String emailAddress;

	@NotNull(message = "is required")
	@Past(message = "birth date must not be future-dated")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "exactly 5 digits/chars only")
	private String postalCode;

	// using custom validation annotation
	// @CourseCode //use default value and parameter
	@CourseCode(value = { "AAA", "BBB" }, message = "Invalid course code")
	private String courseCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", freePasses=" + freePasses
				+ ", emailAddress=" + emailAddress + ", birthDate=" + birthDate + ", postalCode=" + postalCode
				+ ", courseCode=" + courseCode + "]";
	}
}
