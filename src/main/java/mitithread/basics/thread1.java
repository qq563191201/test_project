package mitithread.basics;

public class thread1 extends Thread {

    @Override
    public void run() {

        try {
            Thread.sleep(1);                         //主线程挂起7秒,之所以加这个方法是为了更好的理解线程．后面我会说的
            System.out.println("one is start......");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
