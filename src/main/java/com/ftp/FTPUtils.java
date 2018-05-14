package com.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
  




import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPReply;  

public class FTPUtils {
	private static FTPUtils ftpUtils;  
    private FTPClient ftpClient;  

    private String serverName;//服务器ip
    private String port; // 服务器端口  
    private String username; // 用户登录名  
    private String password; // 用户登录密码  
    private static String COMMON_JAR = "NewFishingCommon-1.0.0.2.jar";
    private static String GAME_JAR = "NewFishingGameServer-0.0.2-SNAPSHOT.jar";
    private static String GAME_BUF_JAR = "NewProtoBufferData-0.0.2-SNAPSHOT.jar";
    private static String GAME_LIB = "NewFishingGameServer_lib";
    private static String SERVER_SRC = "server\\";
    private static String DATA_NAME = "NewFishingDataServer";
    private static String DATA_JAR = "NewFishingDataServer-2.3.3.jar";
    private static String HUB_NAME = "NewFishingHubServer";
    private static String HUB_JAR = "NewFishingHubServer-0.0.2-SNAPSHOT.jar";
    private static String HUB_BUF_JAR = "NewProtoBufferData-0.0.2-SNAPSHOT.jar";
    private static String LOG_NAME = "NewFishingLoginServer";
    private static String LOG_JAR = "NewFishingLoginServer-0.0.2-SNAPSHOT.jar";
    private static String MATCH_NAME = "NewFishingMatchserver";
    private static String MATCH_JAR = "NewFishingMatchServer-0.0.2-SNAPSHOT.jar";
    
      
    private InputStream is; // 文件下载输入流  
    
