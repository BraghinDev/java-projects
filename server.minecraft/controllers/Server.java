package controllers;

import java.time.LocalTime;

import javax.swing.JOptionPane;

public class Server extends Thread{
 
    // default values 
    private String port     = "25565";
    private String path     = "C:/Ngrok/ngrok.exe";
    private String protocol = "tcp";
    private String flag     = "-region";
    private String region   = "sa";

    // logmenu
    private LogMenu logMenu;

    // process
    private Process process;
    private ProcessBuilder pb;

    public Server(LogMenu logMenu) {
        this.logMenu = logMenu;
    }

    public void run() {
        // tries instantiate a new process to run independently of the main window
        try {
            String[] prog = {path, protocol,flag, region, port};
            String status = "";
            String defaultMessage = getTimeUser() + "Server running on port: " + port + "\n";
            int counter = 0;

            pb = new ProcessBuilder(prog);
            process = pb.start();         

            logMenu.getTextArea().append(defaultMessage);

            // updates the log menu
            while(process.isAlive()) 
            {
                sleep(1000);
                counter++;
                status = "\r" + getTimeUser() + "time running: " + transformSecondsInTime(counter);
                logMenu.getTextAreaTime().setText("");
                logMenu.getTextAreaTime().append(status);
            }

        } catch (Exception err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error on start server. Please, try again.", "Fail",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void stopProcess() {
        this.process.destroy();
        this.logMenu.getTextArea().append(getTimeUser() + "stopping server...\n");
    }

    public String transformSecondsInTime(int seconds) {
        String returns = "";
        int hour = 0;
        int minute = 0;
        int second = 0;

        minute = seconds / 60;
        hour    = minute / 60;
        second = seconds - (hour * 3600) - (minute * 60);

        if(hour < 10) {
            returns += "0" + hour + ":";
        } else {
            returns += hour + ":";
        }

        if(minute < 10) {
            returns += "0" + minute + ":";
        } else {
            returns += minute + ":";
        }

        if(second < 10) {
            returns += "0" + second;
        } else {
            returns += second;
        }

        return returns;
    }

    // gets the current time of the system then format to be placed in the log window
    public String getTimeUser() {
        return "["+ LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() +":" + LocalTime.now().getSecond() +  "]"+ System.getProperty("user.name")+ "> ";
    }

}
