package vehicleenquiry.domain;

public class VehicleSearchResultDetails {
	String expectedVRN;
	String expectedMake;
	String expectedColour;
	String actualVRN;
	String actualMake;
	String actualColour;
	String assertVRN;
	String assertMake;
	String assertColour;
	String message;

	public String getExpectedVRN() {
		return expectedVRN;
	}

	public void setExpectedVRN(String expectedVRN) {
		this.expectedVRN = expectedVRN;
	}

	public String getExpectedMake() {
		return expectedMake;
	}

	public void setExpectedMake(String expectedMake) {
		this.expectedMake = expectedMake;
	}

	public String getExpectedColour() {
		return expectedColour;
	}

	public void setExpectedColour(String expectedColour) {
		this.expectedColour = expectedColour;
	}

	public String getActualVRN() {
		return actualVRN;
	}

	public void setActualVRN(String actualVRN) {
		this.actualVRN = actualVRN;
	}

	public String getActualMake() {
		return actualMake;
	}

	public void setActualMake(String actualMake) {
		this.actualMake = actualMake;
	}

	public String getActualColour() {
		return actualColour;
	}

	public void setActualColour(String actualColour) {
		this.actualColour = actualColour;
	}

	public String getAssertVRN() {
		return assertVRN;
	}

	public void setAssertVRN(String assertVRN) {
		this.assertVRN = assertVRN;
	}

	public String getAssertMake() {
		return assertMake;
	}

	public void setAssertMake(String assertMake) {
		this.assertMake = assertMake;
	}

	public String getAssertColour() {
		return assertColour;
	}

	public void setAssertColour(String assertColour) {
		this.assertColour = assertColour;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
