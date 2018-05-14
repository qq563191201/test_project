package interfaces.person;

public class Parents implements Person{
	public void eat(){  
		System.out.println("家长去招待所饭馆吃饭！");  
	}  
	public void sleep(){  
		System.out.println("家长回招待所睡觉！");  
	}
	public static void main(String[] args)  
    {  
		  Person p=new Student();  
		  p.eat();  
		  p.sleep();  
		  p=new Teacher();  
		  p.eat();  
		  p.sleep();  
		  p=new Parents();  
		  p.eat();  
		  p.sleep();  
    }
}
