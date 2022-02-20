package com.caibi.minecraft.launcher.cb.main;

import com.caibi.minecraft.launcher.cb.Minecraft.DownloadMinecraft;

public class CBML {
    public static void CBLInit(String[] args){
        DownloadMinecraft.downloadVersionJson();
        DownloadMinecraft.downloadVersion("1.17.1");
    }
}
