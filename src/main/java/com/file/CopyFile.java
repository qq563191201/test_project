package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {
	
	public static void main(String[] args){
		File newFile = new File("D:/apk/ttt/");
		if(!newFile.exists()){
			newFile.mkdirs();
		}
		fileChannelCopy(new File("D:/glm/work/apache-tomcat-8.0.21/webapps/marketDoc/uploadtmp/pic/2015-07-08/253beb2f857b4953ad194406c01c0711.png"), 
				new File("D:/apk/ttt/11.png"));
	}
	
	public static void fileChannelCopy(File s, File t) {

        FileInputStream fi = null;

        FileOutputStream fo = null;

        FileChannel in = null;

        FileChannel out = null;

        try {

            fi = new FileInputStream(s);

            fo = new FileOutputStream(t);

            in = fi.getChannel();//得到对应的文件通道

            out = fo.getChannel();//得到对应的文件通道

            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                fi.close();

                in.close();

                fo.close();

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }
}
