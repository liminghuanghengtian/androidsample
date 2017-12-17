package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ProjectName：MyApplication
 * PackageName: com.example
 * Description:
 * <p>
 * CreateTime: 2017/12/16 15:55
 * Modifier: Adaministrator
 * ModifyTime: 2017/12/16 15:55
 * Comment:
 *
 * @author Adaministrator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface RouterTarget {
    String path() default "";

    boolean auth() default false;

    Class<?> target();

    String module() default "";
}
