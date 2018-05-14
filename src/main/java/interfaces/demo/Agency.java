package interfaces.demo;

public class Agency {
	  public static void toMI5(BritishSpy spy) {
	    //军情5处当然只能说英语，做英国间谍
	    spy.speak();
	    //spy.sprechen();不可见
	  }

	  public static void inGermany(GermanSpy spy) {
	    //spy.speak();不可见
	    spy.sprechen();
	  }

	public static void main(String[] args) {
		DoubleAgent da = new DoubleAgent();
	    BritishSpy es = (BritishSpy) da;
	    GermanSpy gs = (GermanSpy) da;
	    toMI5(da); //MI5也不知道他是一个双重间谍，只知道他是BritishSpy
	    toMI5(es); //更安全
	    //toMI5(gs); 不可能
	    inGermany(da); //在德国还是安全的，德国人不知道他的双重间谍身份，只知道他是GermanSpy
	    inGermany(gs); 
	    //inGermany(es); 不可能
	}
}

