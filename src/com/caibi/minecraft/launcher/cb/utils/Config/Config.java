package com.caibi.minecraft.launcher.cb.utils.Config;

import java.io.*;
import java.util.*;

import com.caibi.minecraft.launcher.cb.utils.Utils;

import org.json.*;

public class Config {
    public static File file = new File("Resource/Config.cbml");
    public static String getConfig(String key) {
        String dat = null;
        try {
            if (!file.exists()){
                Utils.Log.print("没有找到配置文件");
                if (file.createNewFile()){
                    Utils.Log.print("成功创建配置文件");
                    writeConfig("{'minecraftVersion':'https://launchermeta.mojang.com/mc/game/version_manifest.json'}");
                    addConfig("downloadThreadNum","50");
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

    public static String getConfig(){
        String data = "{}";
        try {
            if (!file.exists()){
                Utils.Log.print("没有找到配置文件");
                if (file.createNewFile()){
                    Utils.Log.print("成功创建配置文件");
                    writeConfig("{'minecraftVersion':'https://launchermeta.mojang.com/mc/game/version_manifest.json'}");
                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void writeConfig(String str){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addConfig(String key, String value){
        JSONObject config = new JSONObject(getConfig());
        writeConfig(config.put(key,value).toString());
    }

    public static void changeConfig(String key, String value){
        JSONObject config = new JSONObject(getConfig());
        writeConfig(config.put(key,value).toString());
    }
}
