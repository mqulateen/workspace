package com.mqul.mp;

import java.util.List;
import java.util.stream.Collectors;

public class TransferableUtils
{
    public static List transferList(List<? extends TransferableObject> list)
    {
        return list.stream()
                .map(TransferableObject::transferToDTO)
                .collect(Collectors.toList());
    }
}
