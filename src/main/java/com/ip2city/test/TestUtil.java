package com.ip2city.test;

import java.lang.reflect.Method;

import com.ip2city.DataBlock;
import com.ip2city.DbConfig;
import com.ip2city.DbSearcher;
import com.ip2city.HeaderBlock;
import com.ip2city.Util;

/**
 * project test script
 * 
 * @author chenxin<chenxin619315@gmail.com>
*/
public class TestUtil 
{
    public static void main(String[] argv)
    {
/*        //1. test the ip2long
        String[] ipSet = new String[]{
            "120.24.78.68", 
            "120.24.229.68", 
            "120.24.87.145", 
            "218.17.162.99"
        };
        
        for ( String ip : ipSet )
        {
            int ipInt = Util.ip2Int(ip);
            System.out.println("src ip: " + ip + ", ip2Int: " + ipInt + ", int2IP: " + Util.int2IP(ipInt));
        }*/
        
/*        int[] arr = new int[]{12344, -1234, 2146789, 0, -1024};
        byte[] b = new byte[arr.length * 4];
        
        //write the int
        System.out.println("+--Testing writeInt ... ");
        int i, idx = 0;
        for ( i = 0; i < b.length; i += 4 )
        {
            System.out.println("offset: " + i);
            Util.writeInt(b, i, arr[idx++]);
        }
        System.out.println("|----[Ok]");
        
        //read the int
        System.out.println("+--Testing getInt ... ");
        idx = 0;
        for ( i = 0; i < b.length; i += 4 )
        {
            System.out.println(arr[idx++]+", " + Util.getInt(b, i));
        }
        System.out.println("|----[Ok]");*/
        
       /*HeaderBlock headerBlock = new HeaderBlock(241658345, 2134785);
        byte[] b = headerBlock.getBytes();
        System.out.println(headerBlock.getIndexStartIp() + ", " + headerBlock.getIndexPtr());
        System.out.println(Util.getInt1(b, 0) + ", " + Util.getInt1(b, 4));*/
        
       	String ip = "114.104.41.138";
        argv = new String[]{"F:\\data\\ip2region.db"};
        double sTime = 0, cTime = 0;
        try {
	        DbConfig config = new DbConfig();
	        DbSearcher searcher = new DbSearcher(config, argv[0]);
	        Method method = null;
	        int algorithm = DbSearcher.BTREE_ALGORITHM;
	        String algoName = "B-tree";
	        if ( argv.length > 1 ) {
	            if ( argv[1].equalsIgnoreCase("binary")) {
	                algoName  = "Binary"; 
	                algorithm = DbSearcher.BINARY_ALGORITHM;
	            } else if ( argv[1].equalsIgnoreCase("memory") ) {
	                algoName  = "Memory";
	                algorithm = DbSearcher.MEMORY_ALGORITYM;
	            }
	        }
	        System.out.println("initializing "+algoName+" ... ");
            switch ( algorithm ) 
            {
            case DbSearcher.BTREE_ALGORITHM:
                method = searcher.getClass().getMethod("btreeSearch", String.class);
                break;
            case DbSearcher.BINARY_ALGORITHM:
                method = searcher.getClass().getMethod("binarySearch", String.class);
                break;
            case DbSearcher.MEMORY_ALGORITYM:
                method = searcher.getClass().getMethod("memorySearch", String.class);
                break;
            }
	        if ( Util.isIpAddress(ip) == false ) {
	        	System.out.println("Error: Invalid ip address");
	        }
	        sTime = System.nanoTime();
	        DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
            cTime = (System.nanoTime() - sTime) / 1000000;
            System.out.println(dataBlock.getCityId()+"/"+dataBlock.getDataPtr()+"/"+dataBlock.getRegion());
            System.out.printf("%s in %.5f millseconds\n", dataBlock, cTime);
        }catch(Exception e) {
        	
        }
    }
}
