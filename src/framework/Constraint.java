package framework;

import java.util.ArrayList;
import java.util.List;

/** class représentant les contraint que doit respecter un plugin, sous la forme d'une list de tag (String)
 * @author quemard
 *
 */
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
