package com.randoms;

import java.security.MessageDigest;

public class SeedGenerater {
	static MessageDigest messageDigest = null;
    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (Exception e) {
        }
    }
 
    int speed;
 
    public SeedGenerater() {
        this(4096);
    }
 
    /**
     * 
     * @param speed
     *            与CPU的运算速度有关,运算越快,speed越高.以便使得种子更加"无序"
     */
    public SeedGenerater(int speed) {
        this.speed = speed;
    }
 
    private int getInt() {
        int k = 0;
        /*
         * 做些无谓的运算
         */
        for (int i = 0; i < speed; i++) {
            k = k++;
        }
        return (int) System.nanoTime() % 256;
    }
 
    /**
     * 
     * @param n
     *            种子的复杂程度,至少10000
     */
    public void make(int n) {
        if (n < 10000)
            n = 10000;
        for (int i = 0; i < n; i++) {
            messageDigest.update((byte) getInt());
        }
    }
 
    /**
     * 
     * @return 字节数组的种子
     */
    public byte[] getSeed() {
        return messageDigest.digest();
    }
}
