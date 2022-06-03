package controllers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import interfaces.IFrameConstants;


public class LogMenu extends JFrame implements IFrameConstants {
    
    // text area
    private JTextArea textArea;
    private JTextArea textAreaTime;

    // panel
    private JPanel logPanel;
    private JPanel panelButton;

    // button
    private JButton btnCopyToClipboard;

    public LogMenu() {
        initLogMenu();
    }
    
    /**
     * Inicialize log menu.
     */
    public void initLogMenu() 
    {
        this.setSize(500, WINDOW_HEIGHT);
        this.setTitle("Starting server");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1));
        this.setLocation((int)(SCREEN_WIDTH / 2), (int)(SCREEN_HEIGHT / 2) - (WINDOW_HEIGHT / 2));

        initLogPanel();
        this.add(logPanel);
    }
    
    public void initLogPanel()
    {
        this.logPanel           = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelButton        = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.textArea           = new JTextArea();
        this.textAreaTime       = new JTextArea();
        this.btnCopyToClipboard = new JButton("Copy IP server");
    
        this.textArea.setBorder(new EmptyBorder(20, 10, 0, 10));
        this.textArea.setBackground(Color.black);
        this.textArea.setForeground(Color.white);
        this.textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setPreferredSize(new Dimension(500, 400));

        this.textAreaTime.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.textAreaTime.setBorder(new EmptyBorder(0, 10, 20, 10));
        this.textAreaTime.setBackground(Color.black);
        this.textAreaTime.setForeground(Color.white);
        this.textAreaTime.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.textAreaTime.setEditable(false);
        this.textAreaTime.setLineWrap(true);
        this.textAreaTime.setWrapStyleWord(true);
        this.textAreaTime.setPreferredSize(new Dimension(500, 100));

        this.btnCopyToClipboard.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        
        this.panelButton.setPreferredSize(new Dimension(500, 100));
        this.panelButton.add(btnCopyToClipboard);
        
        this.logPanel.setBackground(Color.decode("#252525"));
        this.logPanel.add(this.textArea);
        this.logPanel.add(this.textAreaTime);
        this.logPanel.add(panelButton);
    }

    public void closeLogMenu() {
        dispose();
    }

    public JTextArea getTextArea() {
        return this.textArea;
    }

    public JTextArea getTextAreaTime() {
        return this.textAreaTime;
    }

    public void run() {
        this.setVisible(true);
    }

}
