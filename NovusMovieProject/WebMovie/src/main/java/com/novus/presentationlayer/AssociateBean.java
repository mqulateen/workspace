package com.novus.presentationlayer;

import com.novus.businesslayer.MovieBusinessLayer;
import com.novus.classlayer.Films;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mqul
 */
@RequestScoped
@Named("associatebean")
public class AssociateBean implements Serializable{
    private String selectedFilm;
    private List<String> actorList, directorList;
    
    public List<String> getActorList(){return actorList;}
    public List<String> getDirectorList(){return directorList;}
    public void setActorList(List<String> actorList){this.actorList = actorList;}
    public void setDirectorList(List<String> directorList){this.directorList = directorList;}
    
    public String getSelectedFilm(){return selectedFilm;}
    public void setSelectedFilm(String film){this.selectedFilm = film;}
    
    public List<SelectItem> getFilms() throws SQLException, ClassNotFoundException{
        List<SelectItem> tmpList = new ArrayList<SelectItem>();
        tmpList.add(new SelectItem("<--Select-->"));
        tmpList.addAll(new MovieBusinessLayer().getDistinctFilmsFromDB().stream()
               .map(f -> new SelectItem(f.filmID, f.filmName)).collect(Collectors.toList()));
        
        return tmpList;
    }
    
    public List<SelectItem> getActors(){
        MovieBusinessLayer mbl = new MovieBusinessLayer();
        
        try{
            List<SelectItem> tmpItems = new ArrayList<>();
            mbl.getDistinctActorsFromDB().stream()
                    .map(actor -> tmpItems.add(new SelectItem(actor.personID, actor.personName)))
                    .collect(Collectors.toList());
            
            return tmpItems;
        }catch(SQLException | ClassNotFoundException se){
            se.printStackTrace();
            return null;
        }
    }
    
    public List<SelectItem> getDirectors(){
        MovieBusinessLayer mbl = new MovieBusinessLayer();
        
        try{
            List<SelectItem> tmpItems = new ArrayList<>();
            mbl.getDistinctDirectorsFromDB().stream()
                    .map(director -> tmpItems.add(new SelectItem(director.personID, director.personName)))
                    .collect(Collectors.toList());
            
            return tmpItems;
        }catch(SQLException | ClassNotFoundException se){
            se.printStackTrace();
            return null;
        }
    }
    
    public void submitForm() throws ClassNotFoundException, SQLException, IOException{
       MovieBusinessLayer mbl = new MovieBusinessLayer();
       
       if(actorList != null && directorList != null){
            mbl.associateActorsWithFilm(actorList, selectedFilm);
            mbl.associateDirectorsWithFilm(directorList, selectedFilm);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
       }
       
       Films films = mbl.getFilmsSubset(selectedFilm, null, null, null, null, mbl.getFilms());
       actorList = films.toListDistinctActor().stream().map(a -> a.personID).collect(Collectors.toList());
       directorList = films.toListDistinctDirector().stream().map(d -> d.personID).collect(Collectors.toList());
        
    }
}
