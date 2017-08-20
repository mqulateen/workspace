package com.novus.datalayer;

import com.novus.classlayer.*;
import com.novus.appvariables.AppVariables;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mqul
 * 
 * extract data from CSV or Database and store in respective Object from ClassLayer
 */
public class MovieData {
    
    //read data from CSV file - path provided as param
    public Films getFilmData(String csvPath){
        Films films = new Films();
        String[] line;
        
        try(CSVReader csv = new CSVReader(new FileReader(csvPath));){
            String[] headers = csv.readNext(); //read first line for header strings
           
            while((line = csv.readNext()) != null){
                films = storeLine(line, films);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return films;
    }
    
    //read data from database into objects
    public Films getFilmData(Connection conn) throws ClassNotFoundException, SQLException{
        
        Films films = new Films();
     
        try(CallableStatement cs = conn.prepareCall(String.format("{CALL %s}", AppVariables.Database.storedProcedureName))){
            boolean hasResults = cs.execute(); //execute stored procedure
            
            //retrieve data from the resultset
            try(ResultSet rs = cs.getResultSet()){
                while(rs.next()){
                    String[] line = {rs.getString(AppVariables.Database.filmID)
                                    ,rs.getString(AppVariables.Database.filmName)
                                    ,rs.getString(AppVariables.Database.imdbRating)
                                    ,rs.getString(AppVariables.Database.directorID)
                                    ,rs.getString(AppVariables.Database.directorName)
                                    ,rs.getString(AppVariables.Database.actorID)
                                    ,rs.getString(AppVariables.Database.actorName)
                                    ,rs.getString(AppVariables.Database.filmYear)};
                                  
                    films = storeLine(line, films); //overwrite Films object with new data
                }
            }
            
            return films;    
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            conn.close(); //close connection to db once try block is complete
        }
    } 
    
    /*store data as objects
        new film: with actors and directors
        existing film: add actor/director if not already present*/                        
    private Films storeLine(String[] line, Films films){
        Films tmpFilms = films;
        
        //ensure record in not already present
        if(tmpFilms.stream().anyMatch(item -> item.filmID.equals(line[AppVariables.filmID]))){
            Film tmpFilm = tmpFilms.stream().filter(item -> item.filmID.equals(line[AppVariables.filmID])).findFirst().get();//.collect(Collectors.toList()).get(0);

            if(tmpFilm.directors.stream().anyMatch(item -> item.getID().equals(line[AppVariables.directorID]))){

            }else{
                Director director = this.getDirectorFromData(line);
                tmpFilm.directors.add(director);
            }
            if(tmpFilm.actors.stream().anyMatch(item -> item.getID().equals(line[AppVariables.actorID]))){

            }else{
                Actor actor = this.getActorFromData(line);
                tmpFilm.actors.add(actor);
            }
        }else{
            Film film = this.getFilmFromData(line);
            tmpFilms.add(film);
        }
        
        return tmpFilms;
    }
    
    private Director getDirectorFromData(String[] line){
        Director director = new Director(line[AppVariables.directorID].trim(), 
                                         line[AppVariables.directorName].trim());
        return director;
    }
    
    private Actor getActorFromData(String[] line){
        Actor actor = new Actor(line[AppVariables.actorID].trim(), 
                                line[AppVariables.actorName].trim());
        return actor;
    }
    
    private Film getFilmFromData(String[] line){
        
        Director director = this.getDirectorFromData(line);
        Actor actor = this.getActorFromData(line);
        
        Film film = new Film(line[AppVariables.filmID].trim(),
                             line[AppVariables.filmName].trim(),
                             line[AppVariables.imdbRating].trim(),
                             line[AppVariables.filmYear].trim());
        film.directors.add(director);
        film.actors.add(actor);
        
        return film;
    }
}