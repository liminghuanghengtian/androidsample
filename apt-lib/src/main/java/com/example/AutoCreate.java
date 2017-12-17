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
 * CreateTime: 2017/12/15 21:16
 * Modifier: Adaministrator
 * ModifyTime: 2017/12/15 21:16
 * Comment:
 *
 * @author Adaministrator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoCreate {
}
