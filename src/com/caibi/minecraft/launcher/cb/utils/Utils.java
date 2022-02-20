package com.caibi.minecraft.launcher.cb.utils;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void Log(String msg) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("[yyyy/MM/dd hh:mm:ss.SSS] ");

        System.out.println(ft.format(date)+msg);
    }

    public static String getWeb(String web) {
        String line = null;
        try {
            URL url = new URL(web);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            line = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public static String subString(String text, String left, String right) {
        String result;
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);

        return result;
    }
}

