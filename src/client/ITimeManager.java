package client;

import extension.EarthTime;


public interface ITimeManager {
	public void IAmNotify(int i);
	public void addModifier(IModify m);
	public void  addModifiers();
	public void updateAff();
	public void setAffichage(IDisplayer a);
	public ITime getTime();
	public void setTime(EarthTime t);
	public int getModifyValue();
	public void setModifyValue(int modifyValue);
}