    public static void main(String[] args) throws FileNotFoundException {
    	FileInputStream fis = null;
    	FileInputStream fisGame = null;
    	try {
    		FTPUtils getInstance = getInstance();
    		
    		String fileSrc = "F:\\programeBY\\zzby_server\\output\\";
//    		File dataCommonFile = new File(fileSrc+DATA_NAME+"\\NewFishingDataServer_lib\\"+COMMON_JAR);
//    		fis = new FileInputStream(dataCommonFile);
//    		getInstance.storeFile(SERVER_SRC+DATA_NAME+"\\NewFishingDataServer_lib\\",COMMON_JAR,fis);
//    		File dataFile = new File(fileSrc+DATA_NAME+"\\NewFishingDataServer_lib\\"+DATA_JAR);
//    		fis = new FileInputStream(dataFile);
//    		getInstance.storeFile(SERVER_SRC+DATA_NAME,DATA_JAR,fis);
//    		
//    		File hubCommonFile = new File(fileSrc+HUB_NAME+"\\"+HUB_NAME+"_lib\\"+COMMON_JAR);
//    		fis = new FileInputStream(hubCommonFile);
//    		getInstance.storeFile(SERVER_SRC+HUB_NAME+"\\"+HUB_NAME+"_lib\\",COMMON_JAR,fis);
//    		File hubFile = new File(fileSrc+HUB_NAME+"\\"+HUB_NAME+"_lib\\"+HUB_JAR);
//    		fis = new FileInputStream(hubFile);
//    		getInstance.storeFile(SERVER_SRC+HUB_NAME,HUB_JAR,fis);
//    		hubFile = new File(fileSrc+HUB_NAME+"\\"+HUB_NAME+"_lib\\"+HUB_BUF_JAR);
//    		fis = new FileInputStream(hubFile);
//    		getInstance.storeFile(SERVER_SRC+HUB_NAME,HUB_JAR,fis);
    		
    		
    		
    		
    	File srcFile = new File(fileSrc+"NewFishingGameServer\\NewFishingGameServer_lib\\"+COMMON_JAR);
//        fis = new FileInputStream(srcFile);
        //data
//        String dataStr = "FishingDataServer";
//        getInstance.deleteFile("/server/"+dataStr+"/NewFishingDataServer_lib/", COMMON_JAR);
//        getInstance.storeFile("/server/"+dataStr+"/NewFishingDataServer_lib",COMMON_JAR,fis);
        
        //game
        File gameFile = new File("F:\\programeBY\\zzby_server\\output\\NewFishingGameServer\\"+GAME_JAR);
        String gameName = "FishingGameServer";
        
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName,GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"1",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"2",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"3",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_2_307",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_2_438",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_2_439",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_2_441",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_3_435",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_3_436",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_3_437",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_4_430",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile(SERVER_SRC+gameName+"_4_431",GAME_JAR,fisGame);
        fisGame = new FileInputStream(gameFile);
        getInstance.storeFile("/server/NewFishingGameServer_Master",GAME_JAR,fisGame);
        
fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"1/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"2/"+GAME_LIB,COMMON_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"3/"+GAME_LIB,COMMON_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.deleteFile(SERVER_SRC+gameName+"_2_307/"+GAME_LIB, COMMON_JAR);
getInstance.storeFile(SERVER_SRC+gameName+"_2_307/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_438/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_439/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_441/"+GAME_LIB,COMMON_JAR,fis);
        
fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_3_435/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_3_436/"+GAME_LIB,COMMON_JAR,fis);

fis = new FileInputStream(srcFile);

getInstance.storeFile("/server/"+gameName+"_3_437/"+GAME_LIB,COMMON_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_4_430/"+GAME_LIB,COMMON_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_4_431/"+GAME_LIB,COMMON_JAR,fis);
        
        
fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/NewFishingGameServer_Master/"+GAME_LIB,COMMON_JAR,fis);

//////////////////////////////////////////////
srcFile = new File(fileSrc+"NewFishingGameServer\\NewFishingGameServer_lib\\"+GAME_BUF_JAR);
fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"1/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"2/"+GAME_LIB,GAME_BUF_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"3/"+GAME_LIB,GAME_BUF_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.deleteFile(SERVER_SRC+gameName+"_2_307/"+GAME_LIB, GAME_BUF_JAR);
getInstance.storeFile(SERVER_SRC+gameName+"_2_307/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_438/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_439/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile(SERVER_SRC+gameName+"_2_441/"+GAME_LIB,GAME_BUF_JAR,fis);
        
fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_3_435/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_3_436/"+GAME_LIB,GAME_BUF_JAR,fis);

fis = new FileInputStream(srcFile);

getInstance.storeFile("/server/"+gameName+"_3_437/"+GAME_LIB,GAME_BUF_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_4_430/"+GAME_LIB,GAME_BUF_JAR,fis);


fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/"+gameName+"_4_431/"+GAME_LIB,GAME_BUF_JAR,fis);
        
        
fis = new FileInputStream(srcFile);
getInstance.storeFile("/server/NewFishingGameServer_Master/"+GAME_LIB,GAME_BUF_JAR,fis);
        
        
        //login
//        fis = new FileInputStream(srcFile);
//        getInstance.storeFile("/server/FishingLoginServer/NewFishingLoginServer_lib",COMMON_JAR,fis);
    	}finally {
    		ftpUtils.logout();
    	}
    }
      
    /** 
     * 私有构造方法 
     */  
    private FTPUtils() {  
        initConfig();  
        if (null == ftpClient) {  
            ftpClient = new FTPClient();  
        }  
    }  
  
    /** 
     * 获取FTPUtils对象实例 
     * @return 
     *      FTPUtils对象实例 
     */  
    public synchronized static FTPUtils getInstance () {  
        if (null == ftpUtils) {  
            ftpUtils = new FTPUtils();  
        }  
        return ftpUtils;  
    }  
      
    /** 
     * 初始化FTP服务器连接属性 
     */  
    public void initConfig () {  
        // 构造Properties对象  
        Properties properties = new Properties();  
          
        // 定义配置文件输入流  
        InputStream is = null;  
        try {  
            // 获取配置文件输入流  
            is = FTPUtils.class.getResourceAsStream("/application.properties");  
            // 加载配置文件  
            properties.load(is);  
            // 读取配置文件  
            serverName = (String) properties.get("ftp_ip"); // 设置端口  
            port = (String) properties.get("ftp_port"); // 设置端口  
            username = (String) properties.get("ftp_username"); // 设置用户名  
            password = (String) properties.get("ftp_password"); // 设置密码  
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
    }  
      
    /** 
     * 连接（配置通用连接属性）至服务器 
     *  
     * @param serverName 
     *      服务器名称 
     * @param remotePath 
     *      当前访问目录 
     * @return 
     *      <b>true</b>：连接成功 
     *      <br/> 
     *      <b>false</b>：连接失败 
     */  
    public boolean connectToTheServer (String remotePath) {  
        // 定义返回值  
        boolean result = false;  
        try {  
            // 连接至服务器，端口默认为21时，可直接通过URL连接  
            ftpClient.connect(serverName, Integer.parseInt(port));  
            // 登录服务器  
            ftpClient.login(username, password);  
            // 判断返回码是否合法  
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {  
                // 不合法时断开连接  
                ftpClient.disconnect();  
                // 结束程序  
                return result;  
            }  
            // 设置文件操作目录  
            result = ftpClient.changeWorkingDirectory(remotePath);  
            // 设置文件类型，二进制  
            result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            // 设置缓冲区大小  
            ftpClient.setBufferSize(3072);  
            // 设置字符编码  
            ftpClient.setControlEncoding("UTF-8");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
      
    /** 
     * 上传文件至FTP服务器 
     *  
     * @param serverName 
     *      服务器名称 
     * @param storePath 
     *      上传文件存储路径 
     * @param fileName 
     *      上传文件存储名称 
     * @param is 
     *      上传文件输入流 
     * @return 
     *      <b>true</b>：上传成功 
     *      <br/> 
     *      <b>false</b>：上传失败 
     */  
    public boolean storeFile (String storePath, String fileName, InputStream is) {  
        boolean result = false;  
        try {  
            // 连接至服务器  
            result = connectToTheServer(storePath);  
            // 判断服务器是否连接成功  
            if (result) {  
                // 上传文件  
                result = ftpClient.storeFile(fileName, is);  
            }  
            // 关闭输入流  
            is.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 判断输入流是否存在  
            if (null != is) {  
                try {  
                    // 关闭输入流  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            // 登出服务器并断开连接  
//            ftpUtils.logout();  
        }  
        return result;  
    }  
      
    /** 
     * 下载FTP服务器文件至本地<br/> 
     * 操作完成后需调用logout方法与服务器断开连接 
     *  
     * @param serverName 
     *      服务器名称 
     * @param remotePath 
     *      下载文件存储路径 
     * @param fileName 
     *      下载文件存储名称 
     * @return 
     *      <b>InputStream</b>：文件输入流 
     */  
    public InputStream retrieveFile (String remotePath, String fileName) {  
        try {  
            boolean result = false;  
            // 连接至服务器  
            result = connectToTheServer(remotePath);  
            // 判断服务器是否连接成功  
            if (result) {  
                // 获取文件输入流  
                is = ftpClient.retrieveFileStream(fileName);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return is;  
    }  
      
    /** 
     * 删除FTP服务器文件 
     *  
     * @param serverName 
     *      服务器名称 
     * @param remotePath 
     *      当前访问目录 
     * @param fileName 
     *      文件存储名称 
     * @return 
     *      <b>true</b>：删除成功 
     *      <br/> 
     *      <b>false</b>：删除失败 
     */  
    public boolean deleteFile (String remotePath, String fileName) {  
        boolean result = false;  
        // 连接至服务器  
        result = connectToTheServer(remotePath);  
        // 判断服务器是否连接成功  
        if (result) {  
            try {  
                // 删除文件  
                result = ftpClient.deleteFile(fileName);  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                // 登出服务器并断开连接  
//                ftpUtils.logout();  
            }  
        }  
        return result;  
    }  
      
    /** 
     * 检测FTP服务器文件是否存在 
     *  
     * @param serverName 
     *      服务器名称 
     * @param remotePath 
     *      检测文件存储路径 
     * @param fileName 
     *      检测文件存储名称 
     * @return 
     *      <b>true</b>：文件存在 
     *      <br/> 
     *      <b>false</b>：文件不存在 
     */  
    public boolean checkFile (String remotePath, String fileName) {  
        boolean result = false;  
        try {  
            // 连接至服务器  
            result = connectToTheServer(remotePath);  
            // 判断服务器是否连接成功  
            if (result) {  
                // 默认文件不存在  
                result = false;  
                // 获取文件操作目录下所有文件名称  
                String[] remoteNames = ftpClient.listNames();  
                // 循环比对文件名称，判断是否含有当前要下载的文件名  
                for (String remoteName: remoteNames) {  
                    if (fileName.equals(remoteName)) {  
                        result = true;  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 登出服务器并断开连接  
//            ftpUtils.logout();  
        }  
        return result;  
    }  
  
    /** 
     * 登出服务器并断开连接 
     *  
     * @param ftp 
     *      FTPClient对象实例 
      * @return 
     *      <b>true</b>：操作成功 
     *      <br/> 
     *      <b>false</b>：操作失败 
     */  
    public boolean logout () {  
        boolean result = false;  
        if (null != is) {  
            try {  
                // 关闭输入流  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        if (null != ftpClient) {  
            try {  
                // 登出服务器  
                result = ftpClient.logout();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                // 判断连接是否存在  
                if (ftpClient.isConnected()) {  
                    try {  
                        // 断开连接  
                        ftpClient.disconnect();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return result;  
    }  
}
