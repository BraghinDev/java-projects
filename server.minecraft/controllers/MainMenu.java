package controllers;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.event.WindowEvent;    
import java.awt.event.WindowAdapter;

import java.net.URI;

import java.io.File;

import interfaces.IFrameConstants;

public class MainMenu extends JFrame implements IFrameConstants {

    // buttons
    private JButton btnInitServer;
    private JButton btnStopServer;
    private JButton btnConfigurePort;
    private JButton btnConfigurePath;
    private JButton btnExitServer;
    private JButton btnCredits;

    // panels
    private JPanel  mainMenu;
    private LogMenu logMenu;

    // server
    private Server server;

    // labels
    private JLabel info1;
    private JLabel info2;

    // values
    private boolean isRunning;
    private String port = "25565";
    private String path = "C:/Ngrok/ngrok.exe";


    public MainMenu()
    {
        initComponents();
        logMenu = new LogMenu();
        isRunning = false;
    }

    /**
     * Inicialize the main menu.
     */
    public void init() 
    {
        this.getMainMenu();   
        this.events();
        this.setVisible(true);
        this.logMenu.run();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(confirmClose() == JOptionPane.YES_OPTION) {
                    exitMainMenu();
                }
            }
        });
    }

    /**
     * Creates the main window
     */

    public void initComponents() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        setLocation((int)(SCREEN_WIDTH / 2) - (WINDOW_WIDTH), (int)(SCREEN_HEIGHT / 2) - (WINDOW_HEIGHT / 2));
    }

    /**
     * Controlls all action events in the buttons of the main menu.
     */
    public void events() {
        ActionListener initServerButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(isRunning) 
                {
                    JOptionPane.showMessageDialog(null, "Server already running!", "Denied", JOptionPane.ERROR_MESSAGE);
                } else {
                    btnStopServer.setEnabled(true);
                    btnStopServer.setBackground(Color.red);
                    btnInitServer.setEnabled(false);
                    btnInitServer.setBackground(Color.gray);
                    btnConfigurePort.setEnabled(false);
                    btnConfigurePath.setEnabled(false);
                    callServer();
                }
            }
        };
        btnInitServer.addActionListener(initServerButtonListener);

        ActionListener stopServerButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(!isRunning) 
                {
                    JOptionPane.showMessageDialog(null, "Server is not running!", "Denied", JOptionPane.ERROR_MESSAGE);
                } else {
                    btnStopServer.setEnabled(false);
                    btnStopServer.setBackground(Color.gray);
                    btnInitServer.setEnabled(true);
                    btnInitServer.setBackground(Color.green);
                    btnConfigurePort.setEnabled(true);
                    btnConfigurePath.setEnabled(true);
                    stopServer();
                }
            }
        };
        btnStopServer.addActionListener(stopServerButtonListener);

        ActionListener configurePortButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(isRunning) 
                {
                    JOptionPane.showMessageDialog(null, "Impossible change port with server running!", "Denied", JOptionPane.ERROR_MESSAGE);
                } else {
                    portMenu();
                }
            }
        };
        btnConfigurePort.addActionListener(configurePortButtonListener);

        ActionListener configurePathButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(isRunning) 
                {
                    JOptionPane.showMessageDialog(null, "Impossible change path with server running!", "Denied", JOptionPane.ERROR_MESSAGE);
                } else {
                    pathMenu();
                }
            }
        };
        btnConfigurePath.addActionListener(configurePathButtonListener);

        ActionListener creditsButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                creditsMenu();   
            }
        };
        btnCredits.addActionListener(creditsButtonListener);

        ActionListener exitServerButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(isRunning)
                {
                    stopServer();
                }
                exitMainMenu();
            }
        };
        btnExitServer.addActionListener(exitServerButtonListener);

    }

    /**
     * Start a new server thread.
     */
    public void callServer() {
        this.server = new Server(logMenu);
        this.server.setPort(port);
        this.server.setPath(path);
        this.server.start();
        isRunning = true;
    }

    /**
     * Stop de current server thread running.
     */
    public void stopServer() {
        this.server.stopProcess();
        this.server = null;
        System.gc();
        isRunning = false;
    }

    /**
     * Display the main menu of the server.
     */
    public void getMainMenu() {

        mainMenu           = new JPanel(new FlowLayout(FlowLayout.LEADING, 25, 60));
        btnInitServer      = new JButton("Init Server");
        btnConfigurePort   = new JButton("Configure Port");
        btnConfigurePath    = new JButton("Configure Path");
        btnStopServer      = new JButton("Stop Server");
        btnExitServer      = new JButton("Exit Server");

        btnCredits         = new JButton("Credits");
        
        info1              = new JLabel("Default Path: C:/Ngrok/ngrok.exe");
        info2              = new JLabel("Default Port: 25565");

        btnInitServer.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnConfigurePort.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnStopServer.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnExitServer.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnConfigurePath.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnCredits.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        
        btnExitServer.setBackground(Color.white);
        btnInitServer.setBackground(Color.green);
        btnStopServer.setBackground(Color.gray);
        btnStopServer.setEnabled(false);

        mainMenu.setBackground(Color.decode("#252525"));
        mainMenu.add(info1);
        mainMenu.add(info2);
        mainMenu.add(btnInitServer);
        mainMenu.add(btnStopServer);
        mainMenu.add(btnConfigurePort);
        mainMenu.add(btnConfigurePath);
        mainMenu.add(btnExitServer);
        mainMenu.add(btnCredits);

        info1.setForeground(Color.white);
        info2.setForeground(Color.white);


        this.add(mainMenu);
    }

    /**
     * Display the edit port menu.
     */
    public void portMenu() {
        String port;
        int portNumber;

        try {
            port = JOptionPane.showInputDialog(null, "Enter a port above 1024:");
            if(port.equals("" + JOptionPane.CANCEL_OPTION)) {
                return;
            }
            portNumber = Integer.parseInt(port);
            
            if(portNumber < 1024)
            {
                JOptionPane.showMessageDialog(null, "Enter a port above 1024!", "Error!",JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Port has been setted to " + port + "!", "Done!",JOptionPane.INFORMATION_MESSAGE);
                this.port = port;
            }
        } catch(NumberFormatException err) {

            JOptionPane.showMessageDialog(null, "Enter a numeric value!", "Error!",JOptionPane.ERROR_MESSAGE);
        } catch(NullPointerException err) {
            return;
        } 
    }

    /**
     * Display the edit path menu.
     */
    public void pathMenu() 
    {
        JFileChooser chooser  = new JFileChooser("/");
        File file;
        String path;

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        if(chooser.getSelectedFile() != null)
        {
            file = chooser.getSelectedFile();
            if(file.isFile())
            {
                path = file.getPath();
                JOptionPane.showMessageDialog(null, "Successfully path changed!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.path = path;
            } else {
                JOptionPane.showMessageDialog(null, "Path not changed!", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Display the credits menu.
     */
    public void creditsMenu() {
        JPanel panel   = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonGithub = new JButton("Visit Pedro's GitHub");
        JButton buttonNgrok = new JButton("Visit Ngrok WebSite");
        JLabel label   = new JLabel("Created by Pedro Braghin using the software Ngrok to run the server.");
        
        
        buttonGithub.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    URI link = new URI("https://github.com/BraghinDev");
                    Desktop.getDesktop().browse(link);
                }catch(Exception erro){
                    System.out.println(erro);
                }
            }
            
        });

        buttonNgrok.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    URI link = new URI("https://ngrok.com");
                    Desktop.getDesktop().browse(link);
                }catch(Exception erro){
                    System.out.println(erro);
                }
            }
            
        });
        buttonGithub.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        buttonNgrok.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        
        panel.add(label);
        Object[] buttons= {buttonGithub, buttonNgrok};
        
        JOptionPane.showOptionDialog(null, panel, "Credits", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null, buttons, buttons[0]);
    }

    /**
     * Close the server.
     */
    public void exitMainMenu() {
        if(isRunning) {
            this.stopServer();
        }
        this.logMenu.closeLogMenu();
        dispose();
    }

    /**
     * Window to confirm close server
     */
    public int confirmClose() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave the server?", "Exit Server", JOptionPane.YES_NO_OPTION);
        System.out.println(option);
        return option;
    }
    
}
