package framework;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;

public class Plateform 
{
	
	Object loadBean(String filename,String interfaceName)
	{
		java.util.Properties p = new Properties();
		try {
			p.load(new FileReader(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Class<?> cl = null;
		try {
			cl = Class.forName((String) p.get(interfaceName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object o = null;
		try {
			o = cl.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO : ajouter remplissage des attributs
		
		return o;
		
	}
	
}