package annoation;

import json.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NameAnnoation {
    int age() default 20;

    String[] name();

    String value() default "haha";
}

class TestAnnotion{
    @NameAnnoation(name = {"sdf", "sfds"})
    public static void Test() {
        NameAnnoation n =
        System.out.println(c.getResource("name"));

    }

    public static void main(String[] args) {
        Test();
    }
}
