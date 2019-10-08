package com.example.java1;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {TYPE, FIELD, PARAMETER, METHOD, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyAnnations {
    MyAnnation[] value();
}
