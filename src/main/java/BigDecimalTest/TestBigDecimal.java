/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package BigDecimalTest;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args){
        Integer i = new Integer("8750");
        BigDecimal b = BigDecimal.ZERO;
        BigDecimal number = new BigDecimal(i.toString());
        
        b = number.divide(new BigDecimal("100"));
        
        System.out.println(b);
        
        
        
        String str = "31101217";
        System.out.println(str.substring(3, 5));
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
    //== [Override Method] Block Start
    //====
    //====
    //== [Override Method] Block Stop 
    //================================================
    //== [Method] Block Start
    //====
    //####################################################################
    //## [Method] sub-block : 
    //####################################################################
    //====
    //== [Method] Block Stop 
    //================================================
    //== [Getter and Setter] Block Start
    //====
    //====
    //== [Getter and Setter] Block Stop 
    //================================================
}

