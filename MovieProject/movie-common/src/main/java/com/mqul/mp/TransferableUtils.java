package com.mqul.mp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferableUtils
{
    public static List transferList(List<? extends TransferableObject> list)
    {
        List items = new ArrayList<>();
        for(TransferableObject item : list)
            items.add(item.transferToDTO());

        return items;
    }
}
