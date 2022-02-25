package com.caibi.minecraft.launcher.cb.ui;

import com.caibi.minecraft.launcher.cb.Minecraft.DownloadMinecraft;
import com.caibi.minecraft.launcher.cb.utils.Souce.Parser.VersionParser;
import com.caibi.minecraft.launcher.cb.utils.Souce.Variable;
import com.caibi.minecraft.launcher.cb.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Window extends JFrame {
    JScrollPane VersionList;
    JButton DownloadButton;

    String clickedVersion = VersionParser.parse("latest release");

    public Window(String title, int width, int height){
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setMinimumSize(new Dimension(width,height));

        DownloadButton = downloadButton();
        add(DownloadButton);

        VersionList = getList();
        add(VersionList);

        setVisible(true);
    }

    JScrollPane getList(){
        JList<String> list = new JList<>(new DefaultComboBoxModel<>(getIDArray()));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                clickedVersion = Variable.versionsID.get(index).toString();

                if (e.getClickCount() == 2) {
                    Utils.Log.print("Clicked: " + clickedVersion);
                    webView("https://minecraft.fandom.com/wiki/"+clickedVersion);
                }
            }
        });

        JScrollPane listScroll = new JScrollPane();
        listScroll.setViewportView(list);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                listScroll.setLocation(getWidth() - 213, 0);
                if (!(getHeight()-150 < 50)) {
                    list.setSize(200, getHeight() - 150);
                    listScroll.setSize(list.getSize());
                }
            }
        });

        return listScroll;
    }

    void webView(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    String[] getIDArray(){
        String[] src = new String[Variable.versionsID.length()];
        for (int index=0; index<Variable.versionsID.length(); index++){
            src[index] = Variable.versionsID.getString(index);
        }

        return src;
    }

    JButton downloadButton(){
        JButton button = new JButton("Download");
        button.setSize(100,20);
        button.setFont(new Font("Arial", Font.PLAIN, 15));

        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        addComponentListener((new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                button.setLocation(getWidth()-100, getHeight()-150);
            }
        }));

        button.addActionListener(e -> DownloadMinecraft.downloadVersion(clickedVersion));

        return button;
    }
}
