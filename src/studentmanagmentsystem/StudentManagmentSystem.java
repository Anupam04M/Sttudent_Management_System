
package studentmanagmentsystem;

import dataAcessObject.StudentDAO;
import entity.Student;
import java.util.Scanner;


public class StudentManagmentSystem {
    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        int op=0,id=0;
        boolean status=false;
        String name="",number="",city="";
        while(true){
          System.out.println("Hello Welcome to Student Management App!"); 
          System.out.println("Enter 1 to Display all the Students"); 
          System.out.println("Enter 2 to Insert all the Students");
          System.out.println("Enter 3 to Update all the Students");
          System.out.println("Enter 4 to Delete all the Students");
          System.out.println("Enter 5 To Exit");
          
          op=sc.nextInt();
          
          if(op==1){
              StudentDAO.displayStudent();
          }
          else if(op==2){
              System.out.println("Enter the name of the student: ");
              name=sc.nextLine();
              System.out.println("Enter the number of the student: ");
              number=sc.nextLine();
              System.out.println("Enter the city of the student: ");
              city=sc.nextLine();
              
              Student st= new Student(name,number,city);
              status=StudentDAO.insertStudentToDB(st);
              
              if(status){
                  System.out.println("Success!");
              }
              else{
                  System.out.println("Something west wrong!!please try again");
              }
          }
          else if(op==3){
              
               System.out.println("We suggest you to kindly display all the Students for confirming the student id, whom to be update");
               System.out.println("Enter the student id: ");
               id= sc.nextInt();
               
               System.out.println("Enter 1 to Update Name"); 
               System.out.println("Enter 2 to Update Phone");
               System.out.println("Enter 3 to Update City");
               op=sc.nextInt();
               
               
               if(op==1){
                   //Name Update
                   System.out.println("Enter the new name");
                   name=sc.nextLine();
                   status = StudentDAO.updateStudent(id, op, name);
                   
                   if(status){
                      System.out.println("Success!");
                   }
                   else{
                      System.out.println("Something west wrong!!please try again");
                   }
               }
               else if(op==2){
                   //phone update
                   System.out.println("Enter the new phone number");
                   number=sc.nextLine();
                   status= StudentDAO.updateStudent(id, op, number);
                   
                   if(status){
                      System.out.println("Success!");
                   }
                   else{
                      System.out.println("Something west wrong!!please try again");
                   }
               }
               else if(op==3){
                   System.out.println("Enter the new city");
                   city =sc.nextLine();
                   status= StudentDAO.updateStudent(id, op, city);
                   if(status){
                      System.out.println("Success!");
                   }
                   else{
                      System.out.println("Something west wrong!!please try again");
                   }
               }
               else{
                   System.out.println("Please enter the right option");
                   
               }
          }
          else if(op==4){
               System.out.println("We suggest you to kindly display all the Students for confirming the student id, whom to be deleted");
               System.out.println("Enter the student id: ");
               id= sc.nextInt();
               
               status= StudentDAO.deleteStudent(id);
               if(status){
                    System.out.println("Success!");
                }
                else{
                    System.out.println("Something west wrong!!please try again");
                }
          }
          else if(op==5){
              System.out.println("Thanks for usding our Appliication!! Hope you enjoyed!!");
          }
          else{
              System.out.println("Invalid Input!! Please Enter Between 1 To 5..");
          }
        }
       
    }
    
}
