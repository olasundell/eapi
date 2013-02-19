package se.svt.api.controller;

import neo.xredsys.api.Article;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Section;
import neo.xredsys.content.type.ArticleType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import se.svt.test.stubs.StubArticleBuilder;
import se.svt.test.stubs.StubArticleTypeBuilder;
import se.svt.test.stubs.StubObjectLoaderBuilder;
import se.svt.test.stubs.StubSectionBuilder;

import java.util.Map;

/**
 * TODO write comment
 */
public class ContentControllerTest {
	private static final int ARTICLE_ID = 1;
	protected ContentController contentController;

	@Before
	public void setup() {
		contentController = new ContentController();
		final ArticleType articleType = new StubArticleTypeBuilder().build();
		final Section homeSection = new StubSectionBuilder().build();
		final Article article = new StubArticleBuilder()
				.setId(ARTICLE_ID)
				.setHomeSection(homeSection)
				.setArticleType(articleType)
				.build();

		final ObjectLoader objectLoader = new StubObjectLoaderBuilder().addArticle(article).build();

		contentController.setObjectLoader(objectLoader);
	}

	@Test
	public void shouldReturnAModelContainingAContentItem() {
		ModelAndView modelAndView = contentController.displayContent(ARTICLE_ID);

		Assert.assertNotNull(modelAndView);
		final Map<String,Object> model = modelAndView.getModel();
		Assert.assertNotNull(model);
		Assert.assertNotNull(model.get("model"));
	}
}
