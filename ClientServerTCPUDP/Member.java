import java.io.Serializable;

/**
 * Member class used by server
 */
public class Member implements Serializable {
	private String firstName;
	private String lastName;
	private String address;
	private String number;

	/**
	 * get current member firstName
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set current member firstName
	 * 
	 * @param firstName
	 * @return void
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get current member lastName
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set current member lastName
	 * 
	 * @param lastName
	 * @return void
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get current member address
	 * 
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set current member address
	 * 
	 * @param address
	 * @return void
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get current member phone number
	 * 
	 * @return String
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * set current member phone number
	 * 
	 * @param number
	 * @return void
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}
