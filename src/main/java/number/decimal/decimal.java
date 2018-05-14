package number.decimal;

import java.math.BigDecimal;

public class decimal {
	/**
	 * @title
	 * @description 
	 * @author Xuxiaobing
	 * @date 2015-4-20 下午02:55:06 
	 * @version v1.0
	 */
	
	public static void main(String[] args) {

		   double i = 26.399999618530273;

		   // 舍掉小数取整
		   System.out.println("舍掉小数取整:Math.floor(3.856)=" + (int) Math.floor(i));

		   // 四舍五入取整
		   System.out.println("四舍五入取整:(3.856)="
		     + new BigDecimal(i).setScale(0, BigDecimal.ROUND_HALF_UP));

		   // 四舍五入保留两位小数
		   System.out.println("四舍五入取整:(3.856)="
		     + new BigDecimal(i).setScale(2, BigDecimal.ROUND_HALF_UP));

		   // 凑整，取上限
		   System.out.println("凑整:Math.ceil(3.856)=" + (int) Math.ceil(i));

		   // 舍掉小数取整
		   System.out.println("舍掉小数取整:Math.floor(-3.856)=" + (int) Math.floor(-i));
		   // 四舍五入取整
		   System.out.println("四舍五入取整:(-3.856)="
		     + new BigDecimal(-i).setScale(0, BigDecimal.ROUND_HALF_UP));

		   // 四舍五入保留两位小数
		   System.out.println("四舍五入取整:(-3.856)="
		     + new BigDecimal(-i).setScale(2, BigDecimal.ROUND_HALF_UP));

		   // 凑整，取上限
		   System.out.println("凑整(-3.856)=" + (int) Math.ceil(-i)); 
	}
}
