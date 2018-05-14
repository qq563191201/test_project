package com.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class FtpTest {
	public static void main(String[] args) {
		// 构造Properties对象  
        Properties properties = new Properties();  
		 InputStream is = null;  
	        try {  
	            // 获取配置文件输入流  
	            is = FtpTest.class.getResourceAsStream("/application.properties");  
	            // 加载配置文件  
	            properties.load(is);  
	            // 读取配置文件  
	           String port = (String) properties.get("initialSize"); // 设置端口  
	           System.out.println(port);
//	            username = (String) properties.get("username"); // 设置用户名  
//	            password = (String) properties.get("password"); // 设置密码  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 判断输入流是否为空  
	            if (null != is) {  
	                try {  
	                    // 关闭输入流  
	                    is.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
//        testUpload();
//        testDownload();
    }

    /**
     * FTP上传单个文件测试
     */
    public static void testUpload() {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        try {
            ftpClient.connect("192.168.10.8", 22);
            ftpClient.login("phpadmin", "admin");

            File srcFile = new File("E:\\FISH\\gameserver_new\\output\\NewFishingDataServer\\NewFishingDataServer_lib\\NewFishingCommon-1.0.0.2.jar");
            fis = new FileInputStream(srcFile);
            //设置上传目录
            ftpClient.changeWorkingDirectory("E:\\fishserver\\server\\FishingDataServer\\NewFishingDataServer_lib");
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile("NewFishingCommon-1.0.0.2.jar", fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }
    
    /** 
     *  
     * <p>删除ftp上的文件</p> 
     * @param srcFname 
     * @return true || false 
     */  
    public boolean removeFile(FTPClient ftpClient, String srcFname){  
        boolean flag = false;  
        if( ftpClient!=null ){  
            try {  
                flag = ftpClient.deleteFile(srcFname);  
            } catch (IOException e) {  
                e.printStackTrace();  
                this.closeCon(ftpClient);  
            }  
        }  
        return flag;  
    }  
      
    /** 
     *<p>销毁ftp连接</p>  
     */  
    public void closeCon(FTPClient ftpClient){  
        if(ftpClient !=null){  
            if(ftpClient.isConnected()){  
                try {  
                    ftpClient.logout();  
                    ftpClient.disconnect();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            }  
        }  
    }  

    /**
     * FTP下载单个文件测试
     */
    public static void testDownload() {
        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect("192.168.14.117");
            ftpClient.login("admin", "123");

            String remoteFileName = "/admin/pic/3.gif";
            fos = new FileOutputStream("c:/down.gif");

            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    } 
}
