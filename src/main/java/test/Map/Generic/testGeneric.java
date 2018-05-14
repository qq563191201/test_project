package test.Map.Generic;

public class testGeneric<T> {
	T t;
    testGeneric(T t) {
        this.t = t;
    }
}
 
class test {
    testGeneric<String> tgs = new testGeneric("Hi,Gineric!");
    testGeneric<Integer> tgi = new testGeneric(100);//AutoBoxing
    {
        System.out.println(tgs.t);//Output:Hi,Gineric!
        System.out.println(tgi.t);//AutoUnBoxing&Output:100
    }
}
