package mitithread.基础.sleep;

/**
 * 
 * @title 线程的优先级
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-31 下午02:44:44 
 * @version v1.0
 */
public class 线程的优先级 implements Runnable{
	public void run() {
        for(int i=0;i<5;++i){
            System.out.println(Thread.currentThread().getName()+"运行"+i);
        }
    }
	 
	public static void main(String[] args) {
        Thread h1=new Thread(new 线程的优先级(),"A");
        Thread h2=new Thread(new 线程的优先级(),"B");
        Thread h3=new Thread(new 线程的优先级(),"C");
        h1.setPriority(8);
        h2.setPriority(2);
        h3.setPriority(6);
        h1.start();
        h2.start();
        h3.start();   
    }
}
