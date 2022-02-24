package com.caibi.minecraft.launcher.cb.utils.Souce.Parser;

import com.caibi.minecraft.launcher.cb.utils.Souce.SourceDownloader;

import com.caibi.minecraft.launcher.cb.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.caibi.minecraft.launcher.cb.utils.Souce.Variable.versionsData;

public class MDParser {


    public static void parse(String id){
        String data = readFile(id);
        System.out.println(data);
    }

    static String readFile(String id){
        File file = new File("Resource\\Launcher\\Versions_Data\\"+id+".json");
        String data = "{}";
        try {
            if (!file.exists()){
                Utils.Log.print("No file founded: "+file.getName());
                if (!(new File(file.getParent())).exists()){
                    if ((new File(file.getParent())).mkdirs()){
                        Utils.Log.print("Successfully create path: "+(new File(file.getParent())));
                    }
                }
                if (file.createNewFile()){
                    Utils.Log.print("Successfully create: Launcher\\Versions_Data\\"+id+".json");
                    SourceDownloader.download(versionsData.getString(id), "Launcher\\Versions_Data\\"+id+".json", false, false);
                    Utils.Log.print("Successfully download: Launcher\\Versions_Data\\"+id+".json");
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
}
