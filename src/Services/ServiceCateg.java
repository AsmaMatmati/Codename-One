/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Catégorie;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class ServiceCateg {
      public ArrayList<Catégorie> Categ;
    
    public static ServiceCateg instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceCateg() {
         req = new ConnectionRequest();
    }

    public static ServiceCateg getInstance() {
        if (instance == null) {
            instance = new ServiceCateg();
        }
        return instance;
    }

    public boolean addCateg(Catégorie c) {
      
        String url = Statics.BASE_URL + "/ajCategory?nom="+ c.getNom();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
                
   

    public ArrayList<Catégorie> parseCateg(String jsonText){
        try {
            Categ=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)CategListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                 Catégorie c =new Catégorie();
            float id =Float.parseFloat(obj.get("id").toString());
            c.setId((int)id); 
            c.setNom(obj.get("nom").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                Categ.add(c);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return Categ;
    }
      
  
    public ArrayList<Catégorie> getAllCateg(){
        String url = Statics.BASE_URL+"/LisCat";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categ = parseCateg(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Categ;
    }

    public boolean deleteCateg(Catégorie c) {
        
         String url = Statics.BASE_URL + "/DelCateg?id=" + c.getId();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean editCateg(Catégorie c) {
    
        String url = Statics.BASE_URL + "/modiCat?id="+c.getId(); //création de l'URL
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
       


    
}
