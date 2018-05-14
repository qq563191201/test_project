package interfaces.annimal;

public class Pigeon extends Bird implements  Flyanimal{
	public void fly(){  
		System.out.println("pigeon  can fly");  
	}  
	public void egg(){  
		System.out.println("pigeon  can lay  eggs ");  
	}
	
	public static void main(String args[]){  
	     Ant a=new Ant();  
	     a.fly();  
	     System.out.println("Ant's legs are"+ a.legnum);  
	     Pigeon p= new Pigeon();  
	     p.fly();  
	     p.egg();  
	} 
}
