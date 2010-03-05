/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.microedition.midlet.*;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Command;
import com.sun.lwuit.Label;
import com.sun.lwuit.Display;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
/**
 * @author Gichamba,Amos
 */
public class Ushahidi extends MIDlet implements ActionListener{
    private Form main,addIncident,viewIncidents,incidents,about;
    private TextArea txaIncident,txtaIncidents,txaAbout;
    private TextField txtLocation;
    private ComboBox cbCategory,cbViewIncident;
    private Dialog thanx;
    private Label lbCategory, lbLocation;

    private Command cmdViewIncidents, cmdSettings,cmdAddIncident,cmdQuit,cmdSubmit,cmdOk,cmdBack,cmdView,cmdCapture,cmdUpload,cmdAbout;

    private Display display;

    public void startApp() {
        Display.init(this);
        try
        {
            Resources res = Resources.open("/res/Ushahidi.res");
            UIManager.getInstance().setThemeProps(res.getTheme("Ushahidi"));
        }
        catch(IOException ex)
        {
        }
        main = new Form("Ushahidi");
        main.addCommandListener(this);
        addIncident = new Form("Add Incident");

        addIncident.addCommandListener(this);
        txaIncident = new TextArea("                          ",500);
        txaIncident.isGrowByContent();
        String categories[] = {"Riot","Rape","Death","Burglary"};
        cbCategory = new ComboBox(categories);
        addIncident.addComponent(cbCategory);
        addIncident.addComponent(txaIncident);
        cmdViewIncidents = new Command("View Incidents");
        cmdSettings = new Command("Settings");
        cmdAddIncident = new Command("+Add Incident");
        cmdQuit = new Command("Quit");
        cmdSubmit = new Command("Submit");
        cmdOk = new Command("Ok");
        cmdBack = new Command("Back");
        cmdView = new Command("View");
        cmdCapture = new Command("Capture Photo");
        cmdUpload = new Command("Video/Image");
        cmdAbout = new Command("About");


        about = new Form("About Ushahidi");
        about.addCommandListener(this);
        txaAbout = new TextArea("Ushahidi is a crisis information crowd-sourcing Platform");
        
        about.addCommand(cmdBack);
        about.addComponent(txaAbout);
        main.addCommand(cmdAbout);
        main.addCommand(cmdQuit);
        main.addCommand(cmdSettings);
        main.addCommand(cmdAddIncident);
        main.addCommand(cmdViewIncidents);

        addIncident.addCommand(cmdSubmit);
        addIncident.addCommand(cmdBack);

        thanx = new Dialog("Thank you ");
        thanx.addCommand(cmdOk);


        lbCategory = new Label("Category");
        lbLocation = new Label("Location");
        txtLocation = new TextField();
        txtaIncidents = new TextArea("incidents...  Incidents.....       ",500);
        txtaIncidents.isGrowByContent();
        cbViewIncident = new ComboBox(categories);
        viewIncidents = new Form("View Incidents");
        viewIncidents.addCommandListener(this);
        viewIncidents.addComponent(lbCategory);
        viewIncidents.addComponent(cbViewIncident);
        viewIncidents.addComponent(lbLocation);
        viewIncidents.addComponent(txtLocation);
        viewIncidents.addCommand(cmdView);
        viewIncidents.addCommand(cmdBack);
        viewIncidents.addCommand(cmdCapture);
        viewIncidents.addCommand(cmdUpload);

        incidents = new Form("");
        incidents.addComponent(txtaIncidents);
        incidents.addCommandListener(this);
        incidents.addCommand(cmdBack);

       display = Display.getInstance();

        main.show();
    }
    public void pauseApp() {
    }
    public void destroyApp(boolean unconditional) {
    }
    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == cmdAddIncident)
        {
            addIncident.show();
        }
        else if(evt.getSource()==cmdSubmit)
        {
           thanx.show();
        }
        else if(evt.getSource()==cmdViewIncidents)
        {
            viewIncidents.show();
        }
        else if(evt.getSource() == cmdBack && display.getCurrent() == addIncident)
        {
            main.show();
        }
        else if(evt.getSource() == cmdBack && display.getCurrent() == viewIncidents)
        {
            main.show();
        }
        else if(evt.getSource() == cmdView && display.getCurrent() == viewIncidents)
        {
            String incident = cbViewIncident.getSelectedItem().toString();
            incidents.setTitle(incident+" in "+txtLocation.getText());
            incidents.show();
        }
        else if(evt.getSource() == cmdBack && display.getCurrent() == incidents)
        {
            viewIncidents.show();
        }
         else if(evt.getSource() == cmdBack && display.getCurrent() == about)
        {
            main.show();
        }
         else if(evt.getSource() == cmdAbout)
         {
             about.show();
         }
        
    }
}
