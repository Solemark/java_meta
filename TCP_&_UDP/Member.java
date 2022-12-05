import java.io.Serializable;
public class Member implements Serializable{
	private String fName;
	private String lName;
	private String address;
	private String pNumber;
	
	public Member() {}
	
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	
	public String getLName() {
		return lName;
	}
	public void setLName(String lName) {
		this.lName = lName;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPNumber() {
		return pNumber;
	}
	public void setPNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	
	@Override
	public String toString() {
		return fName + lName + address + pNumber;
	}
}
