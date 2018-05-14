package mitithread.basics;

public class thread_basic{
	
	public static void main(String[] args) {
	       
        thread1 t1 = new thread1();                 
                               //用Thread类的子类创建线程 t1即自定义的线程类
        Thread t2 = new Thread(new thread2());  
                               //用Runnable接口类的对象创建线程，即Java提供的定义方式
        t1.start();        //strat()方法启动线程
        t2.start();
       
    }    
}
