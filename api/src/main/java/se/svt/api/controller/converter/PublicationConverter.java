package se.svt.api.controller.converter;

import neo.xredsys.api.NoSuchObjectException;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.PersistentStoreException;
import neo.xredsys.api.Publication;
import org.springframework.core.convert.converter.Converter;

public class PublicationConverter implements Converter<String, Publication> {
	private ObjectLoader objectLoader;

	public PublicationConverter(ObjectLoader objectLoader) {
		this.objectLoader = objectLoader;
	}

	@Override
	public Publication convert(String identifier) {
		try{
			return objectLoader.getPublication(identifier);
		} catch(PersistentStoreException e) {
			throw new IllegalArgumentException("Could not find publication.");
		} catch(NoSuchObjectException e) {
			throw new IllegalArgumentException("Could not find publication.");
		}
	}
}
