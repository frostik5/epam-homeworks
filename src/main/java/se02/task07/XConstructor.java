package se02.task07;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.CONSTRUCTOR)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface XConstructor {
    String name();
    String project() default "Borei";
    String centralDesignBureau() default "Rubin";
}
