package se.svt.api;

import neo.xredsys.api.Article;
import neo.xredsys.content.type.ArticleType;
import org.junit.Assert;
import org.junit.Test;
import se.svt.test.stubs.StubArticleBuilder;

public class ItemToXmlTest {
	private static final String TITLE = "title";
	private static final int ID = 123;
	private static final String ARTICLE_TYPE = "news";

	@Test
	public void shouldProduceXml() {
		final Article article = new StubArticleBuilder()
				.setArticleType(ARTICLE_TYPE)
				.setId(ID)
				.setTitle(TITLE)
				.build();

		String xml = new ItemToXml().convert(article);

		Assert.assertNotNull(xml);
	}
}
