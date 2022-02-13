package com.caibi.minecraft.launcher.cb.utils.Souce.Parser;

import com.caibi.minecraft.launcher.cb.utils.Souce.SourceDownloader;

import org.json.*;

public class MDParser {
    public static void parse(String id){
        JSONObject versionsData = new JSONObject(VersionParser.parse("versions list url"));
        SourceDownloader.download(versionsData.getString(id), "Launcher\\Versions_Data\\"+id+".json", false);
    }
}
