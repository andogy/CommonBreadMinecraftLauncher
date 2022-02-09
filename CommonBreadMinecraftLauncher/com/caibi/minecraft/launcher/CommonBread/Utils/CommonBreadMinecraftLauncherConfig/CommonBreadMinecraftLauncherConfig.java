package com.caibi.minecraft.launcher.CommonBread.Utils.CommonBreadMinecraftLauncherConfig;

import java.io.*;
import java.util.*;

import org.json.*;

public class CommonBreadMinecraftLauncherConfig {
    public static String getConfig(String key) {
        String dat = null;
        try {
            File file = new File("CommonBreadMinecraftLauncherConfigFile.CommonBreadMinecraftLauncher");
            if (!file.exists()){
                if (file.createNewFile()){

                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                dat = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
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
