package client;


public interface ITimeManager {
	public void IAmNotify(int i);
	public void addModifier(IModify m);
	public void  addModifiers();
	public void updateAff();
	public void setAffichage(IDisplayer a);
	public ITime getTime();
	public void setTime(ITime t);
	public int getModifyValue();
	public void setModifyValue(int modifyValue);
	public int getRefresh();
	
	/**
	 * Cette methode permet de suprimer un modifieur
	 * @param im, modify a supprimer
	 */
	public void removeModifier(IModify im);
}
