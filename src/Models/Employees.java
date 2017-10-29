/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Connections.db_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fci
 */
public class Employees {

    private int EmpId;
    private String Name;
    private double Salary;

    public Employees() {
    }

    public Employees(String name, double salary) {
        this.Name = name;
        this.Salary = salary;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    @Override
    public String toString() {
        return "Employees : " + "EmpId=" + EmpId + ", Name=" + Name + ", Salary=" + Salary;
    }


    public Employees addEmployee() {

        try {

            Connection con = db_Connection.getActiveConnection();
            String query = "insert into employee (Name, Salary) values (?,?);";

            PreparedStatement statement = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, this.Name);
            statement.setDouble(2, this.Salary);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                this.EmpId = rs.getInt(1);
            }
            db_Connection.closeConnection();
            return this;
        } catch (SQLException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        db_Connection.closeConnection();
        return null;
    }

    public Employees getEmployeeByID(int Id) {
        try {
            Connection conn = db_Connection.getActiveConnection();
            //System.out.println(conn);
            String query = "Select * from employee where EmpId = ? ;";
           // System.out.println("ID "+Id);
            PreparedStatement stmt;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
             Employees emp = new Employees();
            if (rs.next()) {
                
                this.EmpId = rs.getInt(1);
                this.Name = rs.getString("Name");
                this.Salary = rs.getDouble("Salary");
             //   System.out.println("Innnnnnn "+ this.EmpId + "  "+this.Name+"  "+this.Salary);
            }
            db_Connection.closeConnection();
           // this.toString();
            return this;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db_Connection.closeConnection();
        return null;
    }

    public boolean deleteEmployee(int Id) {
        try {
            Connection con = db_Connection.getActiveConnection();
            String query = "delete from employee where EmpId = ?";

            PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
            statement.setInt(1, Id);
            statement.executeUpdate();
            db_Connection.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        db_Connection.closeConnection();
        return false;
    }

    public Employees updateEmployee(Employees employee) {
        Employees emp = getEmployeeByID(employee.EmpId);
        if (emp == null) { // Employee doesn't exists 
            System.out.println("Employee Doesn't Exist");
            return null;
        }

        try {
            Connection conn = db_Connection.getActiveConnection();
            String query = "update employee set Name = ? , Salary= ? where EmpId= ? ;";

            // PreparedStatement stmt;
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //stmt = conn.prepareStatement(query);
            stmt.setString(1, employee.Name);
            stmt.setDouble(2, employee.Salary);
            stmt.setInt(3, employee.EmpId);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.EmpId = rs.getInt(1);
                this.Name = rs.getString("Name");
                this.Salary = rs.getDouble("Salary");

                return this;
            }
            db_Connection.closeConnection();
            return this;
        } catch (SQLException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        db_Connection.closeConnection();
        return null;
    }

    public ArrayList<Employees> getAllEmployees() {
        try {
            Connection conn = db_Connection.getActiveConnection();
            System.out.println(conn);
            String query = "Select * from Employee ";
            ArrayList<Employees> Employees = new ArrayList<>();
            PreparedStatement stmt;
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            // System.out.println("Display Employees : " );
            while (rs.next()) {
                Employees employee = new Employees();
                employee.EmpId = rs.getInt(1);
                employee.Name = rs.getString("Name");
                employee.Salary = rs.getDouble("Salary");

                //  System.out.println(employee.toString());
                Employees.add(employee);
            }
            db_Connection.closeConnection();
            return Employees;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db_Connection.closeConnection();
        return null;
    }

    public void Display(ArrayList<Employees> employees) {
        System.out.println("Display Employees : ");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).toString());
        }
    }
}
