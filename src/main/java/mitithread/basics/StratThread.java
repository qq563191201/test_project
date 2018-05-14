package mitithread.basics;

//定义Thread 类的子类
class ThreadText extends Thread { 
//    重写run()方法
    public void run()
    {
        for (int a = 0 ; a <10 ; a ++)
        {
            System.out.println( currentThread().getName() + ":" + a); 
        }
    }
}
public class StratThread {
    
    public static void main(String args[])
    {
//        创建Thread 类的子类的实例
        ThreadText t = new ThreadText() ;
//        调用线程的start()方法来启动该线程
        t.start() ; 
        
//         主线程调用用户线程的对象run()方法。        
        t.run() ; 
    }
}
