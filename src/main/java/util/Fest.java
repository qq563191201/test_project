package util;

public class Fest {
	private String day;
	private String type;
	private String name;
	
	public static class Cp {

		public static Fest FactoryFest() {
			return new Fest();
		}


	}
	public static Fest FactoryFest() {
		return new Fest();
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
