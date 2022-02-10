package com.caibi.minecraft.launcher.CommonBread.Utils.CommonBreadMinecraftLauncherConfig;

import java.io.*;
import java.util.*;

import com.caibi.minecraft.launcher.CommonBread.Utils.CommonBreadMinecraftLauncherUtils;
import com.caibi.minecraft.launcher.CommonBread.Utils.LogType;
import org.json.*;

public class CommonBreadMinecraftLauncherConfig {
    public static String getConfig(String key) {
        String dat = null;
        try {
            File file = new File("CommonBreadMinecraftLauncherResource/CommonBreadMinecraftLauncherConfigFile.CommonBreadMinecraftLauncher");
            if (!file.exists()){
                CommonBreadMinecraftLauncherUtils.Log("没有找到配置文件", LogType.E);
                if (file.createNewFile()){
                    CommonBreadMinecraftLauncherUtils.Log("成功创建配置文件", LogType.M);
                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                dat = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String src = "{}";
        if (dat != null) {
            JSONObject data = new JSONObject(dat);
            src = data.get(key).toString();
        }

        return src;
    }
}
