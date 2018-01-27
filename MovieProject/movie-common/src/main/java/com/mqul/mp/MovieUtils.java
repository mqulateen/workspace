package com.mqul.mp;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils
{
    public static boolean isStringNonEmpty(String str)
    {
        return (str != null && !str.isEmpty());
    }

    public static <T>boolean notEquals(T s1, T s2)
    {
        return s1 != null && s2 != null && !s1.equals(s2);
    }

    public static <T> List<T> transferList(List<? extends TransferableObject<T>> list)
    {
        List<T> items = new ArrayList<>();
        for(TransferableObject<T> item : list)
            items.add(item.transferToDTO());

        return items;
    }
}
