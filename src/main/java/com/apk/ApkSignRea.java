package com.apk;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ApkSignRea {
	
	public static void main(String[] args) throws IOException{
		getSignaturesFromApk(new File("D:/glm/work/apache-tomcat-8.0.21/webapps/marketDoc/app/20150615/1434355034611/com.qihoo.antivirus_25.apk"));
	}
	/**
	　　* 从APK中读取签名
	　　* @param file
	　　* @return
	　　* @throws IOException
	　　*/
	private static List getSignaturesFromApk(File file) throws IOException {
		List signatures=new ArrayList();
		JarFile jarFile=new JarFile(file);
		try {
			JarEntry je=jarFile.getJarEntry("AndroidManifest.xml");
			byte[] readBuffer=new byte[8192];
			Certificate[] certs=loadCertificates(jarFile, je, readBuffer);
			if(certs != null) {
				for(Certificate c: certs) {
					String sig=toCharsString(c.getEncoded());
					signatures.add(sig);
				}
			}
		} catch(Exception ex) {
		}
		System.out.println(signatures.get(0));
		return signatures;
	}
	/**

	　　* 加载签名
	　　* @param jarFile
	　　* @param je
	　　* @param readBuffer
	　　* @return
	　　*/
		private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je,
				byte[] readBuffer) {
			try {
				InputStream is=jarFile.getInputStream(je);
				while(is.read(readBuffer, 0, readBuffer.length) != -1) {
				}
				is.close();
				return je != null ? je.getCertificates() : null;
			} catch(IOException e) {
			}
			return null;
		}
		/**
		 * 将签名转成转成可见字符串
		 * @param sigBytes
		 * @return
		 */
		private static String toCharsString(byte[] sigBytes) {
			byte[] sig=sigBytes;
			final int N=sig.length;
			final int N2=N * 2;
			char[] text=new char[N2];
			for(int j=0; j < N; j++) {
				byte v=sig[j];
				int d=(v >> 4) & 0xf;
				text[j * 2]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));
				d=v & 0xf;
				text[j * 2 + 1]=(char)(d >= 10 ? ('a' + d - 10) : ('0' + d));
			}
			return new String(text);
		}
}
