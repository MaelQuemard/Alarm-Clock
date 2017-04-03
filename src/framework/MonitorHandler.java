package framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import extension.AnnotationPlugin;

/** implémentation de {@link InvocationHandler}, permettant de monitorer un objet.
 * @author PO Gillier - K Gomes - E Jain - E Ngamije - M Quémard - S Vuylsteke 
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
		// Active le monitor
		if(m.getName().equals("turnMonitor"))
		{
			monitor = (IMonitor) args[0];
			return null;
		}
		
		if(m.getName().equals("activateMonitor"))
		{
			active = !active;
			return null;
		}
		
		if (active && (m.getName().equals("getAttributsPlugin") || m.getName().equals("getAttribut") || m.getName().equals("modifyAttribut") || m.getName().equals("kill")))
		{
			if (m.getName().equals("getAttributsPlugin")) {
				System.out.println("MonitorHandler::invoke");
				Map<String, Class<?>> listAttribut = new HashMap<String, Class<?>>();
				for(Method met : target.getClass().getMethods())
				{
					if(met.getName().contains("set")) {
						AnnotationPlugin an = met.getDeclaredAnnotation(AnnotationPlugin.class);
						if (an != null && an.value()) {
								listAttribut.put(met.getName().substring(3), met.getParameterTypes()[0]);
						}			
					}
				}
				return listAttribut;
			}
			
			if (m.getName().equals("getAttribut")) {
				System.out.println("MonitorHandler::invoke::getAttribut:: " + args[0]);
				for(Method met : target.getClass().getMethods())
				{
					if(met.getName().contains("get"+args[0])) return met.invoke(target);
				}
				return null;
			}
			
			// Modifie dynamiquement les plugins
			if (m.getName().equals("modifyAttribut")) {
				System.out.println("MonitorHandler::invoke    args : "+args.toString()+"\n");
				
				for(Method met : target.getClass().getMethods())
				{
					if(met.getName().contains("set"+args[0])) met.invoke(target, args[1]);
				}
				return null;
				
			}
			// Kill un plugin s'il est killable (renseigné dans le fichier de config)
			if (m.getName().equals("kill")) {
				try {
					
					// Recupère les plugins
					List<DescriptionPlugin> dp = ExtensionLoader.getInstance().getListPlugins();
					for (DescriptionPlugin d : dp) {
						System.out.println("MonitorHandler::invoke::kill : args[0]" + args[0].toString() + " descriptionPlugin::getNom " + d.getNom());
						
						// Si la description est la même que ce qui est passé en parametre (nom du plugin)
						if (d.getNom().equals(target.getClass().getMethod("get"+args[0]).invoke(target).getClass().getName()) && d.isKillable()) {
							
							// Recupere la methode pour kill le plugin
							for(Method met : target.getClass().getMethods())
							{
								if(met.getName().contains("set"+args[0])){
									System.out.println("MonitorHandler::invoke      kill : "+args[0].toString());
									args[0]=null;
									met.invoke(target, args[0]);
									return null;
								}
							}
						} else if (target.getClass().getMethod("get"+args[0]).getReturnType() == List.class) { // Si le type de retour est une liste
							
							// Parcours de cette liste pour verifier que le plugin de cette liste est bien celui passé en parametre
							for (Object o : ((List<Object>) target.getClass().getMethod("get"+args[0]).invoke(target))) {
								System.out.println("MonitorHandler::invoke      kill : "+args[0].toString() + "args[1] : "+args[1]);
								if (o.getClass().getMethod("getName").invoke(o).equals(args[1]) && d.getNom().equals(o.getClass().getName()) && d.isKillable()) {
									
									// Recupere la methode pour qu'il le plugin
									for(Method met : target.getClass().getMethods())
									{
										if (met.getName().contains("remove" + args[0])) {
											met.invoke(target, o);
										}
									}
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
		
		} else if (!active && (m.getName().equals("getAttributsPlugin") || m.getName().equals("getAttribut") || m.getName().equals("modifyAttribut") || m.getName().equals("kill"))) {
			return null;
		}
		
		return m.invoke(target, args);
	}
}
