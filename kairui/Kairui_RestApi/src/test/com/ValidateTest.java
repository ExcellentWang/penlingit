package com;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class ValidateTest {

    @Test
    public void test1() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Class groups[] = {Bus.aa.class};
        Bus car = new Bus();
        Set<ConstraintViolation<Bus>> set = validator.validate(car, groups);
        for (ConstraintViolation<Bus> violation : set) {
            String message = violation.getMessage();
            System.out.println(message);
        }

        // 验证方法参数
        Method method = null;
        try {
            method = Car.class.getMethod("drive", int.class);
        } catch (SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object[] parameterValues = {80};
        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<Bus>> methodValidators = executableValidator.validateParameters(car,
                method, parameterValues);
        for (ConstraintViolation<Bus> violation : methodValidators) {
            String message = violation.getMessage();
            System.out.println(message);
        }
    }

    public static class Car {

        public interface a {

        }

        private String name;
        private Integer age;
        private Integer sex;

        public Car() {
        }

        public Car(String name) {
            this.name = name;
        }

        @NotNull(message = "name不能为空", groups = {a.class})
        public String getRentalStation() {
            return name;
        }

        @NotNull(message = "age不能为空", groups = {a.class})
        public Integer getAge() {
            return age;
        }

        @NotNull(message = "sex不能为空", groups = {a.class})
        public Integer getSex() {
            return sex;
        }

        public void drive(@Max(75) int speedInMph) {

        }
    }

    public static class Bus extends Car {

        public interface aa extends a {

        }

        private Integer number;

        @NotNull(message = "number不能为空", groups = {aa.class})
        public Integer getNumber() {
            return number;
        }

    }
}
