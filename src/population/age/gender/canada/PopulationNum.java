package populcation.age.gender.canada;

public class PopulationNum implements Population {
 
	private int year;
	private String GEO;
	private String gender;
	private String ageGroup;
	private int num;
	
	public PopulationNum() {}
	public PopulationNum(int year, String GEO, String gender,String ageGroup, int num) {
		setYear(year);
		setGEO(GEO);
		setGender(gender);
		setAgeGroup(ageGroup);
		setNum(num);
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGEO() {
		return GEO;
	}
	public void setGEO(String gEO) {
		GEO = gEO;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public static PopulationNum creatPopulationNum (){
		PopulationNum pop = new PopulationNum();
		pop.setYear(0);
		pop.setNum(0);
		return pop;
	}
	
	
}
