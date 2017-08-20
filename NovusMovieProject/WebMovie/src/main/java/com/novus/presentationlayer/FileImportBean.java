package com.novus.presentationlayer;

import com.novus.businesslayer.MovieBusinessLayer;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mqul
 */
@RequestScoped
@Named("fileimport")
public class FileImportBean implements Serializable{
    private Part file; // +getter+setter
    private String fileName;
    
    public void submit() throws ClassNotFoundException, SQLException, IOException{
        boolean isSuccess = false;
        String message = "";
        
        //http://stackoverflow.com/questions/27677397/how-to-upload-file-using-jsf-2-2-hinputfile-where-is-the-saved-file
        
        try (InputStream input = file.getInputStream()) {
            fileName = "CSVDATA_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) +".csv";
            Files.copy(input, new File("/Users/mqul/NetBeansProjects/NovusMovieProject/Data/", fileName).toPath());
            
            MovieBusinessLayer mbl = new MovieBusinessLayer();
            isSuccess = mbl.importData(fileName);
            message = mbl.getMessage();
        }catch (IOException e) {
            // Show faces message?
        }finally{
            Files.delete(new File("/Users/mqul/NetBeansProjects/NovusMovieProject/Data/", fileName).toPath());
        }
        
        if(isSuccess){
            FacesContext.getCurrentInstance().addMessage("resultMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage("resultMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, message , null));
        }  
    }
    
    public Part getFile(){return file;}
    public void setFile(Part file){this.file = file;}
}
