package mitithread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOfTestThreadPool {
	public static void main(String args[]) throws InterruptedException {
		  // only two threads

		  ExecutorService exec = Executors.newFixedThreadPool(20);
		  List<Long> list = new ArrayList<Long>();
		  for(int index = 0; index < 1000000; index++){
		   list.add(System.nanoTime());
		  }
		  
		  long start = System.currentTimeMillis();
		  for (Long long1 : list) {
		   final Long l = long1;
		   exec.execute(new Runnable(){

		    public void run() {
		     System.out.println(l);
		     try {
		      Thread.sleep(5000);
		     } catch (InterruptedException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }
		     
		    }});
		  }
		  // must shutdown
		  exec.shutdown();
		   long end = System.currentTimeMillis();
		   System.out.print("共计用时 ");
		   System.out.println(end  - start);
		 }
}
