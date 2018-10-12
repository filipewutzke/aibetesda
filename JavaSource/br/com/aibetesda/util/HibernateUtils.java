package br.com.aibetesda.util;

import java.util.List;
import org.hibernate.Hibernate;

public class HibernateUtils {

	public static void initList(List<?> lst){
		Hibernate.initialize(lst);
	}
	public static void initList(Object obj){
		Hibernate.initialize(obj);
	}
}
