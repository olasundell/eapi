package se.svt.api.factory;

import neo.xredsys.api.Article;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Section;
import neo.xredsys.content.type.ArticleType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.svt.api.domain.ContentModel;
import se.svt.test.stubs.StubArticleBuilder;
import se.svt.test.stubs.StubArticleTypeBuilder;
import se.svt.test.stubs.StubObjectLoaderBuilder;
import se.svt.test.stubs.StubSectionBuilder;

import java.util.Map;

public class ContentModelFactoryTest {
	private static final int ARTICLE_ID = 1;
	private ContentModelFactory contentModelFactory = new ContentModelFactory();
	protected Article article;

	@Before
	public void stubStuff() {
		final ArticleType articleType = new StubArticleTypeBuilder().build();
		final Section homeSection = new StubSectionBuilder().build();
		article = new StubArticleBuilder()
				.setId(ARTICLE_ID)
				.setHomeSection(homeSection)
				.setArticleType(articleType)
				.build();
	}


	@Test
	public void shouldCreateModelWithFields() {
		ContentModel model = contentModelFactory.createContentModel(article);

		Map<String, Object> fields = model.getFields();
		Assert.assertNotNull(fields);
	}
}
