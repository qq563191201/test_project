package url.read;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class ReadUrl {
	public static void main(String args[]){
		String url = "http://qd.baidupcs.com/file/e80663d1b9eb6f9611ae45809d5a7121?bkt=p2-qd-706&fid=1914893594-250528-1124839426197389&time=1422927286&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-Ut%2BrJyWEyWXuSAGrAX47eTX01zg%3D&to=qb&fm=Qin,B,U,nc&newver=1&newfm=1&flow_ver=3&sl=81723468&expires=8h&rt=pr&r=271712990&mlogid=2446793807&vuk=1914893594&vbdid=3638024026&fin=users.xls&fn=users.xls";
		try
		{
			System.out.println("begin");
			getURLResource("E:/users.xls",url);
			System.out.println("end");
     	}
		catch (Exception e)
		{    
			e.printStackTrace();   
		}
	}
	
	public static void getURLResource(String ourputFile,String urlStr) throws Exception

	{   

	      FileWriter fw = new FileWriter(ourputFile);   

	      PrintWriter pw = new PrintWriter(fw);   

	      URL resourceUrl = new URL(urlStr);   

	      InputStream content = (InputStream) resourceUrl.getContent();   

	      BufferedReader in = new BufferedReader(new InputStreamReader(content));   

	      String line;   

	      while ((line = in.readLine()) != null)

	    {    

	        pw.println(line);   

	    }   

	       pw.close();   

	       fw.close();

	}
	
	public void readUrl(){
		try {
			String urldz = "http://125.67.61.120:10130/scdzqx/currenttimebymobile/currentTimeAll?weather_code=2&fun=1&station=";
			String output = "time";
			URL url = new URL(urldz);  
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));  
			String s = "";  
			StringBuffer sb = new StringBuffer("");  
			while ((s = br.readLine()) != null) {  
				sb.append(s);
			}
            br.close();
            String[] arr = (sb+"").split(",");
            Long date = null;
            int k = 0;
            Long temp = null;
            for (String string : arr) {
				if(string.contains(output)){
					date = Long.valueOf(string.replace("\"", "").replace(output+":", "").substring(0,10));
//					Timestamp nowtime = new Timestamp(System.currentTimeMillis());
//		            String now = Util.format1.format(nowtime);
//		            if(!now.equals(date.toString()) && k == 0){
//		            	k++;
//		            	date = Long.valueOf(string.replace("\"", "").replace(output+":", "").substring(0,10));
////		            	System.out.println(time);
//		            }
		            for (String str : arr) {
		            	if(str.contains(output)){
			            	Long str1 = Long.valueOf(str.replace("\"", "").replace(output+":", "").substring(0,10));
			            	if(str1 < date){
			            		temp = str1;
			            	}
		            	}
		            }
				}
			}
            System.out.println(temp+"<<<");
//            Long date = this.getTime(urldz+input, output);
//			Timestamp nowtime = new Timestamp(System.currentTimeMillis());
//			String now = Util.format1.format(nowtime);//准确到小时
//			String nowmm = Util.f1.format(nowtime);//准确到分
//			//前一小时
//			Calendar c = DateUtil.calendarOperationFactoryByHour(DateUtil.TimestampToCanlendar(nowtime), -1);
//		    Timestamp beforehour = DateUtil.calendarToSQLTimetamp(c);
//		    String onehour = Util.format1.format(beforehour);
//			//时间延迟(每小时第20分钟统计若小于第20分钟则取前一小时时间大于则取当前时间)
//			if(!(date!=null?date:"").equals("")){
//				if(Long.valueOf(nowmm)<Long.valueOf(now+"19") && 
//						(Long.valueOf(onehour)>date)){
//					System.out.println(date);
//				}
//			}
//			if(Long.valueOf(nowmm)>Long.valueOf(now+"19") 
//					&& (!date.equals(Long.valueOf(now)))){
//				System.out.println(date);
//			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
