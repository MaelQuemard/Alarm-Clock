package framework;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;

import client.IModify;

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
		
	public static boolean isDuckSubTypeOf(Class<?> pSuper, Class<?> pSub)
	{
		if(pSuper.isAssignableFrom(pSub)) return true;
		for (Method mSuper : pSuper.getMethods())
		{
			try {
				Method mSub = pSub.getMethod(mSuper.getName(),mSuper.getParameterTypes());
				if (mSub == null || !mSuper.getReturnType().isAssignableFrom(mSub.getReturnType())) return false;
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static Object loadExtension(String filename, Class<?> supercl)
	{
		java.util.Properties p = new Properties();
		
		try 
		{
			p.load(new FileReader(filename));
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		Class<?> cl = null;
		try 
		{
			cl = Class.forName((String)p.get("class"));
			if  (!isDuckSubTypeOf(supercl,cl)) return null;
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		Object res = null;
		try 
		{
			res = cl.newInstance();
			// Revoir le if
			if (supercl == IModify.class) {
				((IModify) res).setName((String)p.get("Name"));
			}
		} catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		return res;
	}
	
}