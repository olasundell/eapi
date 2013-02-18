package se.svt.api;

import org.junit.Assert;
import org.junit.Test;
import se.svt.test.stubs.StubArticleBuilder;

public class ItemToXmlTest {
	@Test
	public void shouldProduceXml() {
		String xml = new ItemToXml().convert(new StubArticleBuilder().build());

		Assert.assertNotNull(xml);
	}
}
