package mitithread.基础.同步;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Info {
	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
//    public synchronized void set(String name, int age){
//        this.name=name;
//        try{
//            Thread.sleep(100);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.age=age;
//    }
//    public synchronized void get(){
//        try{
//            Thread.sleep(100);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = format.format(new Date());
//        System.out.println(date+"<<<"+this.getName()+"<===>"+this.getAge());
//    }
    public synchronized void set(String name, int age){
        if(!flag){
            try{
                super.wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.name=name;
        try{
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.age=age;
        flag=false;
        super.notify();
    }
    public synchronized void get(){
        if(flag){
            try{
                super.wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
         
        try{
            Thread.sleep(100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        System.out.println(date+"<<<<"+this.getName()+"<===>"+this.getAge());
        flag=true;
        super.notify();
    }
    private String name = "Rollen";
    private int age = 20;
    private boolean flag=false;
}
