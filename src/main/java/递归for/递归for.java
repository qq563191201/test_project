package 递归for;

public class 递归for {
	static int c = 0;  
	  
    public static void main(String[] args) {  
        String s = "1234";  
        index(s);  
    }  
    
    public static void index(String s){
    	for(int i=0;i<s.length();i++){
    		System.out.println(s.substring(i,i+1));
    	}
    }
  
    private static void printAllArray(String s) {  
        printAllArray(s, "");  
    }  
  
    /**
     * 组合排列
     * @param s
     * @param n
     */
    private static void printAllArray(String s, String n) {  
        if (s.length() == 0) {  
            System.out.println(n + "  ---  " + ++c);  
        } else {  
            for (int i = 0; i < s.length(); ++i) {  
                printAllArray(s.substring(1), n + s.charAt(0));  
                s = s.substring(1) + s.charAt(0);  
            }  
        }  
    }  
}
