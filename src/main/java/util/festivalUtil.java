//package util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//
//import util.Fest.Cp;
//
//public class festivalUtil {
//	/**
//	 * 获取节日 节气
//	 *@engineer wangrun
//	 *@Description 
//	 * time 2013-6-25 下午02:58:44
//	 * @return
//	 */
//	public List<Fest> findFest() {
//		List<Fest> fest = new ArrayList<Fest>();
//		//农历节日
//		for(int i=0;i<=30;i++){
//			Lunar lunar = new Lunar(DateUtil.calendarOperationFactoryByDay(DateUtil.getNowCalendar(), i).getTime());
//			Fest f =Cp.FactoryFest();
//			if(lunar.isLFestival()){
//				f.setDay(i==0?"0":i+"");
//				f.setType("1");
//				f.setName(lunar.getLFestivalName());
//				fest.add(f);
//			}
//		}
//		//新历节日
//		for(int i=0;i<=30;i++){
//			Lunar lunar = new Lunar(DateUtil.calendarOperationFactoryByDay(DateUtil.getNowCalendar(), i).getTime());
//			Fest f =Cp.FactoryFest();
//			if(lunar.isSFestival()){
//				f.setDay(i==0?"0":i+"");
//				f.setType("2");
//				f.setName(MyStringUtil.replaceNull(lunar.getSFestivalName()));
//				fest.add(f);
//			}
//		}
//		//农历节气
//		int i =0;
//		while(true){
//			Lunar lunar = new Lunar(DateUtil.calendarOperationFactoryByDay(DateUtil.getNowCalendar(),i).getTime());
//			Fest f =Cp.FactoryFest();
//			if(StringUtils.isNotBlank(lunar.getTermString())){
//				f.setDay(i==0?"0":i+"");
//				f.setType("3");
//				f.setName(lunar.getTermString());
//				fest.add(f);
//				break;
//			}
//			i++;
//		}
//		return fest;
//	}
//}
