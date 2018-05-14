package interfaces.traffic;

public class Car implements Common{
	public double runTimer(double a, double b, double c){
		return (a*b/c);
	}
	public String getName(){
		return "Car";
	}
}
