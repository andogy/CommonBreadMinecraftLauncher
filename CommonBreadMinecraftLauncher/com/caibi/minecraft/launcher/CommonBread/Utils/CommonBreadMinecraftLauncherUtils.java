package com.caibi.minecraft.launcher.CommonBread.Utils;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonBreadMinecraftLauncherUtils{
    public static void Log(String msg, @Nullable LogType type) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("  yyyy/MM/dd hh:mm:ss.SSS] ");

        String t = "M";
        String c1 = "";
        String c2 = "";
        if (type == LogType.E || type == LogType.Error){
            t = "Error";
            c1 = "\033[31;1m";
            c2 = "\033[0m";
        }else if (type == LogType.M || type == LogType.Message || type == null){
            t = "Message";
            c1 = "\033[1m";
            c2 = "\033[0m";
        }

        System.out.println(c1+"["+t+ft.format(date)+msg+c2);
    }
}

