/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author user
 */
public class Notification {
    
    
      public void notif (String text){
     //   Image img = new Image ("/Gui/notiff.png");
          Notifications.create()
                  .text(text)
                  .darkStyle()
                //  .graphic(new ImageView(img))
                  .hideAfter(Duration.seconds(5))
                  .position(Pos.TOP_RIGHT)
                  .show();
          
          
      }
      
}
