/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.employee.registry;

import Models.Employees;
import java.util.Scanner;

/**
 *
 * @author fci
 */
public class SimpleEmployeeRegistry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String display = "------------------------------------------\n"
                +"1-Enter 1 to Display All Employees\n"
                + "2-Enter 2 to get Employee by ID\n"
                + "3-Enter 3 to insert Employee\n"
                + "4-Enter 4 to edit Employee\n"
                + "5-Enter 5 to delete Employee\n"
                + "6-Enter 6 to Exit\n";

        Scanner sc = new Scanner(System.in);
        int value;
        Employees employee = new Employees();
        while (true) {
            System.out.println(display);
            value = sc.nextInt();
            if (value == 1) {
                System.out.println("---------------------------------------------");
                employee.Display(employee.getAllEmployees());
                
            }
           else if (value == 2 )
           {
               System.out.println("------------------------------------------");
               System.out.print("ID = ");
               int id = sc.nextInt();
              // Employees emp = new Employees();
               employee.getEmployeeByID(id);
               System.out.println(employee.toString());
               
             }
           else if (value == 3)
           {
               System.out.println("------------------------------------------");
               Employees emp = null;
               System.out.print("Enter Employee Name : ");
               employee.setName(sc.next());
               System.out.print("Enter Employee Salary : ");
               employee.setSalary(sc.nextDouble());
               emp = employee.addEmployee();
               System.out.println(emp.toString());
           }
           else if (value == 4 )
           {
               System.out.println("------------------------------------------");
               Employees emp = new Employees();
               System.out.print("Enter ID of Employee that you want to edit : ");
               
               emp.setEmpId(sc.nextInt());
               System.out.print("Enter Name : ");
               emp.setName(sc.next());
               System.out.print("Enter Salary : ");
               emp.setSalary(sc.nextDouble());
               employee.updateEmployee(emp);
              
           }
           else if(value == 5 )
           {
               System.out.println("Enter Id of employee That you want to delete : ");
               Employees emp = new Employees();
               if(emp.deleteEmployee(sc.nextInt()))
                   System.out.println("Employee deleted successfuly");;
           }
           else if(value == 6 )
               break;
        }

    }

}
