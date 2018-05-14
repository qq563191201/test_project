package number.round;

public class is_float {
	public static void main(String []args){
        java.util.Scanner sca = new java.util.Scanner(System.in);
        System.out.println("输入一个数字");
        double a = sca.nextDouble();
        int b = (int)a;
        int c = b;
        if (c!=a){
            System.out.println("输入的是浮点型数字！");
        }else{
           System.out.println("输入的是整型数字！");
        }
    }
}
