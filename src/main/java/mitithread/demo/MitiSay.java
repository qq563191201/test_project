package mitithread.demo;

class MitiSay extends Thread {
    public MitiSay(String threadName) {
        super(threadName);
    }
 
    public void run() {
        System.out.println(getName() + " 线程运行开始!");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
            try {
                sleep(10000*((int) Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + " 线程运行结束!");
    }
}
