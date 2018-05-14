package com.single.simple;

//饿汉模式
public class SingletonEH {
	private static SingletonEH instance = new SingletonEH();  
    private SingletonEH(){}  
    public static SingletonEH newInstance(){  
        return instance;  
    } 
}
