package shen.training;

import java.lang.reflect.Field;

public class ObjectStringUtils {

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

    public static void fillAllStringField(final Object dto, final String defaultValue) {
        if (null == dto) {
            return;
        }
        final Field[] fields = dto.getClass().getDeclaredFields();
        for (Field aField : fields) {
            Class<?> types = aField.getType();
            if (String.class.equals(types)) {
                boolean accessible = aField.isAccessible();
                aField.setAccessible(true);
                try {
                    final String tempString = (String) aField.get(dto);
                    if (tempString == null) {
                        aField.set(dto, defaultValue);
                    }
                } catch (IllegalArgumentException e) { // e.printStackTrace();
                } catch (IllegalAccessException e) { // e.printStackTrace();
                }
                aField.setAccessible(accessible);
            }
        }
    }

    public static void trimAllStringTypeVars(final Object dto) {
        if (null == dto) {
            return;
        }
        final Field[] fields = dto.getClass().getDeclaredFields();
        for (Field aField : fields) {
            Class<?> types = aField.getType();
            if (String.class.equals(types)) {
                boolean accessible = aField.isAccessible();
                aField.setAccessible(true);
                try {
                    final String tempString = (String) aField.get(dto);
                    if (tempString != null) {
                        aField.set(dto, tempString.trim());
                    }
                } catch (IllegalArgumentException e) { // e.printStackTrace();
                } catch (IllegalAccessException e) { // e.printStackTrace();
                }
                aField.setAccessible(accessible);
            }
        }
    }

    //    @SuppressWarnings("deprecation")
    //    public static void main(final String[] args) {
    //        Mldf014mType mldf014mType = new Mldf014mType();
    //        mldf014mType.setPersonId("   test  ");
    //        mldf014mType.setAreaCode(null);
    //        trimAllStringTypeVars(mldf014mType);
    //        DTOHelper.show(mldf014mType);
    //    }

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
