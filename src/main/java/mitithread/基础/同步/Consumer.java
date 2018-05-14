package mitithread.基础.同步;

/**
 * 消费者类
 * @title
 * @description 
 * @author Xuxiaobing
 * @date 2015-1-4 下午05:40:44 
 * @version v1.0
 */
public class Consumer implements Runnable{
	private Info info = null;
	 
    public Consumer(Info info) {
        this.info = info;
    }
 
    public void run() {
        for (int i = 0; i < 25; ++i) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.info.get();
        }
    }
    
    public static void main(String[] args) {
        Info info = new Info();
        Producer pro = new Producer(info);
        Consumer con = new Consumer(info);
        new Thread(pro).start();
        new Thread(con).start();
    }
}
