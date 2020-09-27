package com.baidu.fsg.uid.impl;

import org.junit.Test;

import java.util.Date;

public class SimpleUidGeneratorTest {

    @Test
    public void foo1() {
        int timeBits = 32;
        int workerBits = 5;
        int seqBits = 12;

        SimpleUidGenerator uid = new SimpleUidGenerator(2, timeBits, workerBits, seqBits);
        uid.afterPropertiesSet();

        System.out.println("总位数: " + uid.bitsAllocator.getTotalBits());

        long delta = new Double(Math.pow(2, timeBits)).longValue();
        System.out.println("可用 "+(delta / 3600 / 24 / 365)+" 年");
        double expire = (Math.pow(2, 32) + uid.epochSeconds) / 3600 / 24 / 365;
        Date expireAt = new Date((uid.epochSeconds + delta) * 1000);
        System.out.println("到期: " + expireAt);
        System.out.println("节点容量: "+Math.pow(2, workerBits));
        System.out.println("每秒容量: "+ (Math.pow(2, seqBits)));





        System.out.println(uid.nextId());
        System.out.println(uid.nextId());
        System.out.println(uid.nextId());
        System.out.println(uid.nextId());
        System.out.println(uid.nextId());

        System.out.println(uid.parseUID(uid.nextId()));


        long uid1 = uid.getUID();
        System.out.println("uid: "+uid1);
        long prefix = 1680000000000000L;
        // int tb = uid.bitsAllocator.getTotalBits();
        // long preUid1 = (prefix << tb) | uid1;
        // System.out.println(prefi);
        System.out.println(prefix + uid1);
    }


    @Test
    public void foo2() {
        Date date = new Date(1599317229571L);
        System.out.println(date);
    }

}
