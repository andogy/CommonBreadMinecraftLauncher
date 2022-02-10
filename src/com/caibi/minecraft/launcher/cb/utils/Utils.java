package com.caibi.minecraft.launcher.cb.utils;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void Log(String msg) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("[yyyy/MM/dd hh:mm:ss.SSS] ");


        System.out.println(ft.format(date)+msg);
    }
}

