package com.caibi.minecraft.launcher.cb.utils.Souce;

import com.caibi.minecraft.launcher.cb.utils.Config.Config;
import com.caibi.minecraft.launcher.cb.utils.Utils;

import java.io.*;
import java.net.*;

public class SourceDownloader {
    public static int threadCount = Integer.parseInt(Config.getConfig("downloadThreadNum"));
    public static int fileLength = 0;
    public static Thread [] threadList = new Thread[threadCount];
    public static String alreadyDownload;

    public static void download(String w, String n, Boolean newThread) {
        if (newThread){
            new Thread(() -> normalDownload(w, n));
        } else {
            normalDownload(w, n);
        }
    }

    static void normalDownload(String web, String filename){
        try {
            String path = "Resource\\" + filename;
            URL url = new URL(web);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);

            int code = conn.getResponseCode();
            if (code == 200) {
                fileLength = conn.getContentLength();
                File file = new File(path);
                if (!file.exists()) {
                    if ((new File(file.getParent()).mkdirs())){
                        Utils.Log("成功创建: "+file.getParent());
                    }

                    if (file.createNewFile()){
                        Utils.Log("成功创建: "+path);
                    }
                }
                RandomAccessFile raf = new RandomAccessFile(path, "rwd");
                raf.setLength(fileLength);

                int blockSize = fileLength / threadCount;
                for (int i = 0; i < threadCount; i++) {
                    int startSize = i * blockSize;
                    int endSize = (i + 1) * blockSize - 1;
                    if (1 + i == threadCount) {
                        endSize = fileLength;
                    }
                    threadList[i] = new DownloadTask(path, web, String.valueOf(i), startSize, endSize);
                    threadList[i].start();
                }

                while (DownloadUtils.downOver) {
                    if (DownloadUtils.downLength == fileLength) {
                        DownloadUtils.downOver = false;
                        Utils.Log(filename+"下载完成");
                    } else {
                        alreadyDownload = ((int) ((float) DownloadUtils.downLength / (float) fileLength * 100)) + "%";
                    }
                    Thread.sleep(10);
                }

            } else {
                Utils.Log("服务器响应失败: " + code);
            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
