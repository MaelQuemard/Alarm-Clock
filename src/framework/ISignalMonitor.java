package framework;

import java.util.List;

public interface ISignalMonitor {
	public void turnMonitor(IMonitor m);
	public List<String> getAttributsPlugin();
	public void modifyAttribut(String attibut, Object newAttribut);
	public void kill(String plugin);
}
