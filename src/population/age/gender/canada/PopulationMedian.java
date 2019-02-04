package populcation.age.gender.canada;

public class PopulationMedian implements Population {

	private int year;
	private String GEO;
	private String gender;
	private String ageGroup;
	private double med;

	public PopulationMedian(int year, String GEO, String gender, String ageGroup, double med) {
		setYear(year);
		setGEO(GEO);
		setGender(gender);
		setAgeGroup(ageGroup);
		setMed(med);
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

	public double getMed() {
		return med;
	}

	public void setMed(double med) {
		this.med = med;
	}
}
