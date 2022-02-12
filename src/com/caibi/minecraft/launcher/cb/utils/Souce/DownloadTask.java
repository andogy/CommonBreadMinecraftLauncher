package com.caibi.minecraft.launcher.cb.utils.Souce;

import java.io.*;
import java.net.*;

public class DownloadTask extends Thread{

    private final String filePath;
    private final String urlPath;
    private final int startSize;
    private final int endSize;

    public DownloadTask(String filePath,String urlPath,String threadName,int startSize,int endSize) {
        this.endSize=endSize;
        this.startSize=startSize;
        this.filePath=filePath;
        this.urlPath=urlPath;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection coon = (HttpURLConnection) url.openConnection();
            coon.setRequestProperty("range","bytes="+startSize+"-"+endSize);
            coon.setConnectTimeout(5000);
            if(coon.getResponseCode()==206) {
                BufferedInputStream bi=new BufferedInputStream(coon.getInputStream());
                RandomAccessFile raf=new RandomAccessFile(filePath, "rwd");
                raf.seek(startSize);
                byte[] b = new byte[1024];
                int len;
                while ((len=bi.read(b))>-1) {
                    raf.write(b, 0, len);
                    synchronized (DownloadUtils.class) {
                        DownloadUtils.downLength=DownloadUtils.downLength+len;
                    }
                }
                raf.close();
                bi.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
