package number.round;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class round_ot {
	public static void main(String args[]){
		double num = 0.7d;
		double nums = num/2;
		Float number = 0.7f/2.0f;
		String js = numberRend(nums+"");
		String result = format(number);
		
		String parten = "#.#";
		DecimalFormat decimal = new DecimalFormat(parten);
		String str= decimal.format(0.45);
		System.out.println(str);		
	}
	
	/**
	 * 保留2位小数
	 */
	public static String round2(String arg){
		if(arg==null)return "0.00";
		arg=arg.replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").trim();
		int position = arg.lastIndexOf(".");
		int lastPosition=arg.length()-1;
		if(lastPosition-position>2){
			arg=arg.substring(0, position+3);
		}
		return arg;
	}
	
	/**
	 * 四舍五入
	 * %.1f %. 表示 小数点前任意位数  1 表示1位小数 格式后的结果为f 表示浮点型。
	 */
	public static String format(double num){
		String number = String.format("%.1f",num);
		return number;
	}
		
	/**
	 * 进位保留一位小数进位
	 * @param str
	 * @return
	 */
	public static String numberRend(String str)	{
		Double num = 0d;
		if(!(str!=null?str:"").equals("")){
			num = Double.valueOf(str);
		}
		return new BigDecimal(num).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
	}
}
