package com.foxhis.h2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface H2Column
{
  String name() default "";

  String tp() default "VARCHAR";

  boolean pk() default false;
}