package extension;

import java.util.ArrayList;

import client.IDisplayer;
import client.IModify;
import client.ITime;
import client.ITimeManager;

public class EarthManager implements ITimeManager {

	private ArrayList<IModify> listModifiers;
	private IDisplayer aff;
	private EarthTime t;
	private int modifyValue;
	
	private int hourLimit;
	private int minuteLimit;
	private int secondLimit;

	public EarthManager() {
		listModifiers = new ArrayList<IModify>();
		
		this.hourLimit = 24;
		this.minuteLimit = 60;
		this.secondLimit = 60;
		
		this.t = new EarthTime(this.hourLimit,this.minuteLimit,this.secondLimit, false);
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#addModifiers()
	 */
	public void addModifiers(){
		int i = 0;
		for (IModify m : listModifiers) {
			aff.addButtons(m.getName(), i);
			++i;
		}
	}
	
	/* (non-Javadoc)
	 * @see client.ITimeManager#removeModifier(client.IModify)
	 */
	public void removeModifier(IModify im) {
		this.listModifiers.remove(im);
		this.aff.removeButton(im.getName());
	}
	
	/* (non-Javadoc)
	 * @see client.ITimeManager#updateAff()
	 */
	public void updateAff()
	{
		aff.showInfo(t.toString());
	}
	
	/* (non-Javadoc)
	 * @see client.ITimeManager#IAmNotify(int)
	 */
	@Override
	public void IAmNotify(int i) {
		System.out.println("EarthManager::IAmNotify : i :"+i+" taile listModifiers : "+listModifiers.size());
		IModify im = listModifiers.get(i);
		im.modify();
		
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#addModifier(client.IModify)
	 */
	@Override
	public void addModifier(IModify m) {
		System.out.println("EarthManager::addModifiers : modify : "+m.getName()+" taille listModifiers : "+listModifiers.size());
		this.listModifiers.add(m);
		// TODO Auto-generated method stub
		
	}

	
	/* (non-Javadoc)
	 * @see client.ITimeManager#setAffichage(client.IDisplayer)
	 */
	public void setAffichage(IDisplayer a)
	{
		aff = a;
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#getTime()
	 */
	public ITime getTime() {
		return t;
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#setTime(client.ITime)
	 */
	public void setTime(ITime t) {
		this.t = (EarthTime) t;
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#getModifyValue()
	 */
	public int getModifyValue() {
		return modifyValue;
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#setModifyValue(int)
	 */
	public void setModifyValue(int modifyValue) {
		this.modifyValue = modifyValue;
	}

	/* (non-Javadoc)
	 * @see client.ITimeManager#getRefresh()
	 */
	@Override
	public int getRefresh() {
		return t.getRefresh();
	}
}
