package 常量;

/**
 * 常量就是从程序开始运行到结束都不变的量。
 * @author Xuxiaobing
 *
 */
public class var {
	//这里的 x 是类常量，所以无论是哪个对象的引用，它的值终究不变
	static final int Y=20;
	//这里的 x 是一个常量，但是是在某个方法内的常量，也可以称为成员常量（作者给它取的名字）
	public static void main(String[] args){
		final int X=20;
        System.out.println("常量 Y="+Y);
        int x;
        double Z = 0;
        x=(int)34.56+(int)11.2;
        System.out.println("x="+x);
        System.out.println("Z="+Z);
	}
	
	
}
