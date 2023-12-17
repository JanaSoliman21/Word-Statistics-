/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Conn {
    
    static Connection con;
    static String sql;
    static Statement s;
    static ResultSet r;
    
    public Connection connect() {
        con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osProject","root","123admin");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OsProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void insertFile (String directory_name, String file_name, String file_path, int words, int is, int are, int you, String Long, String Short) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osProject","root","123admin");
            s = con.createStatement();
            
            sql = "INSERT INTO osproject.words (directory_name, file_name, file_path, words, _is, are, you, Longest, Shortest) VALUES ('"+directory_name+"', '"+file_name+"', '"+file_path+"', '"+words+"', '"+is+"', '"+are+"', '"+you+"', '"+Long+"', '"+Short+"');";
            
            s.execute(sql);
            s.close();
            con.close();
            
            System.out.println("ok");
        }catch (SQLException ee){
            System.out.println(ee.getMessage());
        }
    }
    
    public String[] ShowLongest() {
        String[] stringArray = {}; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osProject","root","123admin");
            s = con.createStatement();
            
            sql = "SELECT Longest FROM osproject.words;";
            
            r = s.executeQuery(sql);
            
            ArrayList<String> stringLong = new ArrayList<>();
            
            while (r.next()) {
                String value = r.getString("Longest");
                stringLong.add(value);
            }
            
            stringArray = stringLong.toArray(new String[0]);
            
            s.close();
            con.close();
            System.out.println("ok");
   
        } catch (SQLException ee){
            System.out.println(ee.getMessage());
        }
        return stringArray;
    }
    
    public String[] ShowShortest() {
        String[] stringArray = {}; 
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osProject","root","123admin");
            s = con.createStatement();
            
            sql = "SELECT Shortest FROM osproject.words;";
            
            r = s.executeQuery(sql);
            
            ArrayList<String> stringLong = new ArrayList<>();
            
            while (r.next()) {
                String value = r.getString("Shortest");
                stringLong.add(value);
            }
            
            stringArray = stringLong.toArray(new String[0]);
            
            s.close();
            con.close();
            System.out.println("ok");
   
        } catch (SQLException ee){
            System.out.println(ee.getMessage());
        }
        return stringArray;
    }
    
    public void deleteRecordFromDatabase(String directory) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/osProject","root","123admin");
            s = con.createStatement();

            sql = "DELETE FROM osproject.words WHERE directory_name = '"+directory+"';";
            s.execute(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    public DefaultTableModel showWordsData() {
        DefaultTableModel model = new DefaultTableModel();

        try {
            con = connect();
            s = con.createStatement();

            sql = "SELECT file_name, file_path, words, _is, are, you, Longest, Shortest FROM osproject.words;";
            r = s.executeQuery(sql);

            // Add columns to the model
            model.addColumn("File Name");
            model.addColumn("File Path");
            model.addColumn("# Words");
            model.addColumn("# is");
            model.addColumn("# are");
            model.addColumn("# you");
            model.addColumn("Longest");
            model.addColumn("Shortest");

            while (r.next()) {
                String fileName = r.getString("file_name");
                String filePath = r.getString("file_path");
                int words = r.getInt("words");
                int _is = r.getInt("_is");
                int are = r.getInt("are");
                int you = r.getInt("you");
                String longest = r.getString("Longest");
                String shortest = r.getString("Shortest");

                // Add a row to the model
                model.addRow(new Object[]{fileName, filePath, words, _is, are, you, longest, shortest});
            }

            // Close the result set, statement, and connection
            r.close();
            s.close();
            con.close();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
        return model;
    }
    
}
