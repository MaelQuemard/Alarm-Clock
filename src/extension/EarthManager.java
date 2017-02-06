package extension;

import java.util.ArrayList;

import client.Time;
import framework.IDisplayer;
import framework.IModify;
import framework.ITimeManager;

public class EarthManager implements ITimeManager {

	private ArrayList<IModify> listModifiers;
	private IDisplayer aff;
	private Time t;
	/*
	private ArrayList<Observer> observers;
	

	 */
	public EarthManager() {
		listModifiers = new ArrayList<IModify>();
		this.t = new Time();
		/*
		this.observers = new ArrayList<Observer>();
		this.t = new Time();
		*/
	}

	public void addModifiers(){
		int i = 0;
		for (IModify m : listModifiers) {
			aff.addButtons(m.getName(), i);
			++i;
		}
	}
	
	public void updateAff()
	{
		aff.showInfo(t.toString());
	}
	/*
	@Override
	public void notifyObservers() {
		t.actualizeTime();
		for (Observer o : observers) {
			o.refresh(this);
		}
	}

	@Override
	public void addObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		this.observers.remove(o);
	}
	*/

	@Override
	public void IAmNotify(int i) {
		System.out.println("le modifieur numero :"+ i + "a été apellé");
		
	}

	@Override
	public void addModifier(IModify m) {
		this.listModifiers.add(m);
		// TODO Auto-generated method stub
		
	}

	
	public void setAffichage(IDisplayer a)
	{
		aff = a;
	}
	

}
