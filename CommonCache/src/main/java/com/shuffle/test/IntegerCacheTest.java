package com.shuffle.test;

/**
 * Author:Shawn.Xu
 * Date:2016/5/23
 * Description:
 */
public class IntegerCacheTest {
    public static void main(String... strings) {

        Integer integer1 = 3;
        Integer integer2 = 3;

        if (integer1 == integer2)
            System.out.println("integer1 == integer2");
        else
            System.out.println("integer1 != integer2");

        Integer integer3 = 300;
        Integer integer4 = 300;

        if (integer3 == integer4)
            System.out.println("integer3 == integer4");
        else
            System.out.println("integer3 != integer4");


        int low = -128;
        int i = Integer.MAX_VALUE - (-low) -1;

        System.out.println(Integer.MAX_VALUE + ":" + i);

        System.out.println(Integer.MAX_VALUE - i);
    }
}
