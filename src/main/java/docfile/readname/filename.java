package docfile.readname;

import java.io.File;

public class filename {
	public static void main(String args[]) throws Exception{
		File file=new File("E:\\乐搜工作\\西南人影\\data\\2014-12-16_西南人影指导产品\\addrainpt");
		filename.showAllFiles(file);
//		String test[];
//		test=file.list();
//		for(int i=0;i<test.length;i++)
//		{
//			System.out.println(test[i]);
//		}
	}
	final static void showAllFiles(File dir) throws Exception{
		File[] fs = dir.listFiles();
		for(int i=0; i<fs.length; i++){
			if(fs[i].toString().contains(".doc")){
				System.out.println("src="+fs[i].getAbsolutePath()+",name="+fs[i].getName());
			}
			if(fs[i].isDirectory()){
				try{
					showAllFiles(fs[i]);
				}catch(Exception e){}
			}
		}
	}
}
