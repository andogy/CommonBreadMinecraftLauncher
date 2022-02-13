package com.caibi.minecraft.launcher.cb.Minecraft;

import com.caibi.minecraft.launcher.cb.utils.Config.Config;
import com.caibi.minecraft.launcher.cb.utils.Souce.Parser.MDParser;
import com.caibi.minecraft.launcher.cb.utils.Souce.SourceDownloader;

public class DownloadMinecraft {
    public static void downloadVersionJson(){
        SourceDownloader.download(Config.getConfig("minecraftVersion"), "Launcher\\version.json", false);
    }

    public static void downloadVersion(String id){
        MDParser.parse(id);
    }
}
