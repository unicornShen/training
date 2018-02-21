package annotactionTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CheckReturnValue {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface iTtrue {

		String getfield();
	}

}
