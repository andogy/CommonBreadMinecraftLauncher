package com.caibi.minecraft.launcher.cb.utils.Souce;

import com.caibi.minecraft.launcher.cb.utils.Config.Config;

import java.io.*;
import java.net.*;

public class SourceDownloader {
    public static int threadCount = Integer.parseInt(Config.getConfig("downloadThreadNum"));
    public static int fileLength = 0;
    public static Thread [] threadList = new Thread[threadCount];

    public static void download(String web, String filename) {
        try {
            String path = "/Resource/"+filename;
            URL url = new URL(web);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);

            int code = conn.getResponseCode();
            if (code == 200) {
                fileLength = conn.getContentLength();
                File file = new File(path);
                if (!file.exists()) {
                    if (file.createNewFile()){
                        System.out.println("成功创建: "+path);
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
                    Thread.sleep(500);
                    if (DownloadUtils.downLength == fileLength) {
                        DownloadUtils.downOver = false;
                        System.out.println("下载完成：100%");
                    } else {
                        System.out.println("已经下载了：" + ((int) (float) DownloadUtils.downLength / (float) fileLength * 100) + "%");
                    }
                }

            } else {
                System.out.println("服务器响应失败: " + code);
            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
