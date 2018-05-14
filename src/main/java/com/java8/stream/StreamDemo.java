//package com.java8.stream;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class StreamDemo {
//	public static void main(String[] args) {
//        List<Integer> intList = new LinkedList<Integer>();
//        for (int i = 1; i <= 1000000; i++) {
//            intList.add(i);
//        }
//
//        TimeRecorder recorder = new TimeRecorder();
//
//        recorder.start();
//        intList.stream().forEach(i -> {
//            i.intValue();
//            i.intValue();
//            i.toString();
//
//            i.intValue();
//            i.intValue();
//            i.toString();
//        });
//        recorder.end();
//        System.out.print("Stream iterator:");
//        System.out.println(recorder.getDuration());
//
//        recorder.start();
//        intList.parallelStream().forEach(i -> {
//            i.intValue();
//            i.intValue();
//            i.toString();
//
//            i.intValue();
//            i.intValue();
//            i.toString();
//        });
//        recorder.end();
//        System.out.print("Parallel Stream iterator:");
//        System.out.println(recorder.getDuration());
//
//        recorder.start();
//        for (Integer i : intList) {
//            i.intValue();
//            i.intValue();
//            i.toString();
//
//            i.intValue();
//            i.intValue();
//            i.toString();
//        }
//        recorder.end();
//        System.out.print("Normal iterator:");
//        System.out.println(recorder.getDuration());
//    }
//}
//
