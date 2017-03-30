package framework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import client.IApp;

/** implémentation de {@link InvocationHandler}, permettant de monitorer un objet.
 * @author 
 *
 */
public class MonitorHandler implements InvocationHandler {

	private Object target;
	private boolean active;
	private IMonitor monitor;
	
	
	public MonitorHandler(Object o) {
		this.target = o;
		this.active = false;
		monitor = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
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
			try {
				List<DescriptionPlugin> dp = ExtensionLoader.getInstance().getListPlugins();
				for (DescriptionPlugin d : dp) {
					if (d.getNom().equals(target.getClass().getMethod("get"+args[0]).invoke(target).getClass().getName()) && d.isKillable()) {
						for(Method met : target.getClass().getMethods())
						{
							if(met.getName().contains("set"+args[0])){
								System.out.println("MonitorHandler::invoke      kill : "+args[0].toString());
								args[0]=null;
								met.invoke(target, args[0]);
								return null;
							}
						}
					}
				}
			}
			catch (Exception e) {
				return null;
			}
			return null;
		}
		
		if(m.getName().contains("run") && active)
		{	
			// TODO 
			Class<?> cl = target.getClass();
			System.out.println(cl.getFields().toString());
		}
		
		return m.invoke(target, args);
	}

}
