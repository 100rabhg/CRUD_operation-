package control;

import java.util.Scanner;

import database.DbOperation;
import emp.Employees;

public class Operator {
	public static void main(String[] args) {
		System.out.println("Application start");
		try (Scanner sc = new Scanner(System.in)) {
			boolean flag = true;
			while (flag) {
				String input = sc.nextLine();
				String[] inputArr = input.split(" ");
				try {
					if (inputArr[0].equalsIgnoreCase("-h") || inputArr[0].equalsIgnoreCase("Help")) {
						help();
					} else if (inputArr[0].equalsIgnoreCase("-i") || inputArr[0].equalsIgnoreCase("insert")) {

						Employees e = new Employees();
						e.setId(Integer.parseInt(inputArr[1]));
						e.setName(inputArr[2]);
						e.setDepartment(inputArr[3]);
						e.setSalary(Integer.parseInt(inputArr[4]));

						DbOperation.insert(e);
						
					} else if (inputArr[0].equalsIgnoreCase("-u") || inputArr[0].equalsIgnoreCase("update")) {
						Employees e = new Employees();
						e.setId(Integer.parseInt(inputArr[1]));
						for (int i = 2; i < inputArr.length; i++) {
							if (inputArr[i].equalsIgnoreCase("-n")) {
								i++;
								while (inputArr.length > i && !inputArr[i].equalsIgnoreCase("-d") && !inputArr[i].equalsIgnoreCase("-s") ) {
									if(e.getName()==null) 
										e.setName(inputArr[i]);
									else
										e.setName(e.getName() +" "+ inputArr[i]);
									i++;
								}
								i--;
							} else if (inputArr[i].equalsIgnoreCase("-d")) {
								i++;
								while (inputArr.length > i && (!inputArr[i].equalsIgnoreCase("-n") && !inputArr[i].equalsIgnoreCase("-s") )) {
									if(e.getDepartment()==null) 
										e.setDepartment(inputArr[i]);
									else
										e.setDepartment(e.getDepartment() +" "+ inputArr[i]);
									i++;
								}
								i--;
							} else if (inputArr[i].equalsIgnoreCase("-s")) {
								i++;
								e.setSalary(Integer.parseInt(inputArr[i]));
							}
						}
						DbOperation.update(e);
					} else if (inputArr[0].equalsIgnoreCase("-dl") || inputArr[0].equalsIgnoreCase("delete")) {
						DbOperation.delete(Integer.parseInt(inputArr[1]));
					} else if (inputArr[0].equalsIgnoreCase("-v") || inputArr[0].equalsIgnoreCase("view")) {
						DbOperation.viewData();
					} else if (inputArr[0].equalsIgnoreCase("exit")) 
						flag = false;
					else
						System.err.println(inputArr[0] + " is not a valid command");
				} catch (IndexOutOfBoundsException e) {
					System.err.println("Enter a valid command");
					help();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void help() {
		System.out.println(" help | -h : Help");
		System.out.println(" insert | -i : insert a data");
		System.out.println("    ex:-  -i id name department salary");
		System.out.println();
		System.out.println(" update | -u : update data");
		System.out.println("    ex:-  -u id -n name -d department -s salary");
		System.out.println("id is COMPALSARY can skip & change position -n,-d,-s ");
		System.out.println();
		System.out.println(" delete | dl : delete data");
		System.out.println("    ex:- -dl id");
		System.out.println();
		System.out.println(" view | -v : View Data");
		System.out.println();
		System.out.println("exit : exit from operation");
		System.out.println();
	}

}
