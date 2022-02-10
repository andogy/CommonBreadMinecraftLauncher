package com.caibi.minecraft.launcher.cb.main;

import com.caibi.minecraft.launcher.cb.utils.Config.Config;
import com.caibi.minecraft.launcher.cb.utils.Souce.SourceDownloader;

public class CBML {
    public static void CommonBreadMinecraftLauncherMainJavaFileInit(String[] args){
        SourceDownloader.download(Config.getConfig("minecraftVersion"), "Launcher/version.json");
    }
}
