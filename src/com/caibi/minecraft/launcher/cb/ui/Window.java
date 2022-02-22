package com.caibi.minecraft.launcher.cb.ui;

import com.caibi.minecraft.launcher.cb.Minecraft.DownloadMinecraft;
import com.caibi.minecraft.launcher.cb.utils.Souce.Variable;
import com.caibi.minecraft.launcher.cb.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {
    public Window(String title, int width, int height){
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(getList());

        setVisible(true);
    }

    JScrollPane getList(){
        JList<String> list = new JList<>(new DefaultComboBoxModel<>(getIDArray()));
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    String clickedVersion = Variable.versionsID.get(index).toString();

                    Utils.Log.print("下载任务: "+clickedVersion);
                    DownloadMinecraft.downloadVersion(clickedVersion);
                }
            }
        });

        JScrollPane listScroll = new JScrollPane();
        listScroll.setViewportView(list);

        return listScroll;
    }

    String[] getIDArray(){
        String[] src = new String[Variable.versionsID.length()];
        for (int index=0; index<Variable.versionsID.length(); index++){
            src[index] = Variable.versionsID.getString(index);
        }

        return src;
    }
}
