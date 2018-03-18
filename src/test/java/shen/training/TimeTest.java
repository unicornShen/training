package shen.training;

import java.util.Calendar;
import java.util.Date;

/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */

public class TimeTest {

    /* Test. */
    public static void main(String[] args) {
        // Thread.sleep(5*1000);

        Calendar cal = Calendar.getInstance();
        cal.set(2012, Calendar.AUGUST, 24);
        Date d1 = cal.getTime();
        cal.set(2013, Calendar.AUGUST, 28);
        Date d2 = cal.getTime();
        long daterange = d2.getTime() - d1.getTime();
        long time = 1000 * 3600 * 24; //A day in milliseconds
        System.out.println(daterange / time);
    }

    //================================================
    //== [Enumeration types] Block Start
    //====
    //====
    //== [Enumeration types] Block End 
    //================================================
    //== [static variables] Block Start
    //====
    //====
    //== [static variables] Block Stop 
    //================================================
    //== [instance variables] Block Start
    //====
    //====
    //== [instance variables] Block Stop 
    //================================================
    //== [static Constructor] Block Start
    //====
    //====
    //== [static Constructor] Block Stop 
    //================================================
    //== [Constructors] Block Start (含init method)
    //====
    //====
    //== [Constructors] Block Stop 
    //================================================
    //== [Static Method] Block Start
    //====
    //====
    //== [Static Method] Block Stop 
    //================================================
    //== [Accessor] Block Start
    //====
    //====
    //== [Accessor] Block Stop 
    //================================================
    //== [Overrided Method] Block Start (Ex. toString/equals+hashCode)
    //====
    //====
    //== [Overrided Method] Block Stop 
    //================================================
    //== [Method] Block Start
    //====
    //####################################################################
    //## [Method] sub-block : 
    //####################################################################
    //====
    //== [Method] Block Stop 
    //================================================
}
