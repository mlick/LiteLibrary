package com.mlick.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lxx on 2016/7/1 9:40
 */
public class TestStringBufferAndStringBuilder {
    private static final String base = " base string. ";
    private static final int count = 2000000;

    public void stringTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        String test = new String(base);
        for (int i = 0; i < count / 100; i++) {
            test = test + " add ";
        }
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used String. ");
    }

    public void stringBufferTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuffer test = new StringBuffer(base);
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used StringBuffer. ");
    }

    public void stringBuilderTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuilder test = new StringBuilder(base);
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used StringBuilder. ");
    }

    public String appendItemsToStringBuiler(List list) {
        StringBuilder b = new StringBuilder();

        for (Iterator i = list.iterator(); i.hasNext(); ) {
            b.append(i.next()).append(" ");
        }

        return b.toString();
    }

    public void addToStringBuilder() {
        List list = new ArrayList();
        list.add(" I ");
        list.add(" play ");
        list.add(" Bourgeois ");
        list.add(" guitars ");
        list.add(" and ");
        list.add(" Huber ");
        list.add(" banjos ");

        System.out.println(appendItemsToStirngBuffer(list));
    }

    public String appendItemsToStirngBuffer(List list) {
        StringBuffer b = new StringBuffer();

        for (Iterator i = list.iterator(); i.hasNext(); ) {
            b.append(i.next()).append(" ");
        }

        return b.toString();
    }

    public void addToStringBuffer() {
        List list = new ArrayList();
        list.add(" I ");
        list.add(" play ");
        list.add(" Bourgeois ");
        list.add(" guitars ");
        list.add(" and ");
        list.add(" Huber ");
        list.add(" banjos ");

        System.out.println(appendItemsToStirngBuffer(list));
    }

    @Test
    public void testMain() {
        stringTest();
        stringBufferTest();
        stringBuilderTest();
        addToStringBuffer();
        addToStringBuilder();
    }
}
