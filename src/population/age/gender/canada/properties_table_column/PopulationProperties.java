package populcation.age.gender.canada.properties_table_column;

public class PopulationProperties {

	private int year,number; 
	private String geo, gender,agegroup;
	private double median;
	
	public PopulationProperties(String geo, String gender, String agegroup,double median,int number) {

		this.setGeo(geo);
		this.setGender(gender);
		this.setAgegroup(agegroup);
		this.setMedian(median);
		this.setNumber(number);
	}
	public PopulationProperties(int year, String gender, String agegroup,double median,int number) {

		this.setYear(year);
		this.setGender(gender);
		this.setAgegroup(agegroup);
		this.setMedian(median);
		this.setNumber(number);
	} 

	public PopulationProperties(String geo,int year, String agegroup,double median,int number) {

		this.setYear(year);
		this.setGeo(geo);
		this.setAgegroup(agegroup);
		this.setMedian(median);
		this.setNumber(number);
	}
	
	public PopulationProperties(String geo, String gender,int year, double median,int number) {
		
		this.setYear(year);
		this.setGender(gender);
		this.setGeo(geo);
		this.setMedian(median);
		this.setNumber(number);
	}
	
	public PopulationProperties(int year, String geo,String gender,int number) {
		
		this.setYear(year);
		this.setGender(gender);
		this.setGeo(geo);
		this.setNumber(number);
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgegroup() {
		return agegroup;
	}
	public void setAgegroup(String agegroup) {
		this.agegroup = agegroup;
	}
	public double getMedian() {
		return median;
	}
	public void setMedian(double median) {
		this.median = median;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
