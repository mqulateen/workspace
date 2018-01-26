package com.mqul.mp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferableUtils
{
    public static <T> List<T> transferList(List<? extends TransferableObject<T>> list)
    {
        List<T> items = new ArrayList<>();
        for(TransferableObject<T> item : list)
            items.add(item.transferToDTO());

        return items;
    }
}
