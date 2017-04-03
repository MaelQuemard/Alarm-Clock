package extension;

import java.util.ArrayList;

import client.IDisplayer;
import client.IModify;
import client.IPlugin;
import client.ITime;
import client.ITimeManager;

public class PandoraManager implements ITimeManager, IPlugin {

	private ArrayList<IModify> listModifiers;
	private IDisplayer aff;
	private PandoraTime t;
	private int modifyValue;
	
	private int hourLimit;
	private int minuteLimit;
	private int secondLimit;
	
	/*
	private ArrayList<Observer> observers;
	

	 */
	public PandoraManager() {
		listModifiers = new ArrayList<IModify>();
		
		this.hourLimit = 20;
		this.minuteLimit = 100;
		this.secondLimit = 100;
		
		this.t = new PandoraTime(this.hourLimit,this.minuteLimit,this.secondLimit, false);
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
	

	@Override
	public void IAmNotify(int i) {
		System.out.println("PandoraManager::IAmNotify : i :"+i+" taile listModifiers : "+listModifiers.size());
		IModify im = listModifiers.get(i);
		im.modify();
		
	}

	@Override
	public void addModifier(IModify m) {
		System.out.println("PandoraManager::addModifiers : modify : "+m.getName()+" taille listModifiers : "+listModifiers.size());
		this.listModifiers.add(m);
		// TODO Auto-generated method stub
		
	}
	
	public void removeModifier(IModify im) {
		this.listModifiers.remove(im);
		this.aff.removeButton(im.getName());
	}

	
	public void setAffichage(IDisplayer a)
	{
		aff = a;
	}

	public ITime getTime() {
		return t;
	}

	public void setTime(ITime t) {
		this.t = (PandoraTime) t;
	}

	public int getModifyValue() {
		return modifyValue;
	}

	public void setModifyValue(int modifyValue) {
		this.modifyValue = modifyValue;
	}

	@Override
	public int getRefresh() {
		return t.getRefresh();
	}
	
	public int getHourLimit() {
		return hourLimit;
	}

	public int getMinuteLimit() {
		return minuteLimit;
	}

	public int getSecondLimit() {
		return secondLimit;
	}
	
	public ITime getITime(int hour, int min, int s, boolean fixe){
		return new PandoraTime(hour,min,s,fixe);
	}
		
	public String getName() {
		return "PandoraManager";
	}
}