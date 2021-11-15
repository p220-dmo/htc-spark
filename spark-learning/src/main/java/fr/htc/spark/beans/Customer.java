package fr.htc.spark.beans;

public class Customer {
	private long customerId;
	private String education;
	
	
	
	
	public Customer(long customerId, String education) {
		this.customerId = customerId;
		this.education = education;
	}

	public static Customer parse(String customerAsString, String sep){
        String[] split = customerAsString.split(sep);
        return new Customer (
        		Integer.valueOf(split[0]), 
        		String.valueOf(split[16]) 
                );
    }

	public static Customer parse(String s) {
		return parse(s, ";");
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}



	
}
