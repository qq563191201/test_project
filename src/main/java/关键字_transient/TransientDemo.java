package 关键字_transient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;

import util.Util;


public class TransientDemo {
	/** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
    private  transient String name;  
    private String password;  
      
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    /** 
     * @param args 
     * @throws IOException  
     * @throws FileNotFoundException  
     * @throws ClassNotFoundException  
     */  
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {  
        // TODO Auto-generated method stub  
//        String path="D:"+File.separator+"object.txt";  
//        File file=new File(path);  
//        TransientDemo transientDemo=new TransientDemo();  
//        transientDemo.setName("姓名");  
//        transientDemo.setPassword("密码");  
//        ObjectOutput output=new ObjectOutputStream(new FileOutputStream(file));  
//        output.writeObject(transientDemo);  
//        ObjectInput input=new ObjectInputStream(new FileInputStream(file));  
//        TransientDemo demo=(    TransientDemo )input.readObject();  
//        System.out.println(demo.getName()+demo.getPassword());  
    	Timestamp nowtime = new Timestamp(System.currentTimeMillis());
		String now = Util.format1.format(nowtime);//准确到小时
		String nowmm = Util.f1.format(nowtime);//准确到分
    	if(Long.valueOf(nowmm)>Long.valueOf(now+"20")){
    		System.out.println("1111");
		}
    }  
}
