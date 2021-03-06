package IsChineses;

import java.util.Random;

public class IsChineseOrEnglish {
//  GENERAL_PUNCTUATION 判断中文的“号  
	  
    //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号  
  
    //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号  
  
    public static boolean isChinese(char c) {  
  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
  
            return true;  
  
        }  
  
        return false;  
  
    }  
  
  
  
    public static void isChinese(String strName) {  
  
        char[] ch = strName.toCharArray();  
  
        for (int i = 0; i < ch.length; i++) {  
  
            char c = ch[i];  
  
            if (isChinese(c) == true) {  
  
                System.out.println(isChinese(c));  
  
                return;  
  
            } else {  
  
                System.out.println(isChinese(c));  
  
                return;  
  
            }  
  
        }  
  
    }  
  
    public static void main(String[] args) {  
  
        Random r = new Random();  
  
        for(int i=0;i<20;i++)  
  
        System.out.println(r.nextInt(10)+1);  
  
        isChinese("き");  
  
        isChinese("中国");  
  
    }  
}
