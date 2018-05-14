//package docfile;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.ConnectException;
//
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.DocumentFormat;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
//import com.aspose.words.Document;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
//
///**
// * 实现doc、docx转换为pdf工具类
// * @author xqls
// *
// */
//public class doctopdf {
//	
//	private static final Logger logger = LoggerFactory.getLogger(doctopdf.class);
//	
//	private static int[] ports = new int[]{8200,8300,8400,8500,8600};
//	
//	static{
//		try {
//			InputStream inStream = Document.class.getResourceAsStream("/resources/license.xml");
//			License license = new License();
//			license.setLicense(inStream);
//		} catch (Exception e) {
//			logger.warn("初始化化Aspose组件失败...{}",e);
//		}
//	}
//	
//	
//
//	public synchronized static boolean docToPdf(String inputFile, String pdfFile)
//			throws Exception {
//		try {
//			Document document = new Document(inputFile);
//			document.save(pdfFile,SaveFormat.PDF);
//		} catch (Exception e) {
//			logger.error("Aspose convert2PDF...{}",e);
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * word to pdf 
//	 * @param xlsfile 需要转的文件来源
//	 * @param targetfile 转换成功为pdf路径
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean docToPdf2(String xlsfile, String targetfile)throws Exception {
//		File xlsf = new File(xlsfile);
//		File targetF = new File(targetfile);
//		DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
//		DocumentFormat pdfFormat = formatReg.getFormatByFileExtension("pdf");
//		DocumentFormat wordFormat = formatReg.getFormatByFileExtension("doc");
//		InputStream inputStream = new FileInputStream(xlsf);
//		OutputStream outputStream = new FileOutputStream(targetF);
//		OpenOfficeConnection connection = null;
//		DocumentConverter converter = null;
//		try {
//			connection = new SocketOpenOfficeConnection(8700);
//			connection.connect();
//			converter = new OpenOfficeDocumentConverter(connection);
//			converter.convert(inputStream, wordFormat, outputStream, pdfFormat);
//			return true;
//		} catch (ConnectException e) {
//			logger.error("连接officeConnection失败:{}",e.getMessage());
//			boolean isTag = false;
//			for(int port : ports){
//				try {
//					connection = new SocketOpenOfficeConnection(port);
//					connection.connect();
//					converter = new OpenOfficeDocumentConverter(connection);
//					converter.convert(inputStream, wordFormat, outputStream, pdfFormat);
//					isTag = true;
//				} catch (Exception ex) {
//					logger.error("连接officeConnection失败:{}",ex);
//					isTag = false;
//				}
//				if(isTag) break;
//			}
//			return isTag;
//		} finally {
//			try {
//				IOUtils.closeQuietly(inputStream);
//				IOUtils.closeQuietly(outputStream);
//				if (connection != null){
//					connection.disconnect();
//				}
//			} finally {
//				connection = null;
//			}
//		}
//	}
//	final static void showAllFiles(File dir) throws Exception{
//		File[] fs = dir.listFiles();
//		if(fs!=null){
//			for(int i=0; i<fs.length; i++){
//				if(fs[i].toString().contains(".doc")){
//					System.out.println("src="+fs[i].getAbsolutePath()+",name="+fs[i].getName());
//					String add_file = "E:\\Game\\"
//						+System.currentTimeMillis()+".pdf";
//					doctopdf.docToPdf(fs[i].getAbsolutePath(), add_file);
//				}
//				if(fs[i].isDirectory()){
//					try{
//						showAllFiles(fs[i]);
//					}catch(Exception e){}
//				}
//			}
//		}else{
//			System.out.println("没文件");
//		}
//	}
//	public static void main(String[] args) throws Exception {
//		File file=new File("E:/Game/word/");
//		doctopdf.showAllFiles(file);
//	}
//}
