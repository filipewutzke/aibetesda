package br.com.aibetesda.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.collection.PersistentBag;

public class PersistenceUtils {

	/**
	 * Returns a Collection of all objects in the specified persistentCollection
	 * without binding to any persistence context or session.
	 *
	 * @param <T>
	 * @param targetCollection
	 * @param persistentCollection
	 * @return
	 */
	 public static <T> Collection<T> 
	 	removeCollectionItemsFromPersistenceContext(
	 			Collection<T> targetCollection, 
	 			Collection<T> persistentCollection) {
		// If runtime type of persistentCollection is not PersistentCollection,
		// take no action
		if (!(persistentCollection instanceof PersistentBag))
		{
			System.out.println("Not from desired type");
			return persistentCollection;
		}

		// Clear existing target
		targetCollection.clear();

		// Place all items in persistent collection into target
		for (T item : persistentCollection) {
			targetCollection.add(item);
		}

		// Return target
		return targetCollection;
	}
	 
	 public static <T> List<T> 
	 	removePersistenceContext(List<T> persistentCollection) {
		if (!(persistentCollection instanceof PersistentBag))
		{
			System.out.println("Not from desired type");
			return persistentCollection;
		}

		List<T> targetCollection = new ArrayList<T>();

		for (T item : persistentCollection) {
			targetCollection.add(item);
		}

		return targetCollection;
	}
}
