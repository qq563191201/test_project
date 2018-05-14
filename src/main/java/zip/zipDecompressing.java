//package zip;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.zip.GZIPOutputStream;
//import java.util.zip.ZipInputStream;
//
//import org.apache.tools.tar.TarEntry;
//import org.apache.tools.tar.TarOutputStream;
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipFile;
//
//public class zipDecompressing {
//	 private static final int buffer = 2048;  
//	 
//	 private static int BUFFER = 1024 * 4; // 缓冲大小
//	 private static byte[] B_ARRAY = new byte[BUFFER];
//	 
//	public static void main(String[] args) throws IOException {  
//		zipDecompressing.unZip("D:\\Tomcat6.0\\webapps\\download\\衡阳1025.zip");
//	 }
//	
//	 /** 
//     * 解压指定zip文件 
//     * @param unZipfile 压缩文件的路径 
//     * @param destFile　　　解压到的目录　 
//     */  
//    public void unZip() {// unZipfileName需要解压的zip文件名
//    	 // TODO Auto-generated method stub  
//        long startTime=System.currentTimeMillis();  
//        try {  
//            ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
//                    "D:\\Tomcat6.0\\webapps\\download\\衡阳1025.zip"));//输入源zip路径  
//            BufferedInputStream Bin=new BufferedInputStream(Zin);  
//            String Parent="D:\\Tomcat6.0\\webapps\\download\\"; //输出路径（文件夹目录）  
//            File Fout=null;  
//            ZipEntry entry;  
//            try {  
////                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
////                    Fout=new File(Parent,entry.getName());  
////                    if(!Fout.exists()){  
////                        (new File(Fout.getParent())).mkdirs();  
////                    }  
////                    FileOutputStream out=new FileOutputStream(Fout);  
////                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
////                    int b;  
////                    while((b=Bin.read())!=-1){  
////                        Bout.write(b);  
////                    }  
////                    Bout.close();  
////                    out.close();  
////                    System.out.println(Fout+"解压成功");      
////                }  
//                Bin.close();  
//                Zin.close();  
//            } catch (IOException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }  
//        } catch (FileNotFoundException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        long endTime=System.currentTimeMillis();  
//        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
//    }
//    
//    /** 
//     * 解压Zip文件 
//     * @param path 文件目录 
//     */  
//    public static void unZip(String path)  
//        {  
//         int count = -1;  
//         String savepath = "";  
//  
//         File file = null;  
//         InputStream is = null;  
//         FileOutputStream fos = null;  
//         BufferedOutputStream bos = null;  
//  
//         savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; //保存解压文件目录  
//         new File(savepath).mkdir(); //创建保存目录  
//         ZipFile zipFile = null;  
//         try  
//         {  
//             zipFile = new ZipFile(path,"gbk"); //解决中文乱码问题  
//             Enumeration<?> entries = zipFile.getEntries();  
//  
//             while(entries.hasMoreElements())  
//             {  
//                 byte buf[] = new byte[buffer];  
//  
//                 ZipEntry entry = (ZipEntry)entries.nextElement();  
//  
//                 String filename = entry.getName();  
//                 boolean ismkdir = false;  
//                 if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
//                    ismkdir = true;  
//                 }  
//                 filename = savepath + filename;  
//  
//                 if(entry.isDirectory()){ //如果是文件夹先创建  
//                    file = new File(filename);  
//                    file.mkdirs();  
//                     continue;  
//                 }  
//                 file = new File(filename);  
//                 if(!file.exists()){ //如果是目录先创建  
//                    if(ismkdir){  
//                    new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
//                    }  
//                 }  
//                 file.createNewFile(); //创建文件  
//  
//                 is = zipFile.getInputStream(entry);  
//                 fos = new FileOutputStream(file);  
//                 bos = new BufferedOutputStream(fos, buffer);  
//  
//                 while((count = is.read(buf)) > -1)  
//                 {  
//                     bos.write(buf, 0, count);  
//                 }  
//                 bos.flush();  
//                 bos.close();  
//                 fos.close();  
//  
//                 is.close();  
//             }
//             zipFile.close();
//         }catch(IOException ioe){  
//             ioe.printStackTrace();  
//         }finally{  
//        	 try{  
//        		 if(bos != null){  
//        			 bos.close();  
//        		 }  
//        		 if(fos != null) {  
//        			 fos.close();  
//        		 }  
//        		 if(is != null){  
//        			 is.close();  
//        		 }  
//        		 if(zipFile != null){  
//        			 zipFile.close();  
//        		 }  
//        	 }catch(Exception e) {  
//        		 e.printStackTrace();  
//        	 }  
//         }
//	} 
//    
//    /*
//     * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
//     */
//    public void execute(String inputFileName, String targetFileName) {
//     File inputFile = new File(inputFileName);
//     String base = inputFileName
//       .substring(inputFileName.lastIndexOf("/") + 1);
//     TarOutputStream out = getTarOutputStream(targetFileName);
//     tarPack(out, inputFile, base);
//     try {
//      if (null != out) {
//       out.close();
//      }
//     } catch (IOException e) {
//      e.printStackTrace();
//     }
//     compress(new File(targetFileName));
//    }
//
//    /*
//     * 方法功能：打包多个文件或文件夹 参数：inputFileNameList 要打包的文件夹或文件的路径的列表 targetFileName
//     * 打包后的文件路径
//     */
//    public void execute(List<String> inputFileNameList, String targetFileName) {
//     TarOutputStream out = getTarOutputStream(targetFileName);
//
//     for (String inputFileName : inputFileNameList) {
//      File inputFile = new File(inputFileName);
//      String base = inputFileName.substring(inputFileName
//        .lastIndexOf("/") + 1);
//      tarPack(out, inputFile, base);
//     }
//
//     try {
//      if (null != out) {
//       out.close();
//      }
//     } catch (IOException e) {
//      e.printStackTrace();
//     }
//     compress(new File(targetFileName));
//    }
//
//    /*
//     * 方法功能：打包成tar文件 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
//     */
//
//    private void tarPack(TarOutputStream out, File inputFile, String base) {
//     if (inputFile.isDirectory()) // 打包文件夹
//     {
//      packFolder(out, inputFile, base);
//     } else // 打包文件
//     {
//      packFile(out, inputFile, base);
//     }
//    }
//
//    /*
//     * 方法功能：遍历文件夹下的内容，如果有子文件夹，就调用tarPack方法 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件
//     * base 打包文件中的路径
//     */
//    private void packFolder(TarOutputStream out, File inputFile, String base) {
//     File[] fileList = inputFile.listFiles();
//     try {
//      // 在打包文件中添加路径
//      out.putNextEntry(new TarEntry(base + "/"));
//     } catch (IOException e) {
//      e.printStackTrace();
//     }
//     base = base.length() == 0 ? "" : base + "/";
//     for (File file : fileList) {
//      tarPack(out, file, base + file.getName());
//     }
//    }
//
//    /*
//     * 方法功能：打包文件 参数：out 压缩后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
//     */
//    private void packFile(TarOutputStream out, File inputFile, String base) {
//     TarEntry tarEntry = new TarEntry(base);
//
//     // 设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
//     tarEntry.setSize(inputFile.length());
//     try {
//      out.putNextEntry(tarEntry);
//     } catch (IOException e) {
//      e.printStackTrace();
//     }
//     FileInputStream in = null;
//     try {
//      in = new FileInputStream(inputFile);
//     } catch (FileNotFoundException e) {
//      e.printStackTrace();
//     }
//     int b = 0;
//
//     try {
//      while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
//       out.write(B_ARRAY, 0, b);
//      }
//     } catch (IOException e) {
//      e.printStackTrace();
//     } catch (NullPointerException e) {
//      System.err
//        .println("NullPointerException info ======= [FileInputStream is null]");
//     } finally {
//      try {
//       if (null != in) {
//        in.close();
//       }
//       if (null != out) {
//        out.closeEntry();
//       }
//      } catch (IOException e) {
//
//      }
//     }
//    }
//
//    /*
//     * 方法功能：把打包的tar文件压缩成gz格式 参数：srcFile 要压缩的tar文件路径
//     */
//    private void compress(File srcFile) {
//     File target = new File(srcFile.getAbsolutePath() + ".gz");
//     FileInputStream in = null;
//     GZIPOutputStream out = null;
//     try {
//      in = new FileInputStream(srcFile);
//      out = new GZIPOutputStream(new FileOutputStream(target));
//      int number = 0;
//      while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
//       out.write(B_ARRAY, 0, number);
//      }
//     } catch (FileNotFoundException e) {
//      e.printStackTrace();
//     } catch (IOException e) {
//      e.printStackTrace();
//     } finally {
//      try {
//       if (in != null) {
//        in.close();
//       }
//
//       if (out != null) {
//        out.close();
//       }
//      } catch (IOException e) {
//       e.printStackTrace();
//      }
//     }
//    }
//
//    /*
//     * 方法功能：获得打包后文件的流 参数：targetFileName 打包后文件的路径
//     */
//    private TarOutputStream getTarOutputStream(String targetFileName) {
//     // 如果打包文件没有.tar后缀名，就自动加上
//     targetFileName = targetFileName.endsWith(".tar") ? targetFileName
//       : targetFileName + ".tar";
//     FileOutputStream fileOutputStream = null;
//     try {
//      fileOutputStream = new FileOutputStream(targetFileName);
//     } catch (FileNotFoundException e) {
//      e.printStackTrace();
//     }
//     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
//       fileOutputStream);
//     TarOutputStream out = new TarOutputStream(bufferedOutputStream);
//
//     // 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
//     out.setLongFileMode(TarOutputStream.LONGFILE_GNU);
//     return out;
//    }
//    
//}
