package com.example.demo.test;

import java.util.Arrays;
import java.util.function.Function;

public class Test13 {
	
	
	public static void main(String[] args) {
//        System.out.println(applyExam(3)); // 结果：1
//        System.out.println(applyExam(30));// 结果：2
//        System.out.println(composeExam("300"));// 结果：3
//        System.out.println(andThenExam(120));// 结果：120
        System.out.println(identityExam(30));// 结果：30
//        System.out.println(decoratorExam(5, (a) -> a * 5, (b) -> b + 4));// 结果 ：29，x*5+4
//        System.out.println(decoratorExam(5, (a) -> a + 5, (b) -> b * 4));// 结果 ：40，(x+5)*4
    }
	
	
	
	
	/**
     * apply：Applies this function to the given argument
     * R apply(T t);
     * 入参T，返回R
     */
    public static int applyExam(int value) {
        Function<Integer, String> converter = (i) -> Integer.toString(i);
        return converter.apply(value).length();
    }

    /**
     * compose：Returns a composed function that first applies the before
     * function to its input, and then applies this function to the result.
     * default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
     *     Objects.requireNonNull(before);
     *     return (V v) -> apply(before.apply(v));
     *  }
     * 默认实现，生成一个新的函数对象，
     * before即为调用方之前的函数，它的返回值应该是调用方参数的类型或子类，新函数对象的入参即为before入参类型或超类
     */
    public static int composeExam(String value) {
        Function<Integer, String> converter = (i) -> Integer.toString(i);
        Function<String, Integer> reverse = (s) -> Integer.parseInt(s);// 先执行
        return converter.compose(reverse).apply(value).length();
    }

    /**
     * andThen：Returns a composed function that first applies this function to
     * its input, and then applies the after function to the result.
     * default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
     *   Objects.requireNonNull(after);
     *   return (T t) -> after.apply(apply(t));
     * }
     * 默认实现，生成一个新的函数对象，
     * after即为调用方之后的函数，它的入参是调用方返回值的类型或超类，新函数对象的返回值即为after返回值的类型或子类
     */
    public static int andThenExam(int value) {
        Function<Integer, String> converter = (i) -> Integer.toString(i);
        Function<String, Integer> reverse = (s) -> Integer.parseInt(s);// 后执行
        // 因为有类型推断，也可以做如下使用：
        // converter.andThen((s) -> Integer.parseInt(s)).apply(value).byteValue();
        return converter.andThen(reverse).apply(value).byteValue();
    }

    /**
     * identity：Returns a function that always returns its input 
     * static <T> Function<T, T> identity() {
     *   return t -> t;
     * }
     * 静态实现，返回入参
     * 可以使用在Optional返回默认值上
     */
    public static int identityExam(int value) {
        Function<Integer, Integer> id = Function.identity();
        return id.apply(value);
    }

    public static int decoratorExam(int value, Function<Integer, Integer>... decorators) {
        return Arrays.asList(decorators).stream().reduce((current, next) -> current.andThen(next))
                .orElseGet(Function::identity).apply(value);
    }

}
