package com.timeformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateTest {
	private SimpleDateFormat dateFormat ;
    public static void main(String[] args) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date tomorrow = new Date(today.getTime()+1000*60*60*24);
        System.out.println(today); // 今天是2010-01-11
        System.out.println(tomorrow); // 明天是2010-01-11
//        Thread thread1 = new Thread(new Thread1(dateFormat,today));
//        Thread thread3 = new Thread(new Thread1(dateFormat,today));
//        thread3.start();
//        Thread thread2 = new Thread(new Thread2(dateFormat,tomorrow));
//        thread2.start();
    }
    
    class Thread1 implements Runnable{
        private SimpleDateFormat dateFormat;
        private Date date;
        public Thread1(SimpleDateFormat dateFormat,Date date){
            this.dateFormat = dateFormat;
            this.date = date;
        }
        public void run() {
            for(;;){// 一直循环到出问题为止吧。
                String strDate = dateFormat.format(date);
                // 如果不等于2010-01-11，证明出现线程安全问题了！！！！
                if(!"2010-01-11".equals(strDate)){
                    System.err.println("today="+strDate);
                    System.exit(0);
                }
            }
        }
    }
    class Thread2 implements Runnable{
        private SimpleDateFormat dateFormat;
        private Date date;
        public Thread2(SimpleDateFormat dateFormat,Date date){
            this.dateFormat = dateFormat;
            this.date = date;
        }
        public void run() {
            for(;;){
                String strDate = dateFormat.format(date);
                if(!"2010-01-12".equals(strDate)){
                    System.err.println("tomorrow="+strDate);
                    System.exit(0);
                }
            }
        }
    }
}
