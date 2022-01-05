package injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * Annotation for providing information about packages
 * @author almtn
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
    String[] packages() default {};
}
