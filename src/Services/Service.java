/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Medicaments;
import Entities.Ordonnance;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ASSOUMA
 */
public class Service {
    
    public  ArrayList<Medicaments> getListBlog(Map m){
        
        ArrayList<Medicaments> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Medicaments");
        
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Medicaments p = new Medicaments();
            
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            Double code = (Double) f.get("code");
            p.setCode(code);
            
            p.setName((String)f.get("name"));
            Double prix = (Double) f.get("prix");
            p.setPrix(prix);
            Double stock = (Double) f.get("stock");
            p.setStock(stock);
            String description = (String) f.get("description");
            
            listMedicaments.add(p);  
        }        
        return listMedicaments;
        
    }
    public  ArrayList<String> getListCategories(Map m){
        ArrayList<String> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Categories");
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
          
            listMedicaments.add((String)f.get("nom"));  
        }        
        return listMedicaments;
        
    }
    public  ArrayList<Ordonnance> getListord(Map m){
        ArrayList<Ordonnance> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Ordonnance");

        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            Ordonnance p = new Ordonnance();
       
            Double id = (Double) f.get("id");
            p.setId(id.intValue());
            Map medicament = ((Map) f.get("medicaments")) ;
            Map categorie = ((Map) f.get("Categorie"));
            Map Consultation = ((Map) f.get("Consultation"));
            Map Patient = ((Map) f.get("Patient"));
            Map Users = ((Map) f.get("Users"));
            
            String description = (String) f.get("description");
            Double nbrJours = (Double) f.get("nbrJrs");
            Double nbrPaquets = (Double)f.get("nbrPaquets");
            Double nbrDoses = (Double)f.get("nbrDoses");
            Double nbrFois = (Double)f.get("nbrFois");
            System.out.println(categorie);
            p.setCategorie((String)categorie.get("nom"));
            p.setConsultation((Double)Consultation.get("numC")+"");
            p.setDescription(description);
            p.setMedicaments((String)medicament.get("name"));
            p.setNbrDoses(nbrDoses);
            p.setNbrFois(nbrFois);
            p.setNbrJours(nbrJours);
            p.setNbrPaquets(nbrPaquets);
            p.setPatient((String)Patient.get("nom"));
            p.setUser((String)Users.get("email"));
            listMedicaments.add(p);  
        }        
        return listMedicaments;
        
    }
    public  ArrayList<String> getListConsultations(Map m){
        ArrayList<String> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Consultation");
        System.out.println("root "+m);
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            listMedicaments.add((Double)f.get("numC")+"");  
        }        
        return listMedicaments;
        
    }
    public  ArrayList<String> getListPatients(Map m){
        ArrayList<String> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Patients");
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            listMedicaments.add((String)f.get("nom"));  
        }        
        return listMedicaments;
        
    }
    public  ArrayList<String> getListNotifications(Map m){
        ArrayList<String> listMedicaments = new ArrayList<>();
        ArrayList d = (ArrayList)m.get("Notifications");
        for(int i = 0; i<d.size();i++){
            Map f =  (Map) d.get(i);
            listMedicaments.add((String)f.get("message"));  
        }        
        return listMedicaments;
        
    }
}
