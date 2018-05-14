package com.mac;

import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;

public class UdpGetClientMacAddr {
	private String getMacAddressIP(String remotePcIP){ 
		String str=""; 
		String macAddress=""; 
		try { 
		Process pp= Runtime.getRuntime().exec ("nbtstat -A " + remotePcIP); 
		InputStreamReader ir = new InputStreamReader(pp.getInputStream()); 
		LineNumberReader input = new LineNumberReader (ir); 
		for (int i = 1; i <100; i++) { 
		str=input.readLine(); 
		if (str!=null) { 
		if(str.indexOf("MAC Address")>1){  
		macAddress=str.substring(str.indexOf("MAC Address")+14,str.length()); 
		break; 
		} 
		} 
		} 
		} catch (IOException ex) {} 
		return macAddress; 
		} 
		//通过机器名获取网卡地址 
		private String getMacAddressName(String remotePcIP){ 
		String str=""; 
		String macAddress=""; 
		try { 
		Process pp= Runtime.getRuntime().exec ("nbtstat -a " + remotePcIP); 
		InputStreamReader ir = new InputStreamReader(pp.getInputStream()); 
		LineNumberReader input = new LineNumberReader (ir); 
		for (int i = 1; i <100; i++) { 
		str=input.readLine(); 
		if (str!=null) { 
		if(str.indexOf("MAC Address")>1) {  
		macAddress=str.substring(str.indexOf("MAC Address")+14,str.length()); 
		break; 
		} 
		} 
		} 
		} catch (IOException ex) {} 
		return macAddress; 
		} 
		// 主函数 
		public static void main(String[] args) { 
			UdpGetClientMacAddr getmac; 
		getmac=new UdpGetClientMacAddr(); 
		String mac=""; 
		mac=getmac.getMacAddressIP("192.168.1.144");//YOUR IP 
		System.out.println(mac); 
		mac=getmac.getMacAddressName("tom");// YOUR HOST-NAME 
		System.out.println(mac); 
		} 
}
