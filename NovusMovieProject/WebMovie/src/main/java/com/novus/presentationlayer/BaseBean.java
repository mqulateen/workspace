package com.novus.presentationlayer;

import com.novus.appvariables.AppVariables;
import com.novus.classlayer.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.model.SelectItem;

/**
 *
 * @author mqul
 * to be used in all beans that requires some generic function which lives in this class
 */
public abstract class BaseBean {
    
    //generic method used when populating dropdowns
    protected <T>List populateDropDownList(List<T> dataSource){
        List<SelectItem> siList = new ArrayList();
         
        //specify default dropdown item
        if(dataSource.size() > 1){
            //No selection option = <--SELECT--> 
            SelectItem noSelect = new SelectItem(null, AppVariables.WebProperties.dropDownDefault);
            noSelect.setNoSelectionOption(true);
            siList.add(noSelect);
        }
        
        //generate SelectItem objects for dropdown based on data type
        if(dataSource != null && dataSource.get(0) instanceof SimplisticFilm){
            List<SimplisticFilm> tmpList = (List<SimplisticFilm>)dataSource;
            siList.addAll(tmpList.stream()
                 .map(f -> new SelectItem(f.getFilmID(), f.getFilmName()))
                 .collect(Collectors.toList()));
        }else if(dataSource != null && dataSource.get(0) instanceof Person){
            List<Person> tmpList = (List<Person>)dataSource;
            siList.addAll(tmpList.stream()
                 .map(p -> new SelectItem(p.getID(), p.getName()))
                 .collect(Collectors.toList()));
        }else if(dataSource != null && dataSource.get(0) instanceof String){
            List<String> tmpList = (List<String>)dataSource;
            tmpList.stream()
                .map(a -> siList.add(new SelectItem(a)))
                .collect(Collectors.toList());
        }
        
        return siList;
    }
    
}
