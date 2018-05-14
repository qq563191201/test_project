package interfaces.traffic;

public class Plane implements Common{
	public double runTimer(double a, double b, double c)  {  
		return (a+ b + c);  
	}  
	public String getName(){  
		return "Plane";  
	}
	
	public static void main(String args[])  {  
        double A=3;  
        double B=5;  
        double C=6;  
        double v,t;  
        Common d=new Car();  
        v=d.runTimer(A,B,C);  
        t=1000/v;  
        System.out.println(d.getName()+"的平均速度: "+v+" km/h");  
        System.out.println(d.getName()+"的运行时间："+t+" 小时");  
        d=new Plane();  
		v=d.runTimer(10,30,40);  
		t=1000/v;  
		System.out.println(d.getName()+"的平均速度: "+v+" km/h");  
        System.out.println(d.getName()+"的运行时间："+t+" 小时");  
  } 
}
