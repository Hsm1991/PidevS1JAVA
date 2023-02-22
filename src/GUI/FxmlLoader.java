/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author user
 */
public class FxmlLoader {
    
    private Pane view ; 
    
    public Pane getPage(String filename) throws FileNotFoundException, IOException 
    {
     URL fileUrl = testwindow.class.getResource("/Gui/"+filename+".fxml");
     
     if (fileUrl == null) {
         
         throw new java.io.FileNotFoundException("file not found");
     }
     view = new FXMLLoader().load(fileUrl);
    return view;
    } 
}
