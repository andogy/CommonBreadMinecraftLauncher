package com.caibi.minecraft.launcher.cb.utils.Souce;

import com.caibi.minecraft.launcher.cb.utils.Souce.Parser.VersionParser;
import org.json.*;

public class Variable {
    public static JSONObject versionsData = new JSONObject(VersionParser.parse("versions list url"));
    public static JSONArray versionsID = new JSONArray(VersionParser.parse("versions list id"));
}
