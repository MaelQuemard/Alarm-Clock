package framework;

import client.Time;

public interface ITimeManager {
	public void IAmNotify(int i);
	public void addModifier(IModify m);
	public void  addModifiers();
	public void updateAff();
	public void setAffichage(IDisplayer a);
	public Time getTime();
	public void setTime(Time t);
	public int getModifyValue();
	public void setModifyValue(int modifyValue);
}
