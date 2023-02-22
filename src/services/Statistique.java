/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.chart.XYChart;

/**
 *
 * @author LENOVO
 */    

public class Statistique {
    Connection cnx2;
    public void Statistique() throws SQLException{
        String sql ="select DateVent,QteVendue FROM HistoriqueVente WHERE DateVent !='' GROUP BY DateVent ORDER BY TIMESTAMP(DateVent) ASC LIMIT 9";
      ResultSet rs;
        PreparedStatement ste = cnx2.prepareStatement(sql);
    XYChart.Series chart = new XYChart.Series();
    try{
        rs= ste.executeQuery();
        while(rs.next()){
            chart.getData().add(new XYChart.Data(rs.getString(1),rs.getInt(2)) );
        }
        
    }catch (Exception e){ System.err.println(e.getMessage());}
    
    }
}
