package com.caibi.minecraft.launcher.cb.main;

import com.caibi.minecraft.launcher.cb.utils.Souce.Parser.VersionParser;

public class CBML {
    public static void CBLInit(String[] args){
        System.out.println(VersionParser.parse("versions list release"));
    }
}
