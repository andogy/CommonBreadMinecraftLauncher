package com.caibi.minecraft.launcher.cb.utils.Souce.Parser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.json.*;

import com.caibi.minecraft.launcher.cb.Minecraft.*;

public class VersionParser {
    public static String parse(String mf){
        JSONObject versions = new JSONObject(getVersionJson());
        String src = null;
        JSONObject srcURL = new JSONObject();
        JSONArray srcID = new JSONArray();

        if (mf.contains("new")){
            DownloadMinecraft.downloadVersionJson();
        }

        if (!mf.contains("versions")) {
            if (mf.contains("latest")) {
                src = versions.get("latest").toString();
                JSONObject srcR = new JSONObject(src);

                if (mf.contains("release")) {
                    src = srcR.get("release").toString();
                } else if (mf.contains("snapshot")) {
                    src = srcR.get("snapshot").toString();
                }
            }
        }

        if (mf.contains("versions")){
            src = versions.get("versions").toString();
            JSONArray vl = new JSONArray(src);

            if (mf.contains("list")){
                StringBuilder idList = new StringBuilder();

                for (Object ver : vl){
                    JSONObject version = new JSONObject(ver.toString());

                    String type = version.get("type").toString();
                    String id = version.get("id").toString();
                    String url = version.get("url").toString();

                    if (mf.contains("release") && type.equals("release")){
                        idList.append(id).append("     ").append(type).append("     ").append(url).append("\n");
                    } else if (mf.contains("snapshot") && type.equals("snapshot")){
                        idList.append(id).append("     ").append(type).append("     ").append(url).append("\n");
                    } else if (mf.contains("old_beta") && type.equals("old_beta")){
                        idList.append(id).append("     ").append(type).append("     ").append(url).append("\n");
                    } else if (mf.contains("old_alpha") && type.equals("old_alpha")){
                        idList.append(id).append("     ").append(type).append("     ").append(url).append("\n");
                    }
                    if (!(mf.contains("old_alpha") || mf.contains("old_beta") || mf.contains("snapshot") || mf.contains("release"))) {
                        idList.append(id).append("     ").append(type).append("     ").append(url).append("\n");
                    }

                    if (mf.contains("url")){
                        srcURL.put(id, url);
                    }

                    if (mf.contains("id")){
                        srcID.put(id);
                    }
                }
                if (mf.contains("url")){
                    src = srcURL.toString();
                } else if (mf.contains("id")){
                    src = srcID.toString();
                } else {
                    src = idList.toString();
                }
            }
        }

        return src;
    }

    public static String getVersionJson(){
        String data = "{}";
        File file = new File("Resource/Launcher/version.json");

        try {
            if (!file.exists()){
                DownloadMinecraft.downloadVersionJson();
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

    public static void updateVersionJson(){
        DownloadMinecraft.downloadVersionJson();
    }
}
