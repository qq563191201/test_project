package com.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class StepComparatorTest {

    @Test
    public void sort() throws Exception{
        List<Step> steps=new ArrayList<Step>();
        Step st = new Step();
        st.setAcceptTime("2016-07-21 15:44:15");
        st.setAcceptAddress("重庆");
        steps.add(st);
        Step st1 = new Step();
        st1.setAcceptTime("2016-07-20 15:44:15");
        st1.setAcceptAddress("上海");
        steps.add(st1);
        //对集合对象进行排序
         StepComparator comparator=new StepComparator();
        Collections.sort(steps, comparator);
        if(steps!=null&&steps.size()>0){
            for(Step step:steps){
               System.out.println(step.getAcceptAddress());
               System.out.println(step.getAcceptTime());
            }
        }

    }
}
