package com.caibi.minecraft.launcher.CommonBread.CommonBreadMinecraftLauncherMain;

import com.caibi.minecraft.launcher.CommonBread.Utils.CommonBreadMinecraftLauncherConfig.CommonBreadMinecraftLauncherConfig;
import com.caibi.minecraft.launcher.CommonBread.Utils.CommonBreadMinecraftLauncherUtils;
import com.caibi.minecraft.launcher.CommonBread.Utils.LogType;

public class CommonBreadMinecraftLauncherMainJavaFile {
    public static void CommonBreadMinecraftLauncherMainJavaFileInit(String[] args){
        System.out.println(CommonBreadMinecraftLauncherConfig.getConfig("NAME"));
    }
}
