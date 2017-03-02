package framework;

import java.util.ArrayList;
import java.util.List;

public class Constraint {
	
	private List<String> constraints;

	public Constraint(List<String> constraints) {
		this.constraints = constraints;
	}
	
	public Constraint() {
		this.constraints = new ArrayList<String>();
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}
	
	public void addContraints(String constraint) {
		this.constraints.add(constraint);
	}
}
