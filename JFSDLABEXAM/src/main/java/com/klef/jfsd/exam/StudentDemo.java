package com.klef.jfsd.exam;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

public class StudentDemo {
	
	public static void main(String args[]) {
		StudentDemo operations = new StudentDemo();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
		
        while (flag) {
            System.out.println("Select an option:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students (Complete Object)");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    operations.addStudent();
                    break;
                case 2:
                    operations.displayAllStudentsCompleteObj();
                    break;
               
                case 0:
                    flag = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }
        scanner.close();
    }
	
	  public void addStudent() {
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");

	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();

	        Transaction t = session.beginTransaction();

	        Student student = new Student();
	        student.setName("Sai");
	        student.setAge(21);
	        student.setGender("Male");
	        student.setEmail("sai@gmail.com");
	        student.setAddress("jrg");

	        session.persist(student);

	        t.commit();
	        System.out.println("Student Added Successfully");

	        session.close();
	        sf.close();
	    }

	    public void displayAllStudentsCompleteObj() {
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");

	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();

	        String hql = "from Student"; 

	        Query<Student> qry = session.createQuery(hql, Student.class);

	        List<Student> studentList = qry.getResultList();
	        System.out.println("Total Students: " + studentList.size());

	        for (Student s : studentList) {
	            System.out.println("Id: " + s.getId());
	            System.out.println("Name: " + s.getName());
	            System.out.println("Age: " + s.getAge());
	            System.out.println("Gender: " + s.getGender());
	            System.out.println("Email: " + s.getEmail());
	            System.out.println("Address: " + s.getAddress());
	        }

	        session.close();
	        sf.close();
	    }
		
		
		
		
		
		
		
		
		
		
	}


