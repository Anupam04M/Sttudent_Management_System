
package dataAcessObject;

import com.mysql.cj.jdbc.DatabaseMetaData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;


import connectionProvider.CP;
import entity.Student;

public class StudentDAO {
    public static boolean insertStudentToDB(Student student){
        
        boolean status =false;
        try{
            Connection con = CP.getConnection();
            DatabaseMetaData meta=  (DatabaseMetaData) con.getMetaData();
            ResultSet set=meta.getTables(null,null,"student", null);
            if(set.next()){
                
                //Table Exist So insert...
                String insertQuery= "INSERT INTO Students(sName,sNumber,sCity) values(?,?,?)";
                PreparedStatement preparedStatement= con.prepareStatement(insertQuery);
                preparedStatement.setString(1,student.getName());
                preparedStatement.setString(2,student.getNumber());
                preparedStatement.setString(3,student.getCity());
                
                preparedStatement.executeUpdate(); 
            }
            else{
                
                //Crete Table....
                String createTable = "create table students(sid int auto_increment,sName varchar(100), sNumber varchar(10),sCity varchar(100),primary key(sid))";
                Statement statement= con.createStatement();
                statement.executeUpdate(createTable);
                
                //Now Insert.....
                
                String insertQuery= "INSERT INTO students(sName,sNumber,sCity) values(?,?,?)";
                PreparedStatement preparedStatement= con.prepareStatement(insertQuery);
                preparedStatement.setString(1,student.getName());
                preparedStatement.setString(2,student.getNumber());
                preparedStatement.setString(3,student.getCity());
                
                preparedStatement.executeUpdate();
               
            }
            status= true;
        }
        catch(Exception e){
            e.printStackTrace();
            return status;
        }
       
        return status;
    }
    
    
    public static boolean updateStudent(int sid,int option , String val){
         boolean status =false;
         try{
             Connection con = CP.getConnection();
             if(option==1){
                 //Update name
                 String query= "Update students set sName = ? where sid =?";
                 PreparedStatement preparedStatement= con.prepareStatement(query);
                 preparedStatement.setString(1,val);
                 preparedStatement.setInt(2,sid); 
                 
                
                preparedStatement.executeUpdate();
             }
             else if(option==2){
                 //update number
                 String query= "Update students set sNumber = ? where sid =?";
                 PreparedStatement preparedStatement= con.prepareStatement(query);
                 preparedStatement.setString(1,val);
                 preparedStatement.setInt(2,sid); 
                 
                
                preparedStatement.executeUpdate();
             }
             else if(option ==3){
                 //update city
                 String query= "Update students set sCity = ? where sid =?";
                 PreparedStatement preparedStatement= con.prepareStatement(query);
                 preparedStatement.setString(1,val);
                 preparedStatement.setInt(2,sid); 
                 
                
                preparedStatement.executeUpdate();
             }
             else{
                 System.out.println("Please enter 1 or 2 or 3");
             }
             status=true;
             
         }
        catch(Exception e){
            e.printStackTrace();
            return status;
        }
        return status;
    }
    
    public static boolean deleteStudent(int sid)    {
        boolean status =false;
        try{
            Connection con = CP.getConnection();
           
            String query= "DELETE from students where sid = ?";
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setInt(1,sid);
            
                
            preparedStatement.executeUpdate();
            status= true; 
            
        }
        catch(Exception e){
            e.printStackTrace();
            return status;
        }
        return status;
    }
    
    public static void displayStudent(){
        
        try{
            Connection con = CP.getConnection();
            String query= "Select * from students";
            Statement statement= con.createStatement();
            ResultSet set =statement.executeQuery(query); 
            
            int c=0;
            
            while(set.next()){
                c++;
                System.out.println("Student Id= "+set.getInt(1));
                System.out.println("Student Name= "+set.getString(2));
                System.out.println("Student Number= "+set.getString(3));
                System.out.println("Student City= "+set.getString(4));
                System.out.println("**************************************************");
            }
            if(c==0){
                System.out.println("No Data Found! Please Insert Some Data First To Display.");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
            
    }
}
