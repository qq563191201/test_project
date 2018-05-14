package com.reg;

import java.util.regex.Pattern;

public class Regexp {
	
	public static void main(String[] args){
		 //Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\/\\/\\s]?((((0?"+"[13578])|(1[02]))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"+"35679])|([13579][01345789]))[\\/\\/\\s]?((((0?[13578])|(1[02]))" +"[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/\\/\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?" +"[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12" +"35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))");
       
        String s = "2015-04-27";
          System.out.println(s + " " + p.matcher(s).matches());
 
          s = "2004/02/29";
          System.out.println(s + " " + p.matcher(s).matches());
 
          s = "2004/04/31";
          System.out.println(s + " " + p.matcher(s).matches());
 
          s = "2004/04/30";
          System.out.println(s + " " + p.matcher(s).matches());
 
          s = "2004/04/30";
          System.out.println(s + " " + p.matcher(s).matches());
 
          s = "2004/09/30";
          System.out.println(s + " " + p.matcher(s).matches());
  
	}
}
