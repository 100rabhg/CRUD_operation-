package bean;

public class Task {
	
	private int id ;
	private String task;
	private boolean status ;
	
//	if true task complete 
//	if false task remaining
	
	public int getId() {
		return id;
	}
	public String getTask() {
		return task;
	}
	public boolean isStatus() {
		return status;
	}
	
	public Task setId(int id) {
		this.id = id;
		return this;
	}
	public Task setTask(String task) {
		this.task = task;
		return this;
	}
	public Task setStatus(boolean status) {
		this.status = status;
		return this;
	}
	
	
	
}
