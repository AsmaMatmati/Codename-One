/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;




import Entities.Medicaments;
import Entities.Ordonnance;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class WebService {
    static Map h;
    static String status ="";
    static int c ;
    static String lg ;
    
    public static Map<String, Object> getResponse(String url){
        url = "http://127.0.0.1:8000/"+url;
        System.out.println("url---------------"+url);
        ConnectionRequest r = new ConnectionRequest();
        System.out.println("url ::::::::: "+url);
        r.setUrl(url);
        r.setPost(false);
        System.out.println("url   :   "+r);
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                System.out.println(targetReader);
                h= p.parseJSON(targetReader);
                
            } catch (IOException ex) {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
 
        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h; 
    }
    
    public void addMediacament(Medicaments m){
        
        String url = "http://127.0.0.1:8000/AddMedicament";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("categorie", m.getCategorie());
     con.addArgument("nom", m.getName());
     con.addArgument("prix", m.getPrix()+"");
     con.addArgument("code", m.getCode()+"");
     con.addArgument("stock", m.getStock()+"");
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                       
                        if(response == 200){
                            Dialog.show("Confirmation", "Médicament ajouté avec succé", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ce médicament existe déja", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void EditMediacament(Medicaments m){
        
        String url = "http://127.0.0.1:8000/EditMedicament/"+m.getId();
        ConnectionRequest con = new ConnectionRequest();
     
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("categorie", m.getCategorie());
     con.addArgument("nom", m.getName());
     con.addArgument("prix", m.getPrix()+"");
     con.addArgument("code", m.getCode()+"");
     con.addArgument("stock", m.getStock()+"");
     
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                       
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ERROR", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void DeleteMediacament(int id){
        
        String url = "http://127.0.0.1:8000/DeletMedicament/"+id;
        ConnectionRequest con = new ConnectionRequest();
   
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
    
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                       
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ERROR", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void AddOrdonnance(Ordonnance o){
        
        String url = "http://127.0.0.1:8000/AddOrdonnance";
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("categorie", o.getCategorie());
     con.addArgument("consultation", o.getConsultation());
     con.addArgument("description", o.getDescription());
     con.addArgument("medicaments", o.getMedicaments());
     con.addArgument("Patient", o.getPatient()+"");
     con.addArgument("nbrDoses", o.getNbrDoses()+"");
     con.addArgument("NbrFois", o.getNbrFois()+"");
     con.addArgument("NbrJours", o.getNbrJours()+"");
     con.addArgument("NbrPackets", o.getNbrPaquets()+"");
     con.addArgument("user", MyApplication.email);
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                       
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ERROR", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
     public void EditOrdonnance(Ordonnance o){
        
        String url = "http://127.0.0.1:8000/EditOrdonnance/"+o.getId();
        ConnectionRequest con = new ConnectionRequest();
        
    
    
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.addArgument("categorie", o.getCategorie());
     con.addArgument("consultation", o.getConsultation());
     con.addArgument("description", o.getDescription());
     con.addArgument("medicaments", o.getMedicaments());
     con.addArgument("Patient", o.getPatient()+"");
     con.addArgument("nbrDoses", o.getNbrDoses()+"");
     con.addArgument("NbrFois", o.getNbrFois()+"");
     con.addArgument("NbrJours", o.getNbrJours()+"");
     con.addArgument("NbrPackets", o.getNbrPaquets()+"");
     con.addArgument("user", MyApplication.email);
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                        
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ERROR", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
     public void DeleteOrdonnance(int id){
        
        String url = "http://127.0.0.1:8000/DeletOrdonnance/"+id;
        ConnectionRequest con = new ConnectionRequest();
        
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
     
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                       
                        if(response == 200){
                            Dialog.show("Confirmation", "success", "Ok", null);
                        }else{
                           Dialog.show("Erreur", "ERROR", "Ok", null); 
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void FlagNotifications(String link){
        
        String url = link;
        ConnectionRequest con = new ConnectionRequest();
   
     con.setUrl(url);
     con.addRequestHeader("X-Requested-With", "XMLHttpRequest");
   
     con.setPost(true);
        System.out.println(url);
      con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        int response = evt.getResponseCode();
                      
                        if(response == 200){
                            System.out.println("Success");
                        }else{
                            System.out.println("Error");
                        }
                    }
                });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
