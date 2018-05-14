package mitithread.基础;

/**JAVA并发编程实践
 * 线程的强制执行
 * @title
 * @description 
 * @author Xuxiaobing
 * @date 2014-12-30 下午04:19:33 
 * @version v1.0
 */
public class 强制执行demo implements Runnable{
	public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
 
    public static void main(String[] args) {
    	强制执行demo he = new 强制执行demo();
        Thread demo = new Thread(he,"线程");
        demo.start();
//        for(int i=0;i<50;++i){
//            if(i>10){
//                try{
//                    demo.join();  //强制执行demo
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("main 线程执行-->"+i);
//        }
    }
}
