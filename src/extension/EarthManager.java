package extension;

import java.util.ArrayList;

import client.IDisplayer;
import client.IModify;
import client.ITimeManager;
import client.Time;

public class EarthManager implements ITimeManager {

	private ArrayList<IModify> listModifiers;
	private IDisplayer aff;
	private Time t;
	private int modifyValue;
	
	private int hourLimit;
	private int minuteLimit;
	private int secondLimit;
	
	/*
	private ArrayList<Observer> observers;
	

	 */
	public EarthManager() {
		listModifiers = new ArrayList<IModify>();
		
		this.hourLimit = 24;
		this.minuteLimit = 60;
		this.secondLimit = 60;
		
		this.t = new Time(this.hourLimit,this.minuteLimit,this.secondLimit);
		

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
		System.out.println(modifyValue);
		//t.addHour(modifyValue,this.hourLimit);
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
		IModify im = listModifiers.get(i);
		im.modify();
		System.out.println("le modifieur numero :"+ i + " a été apellé");
		
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

	public Time getTime() {
		return t;
	}

	public void setTime(Time t) {
		this.t = t;
	}

	public int getModifyValue() {
		return modifyValue;
	}

	public void setModifyValue(int modifyValue) {
		this.modifyValue = modifyValue;
	}
}
