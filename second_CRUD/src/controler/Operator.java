package controler;

import java.util.ArrayList;
import java.util.Scanner;

import bean.Task;
import dao.DBOpearation;


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
					} else if (inputArr[0].equalsIgnoreCase("-a") || inputArr[0].equalsIgnoreCase("Add")) {

						Task t = new Task();
						for (int i=1; i<inputArr.length-1;i++) {	
							if(t.getTask() == null) 
								t.setTask(inputArr[i]);
							else
								t.setTask(t.getTask() +" "+ inputArr[i]);
						}

						if(DBOpearation.insert(t)) {
							System.err.println("Task adding failed");
						}
						else {
							System.out.println("Task added");
						}
						
					} else if (inputArr[0].equalsIgnoreCase("-u") || inputArr[0].equalsIgnoreCase("update")) {
						Task t = new Task();
						t.setId(Integer.parseInt(inputArr[1]));
						Boolean b = null ;
						boolean isvalid =true;
						for (int i = 2; i < inputArr.length; i++) {
							if (inputArr[i].equalsIgnoreCase("-t")) {
								i++;
								while (inputArr.length > i && !inputArr[i].equalsIgnoreCase("-s") ) {
									if(t.getTask() == null) 
										t.setTask(inputArr[i]);
									else
										t.setTask(t.getTask() +" "+ inputArr[i]);
									i++;
								}
								i--;
							} else if (inputArr[i].equalsIgnoreCase("-s")) {
								i++;
								
								if(inputArr[i].equals("false")) {
									b=false;
								}
								else if(inputArr[i].equals("true")) {
									b=true;
								}
								else {
									System.err.println(inputArr[i] + " is not a valid status");
									isvalid =false;
								}
							}
						}
						if(b!=null) {
							if(t.getTask()==null) {
								t.setTask(DBOpearation.getDataById(t.getId()).getTask());
							}
							t.setStatus(b);
						}
						else  if(isvalid){
							t.setStatus(DBOpearation.getDataById(t.getId()).isStatus());
						}
						if(isvalid) {
							if(DBOpearation.update(t)) {
								System.err.println("Task update failed");
							}
							else {
								System.out.println("Task update successful");
							}
						}
					} else if (inputArr[0].equalsIgnoreCase("-d") || inputArr[0].equalsIgnoreCase("delete")) {
						if(DBOpearation.delete(Integer.parseInt(inputArr[1]))) {
							System.err.println("Task delete failed");
						}
						else {
							System.out.println("Task delete successful");
						}
					} else if (inputArr[0].equalsIgnoreCase("-v") || inputArr[0].equalsIgnoreCase("view")) {
						ArrayList<Task> al = DBOpearation.getData();
						for (Task t :al) {
							System.out.println("Id: "+t.getId()+" Task: "+t.getTask()+" Status: "+t.isStatus());
						}
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
		System.out.println(" Add | -a : Add Task");
		System.out.println("    ex:-  -a TASK");
		System.out.println();
		System.out.println(" update | -u : update data");
		System.out.println("    ex:-  -u ID -t TASK -s STATUS");
		System.out.println("id is COMPALSARY can skip & change position -t, -s ");
		System.out.println();
		System.out.println(" delete | d : delete Task");
		System.out.println("    ex:- -d ID");
		System.out.println();
		System.out.println(" view | -v : View Data");
		System.out.println();
		System.out.println("exit : exit from operation");
		System.out.println();
	}
}