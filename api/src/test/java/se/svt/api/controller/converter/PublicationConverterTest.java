package se.svt.api.controller.converter;

import neo.xredsys.api.NoSuchObjectException;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Publication;
import org.junit.Before;
import org.junit.Test;
import se.svt.test.stubs.StubObjectLoaderBuilder;
import se.svt.test.stubs.StubPublicationBuilder;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PublicationConverterTest {

	private Publication publication;
	private PublicationConverter publicationConverter;
	private ObjectLoader objectLoader;

	@Before
	public void setUp() throws Exception {
		publication = new StubPublicationBuilder().setName("publication").build();
		objectLoader = new StubObjectLoaderBuilder().addPublication(publication).build();
		publicationConverter = new PublicationConverter(objectLoader);

	}

	@Test
	public void shouldConvertNameToPublication() throws Exception {
		assertEquals(publication, publicationConverter.convert("publication"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfInvalidPublicationName() throws Exception {
		when(objectLoader.getPublication("invalidPublication")).thenThrow(new NoSuchObjectException());
		publicationConverter.convert("invalidPublication");
	}
}
