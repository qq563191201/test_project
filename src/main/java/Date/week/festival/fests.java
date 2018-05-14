//package Date.week.festival;
//
//import java.util.List;
//
//import util.Fest;
//import util.festivalUtil;
//
//public class fests {
//	public static void main(String args[]){
//		festivalUtil festivalEntity = new festivalUtil();
//		List<Fest> festivalList = festivalEntity.findFest();
//		String hoday = "";
//		String festday = (festivalList.get(0).getDay()!=null?festivalList.get(0).getDay():"");
//		if(festday.equals("0")){
//			hoday = festivalList.get(0).getName();
//		}else{
//			hoday = "距离"+festivalList.get(0).getName()+"还有 "+festivalList.get(0).getDay()+"天";
//		}
//		System.out.println(hoday);
//	}
//}
