package com.mqul.mp;

public class Utils
{
    public static boolean isStringEmpty(String str)
    {
        return (str == null || str.isEmpty());
    }

    public static boolean isStringNonEmpty(String str)
    {
        return (str != null && !str.isEmpty());
    }

    public static boolean isStringEqual(String s1, String s2)
    {
        return s1.equals(s2);
    }

    public static <T>boolean notEquals(T s1, T s2)
    {
        return s1 != null && s2 != null && !s1.equals(s2);
    }
}
