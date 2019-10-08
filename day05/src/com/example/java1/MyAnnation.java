package com.example.java1;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Inherited
@Repeatable(MyAnnations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyAnnation {

    String value() default "hello";
}
