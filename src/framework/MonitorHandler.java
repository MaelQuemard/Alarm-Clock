package framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MonitorHandler implements InvocationHandler {

	private Object target;
	private boolean active;
	private IMonitor monitor;
	
	
	public MonitorHandler(Object o) {
		this.target = o;
		this.active = false;
		monitor = null;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		if(m.getName().equals("turnMonitor"))
		{
			active = !active;
			monitor = (IMonitor) args[0];
			return null;
		}
		if (m.getName().equals("getAttributsPlugin")) {
			System.out.println("MonitorHandler::invoke");
			List<String> listAttribut = new ArrayList<String>();
			for(Method met : target.getClass().getMethods())
			{
				if(met.getName().contains("set")) 
				listAttribut.add(met.getName().substring(3));
				
			}
			return listAttribut;
		}
		if (m.getName().equals("modifyAttribut")) {
			System.out.println("MonitorHandler::invoke    args : "+args.toString()+"\n");
			
			for(Method met : target.getClass().getMethods())
			{
				if(met.getName().contains("set"+args[0])) met.invoke(target, args[1]);
			}
			return null;
			
		}
		if (m.getName().equals("kill")) {
			for(Method met : target.getClass().getMethods())
			{
				if(met.getName().contains("set"+args[0])){
					System.out.println("MonitorHandler::invoke      kill"+args[0].toString());
					args[0]=null;
					met.invoke(target, args[0]);
				}
			}
			return null;
		}
		if(m.getName().contains("run") && active)
		{	
			
			/*monitor.writeLog("L'attribut suivant: "+
					m.getName().substring(3)+
					"\n		par la valeur suivante:"
					+ args.toString()
					);*/
		}
		
		return m.invoke(target, args);
	}

}
