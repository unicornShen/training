package annotactionTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.FIELD)
//@Target(ElementType.METHOD)
public @interface CustomizeAnnotation {
	String value() default "ca YA!";
	
	public @interface dmc{
		String value() default "go to DMC";
	}
}
