package se.svt.api.factory;

import neo.xredsys.api.Article;
import neo.xredsys.api.Section;
import neo.xredsys.content.type.ArticleType;
import neo.xredsys.content.type.Field;
import se.svt.api.domain.ContentModel;
import se.svt.api.domain.SectionListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentModelFactory {
	public ContentModel createContentModel(String publicationName, Article article) {
		ContentModel contentModel = new ContentModel();

		Map<String, Object> map = createMapOfFields(article);

		contentModel.setFields(map);
		contentModel.setId(article.getId());
		contentModel.setTypeName(article.getArticleType().getLabel());

		final SectionListItemFactory sectionListItemFactory = new SectionListItemFactory();
		final SectionListItem sectionListItem = sectionListItemFactory.createSectionListItem(publicationName, article.getHomeSection());

		contentModel.setHomeSection(sectionListItem);

		List<SectionListItem> sectionListItems = new ArrayList<SectionListItem>();
		for (Section s: article.getSections()) {
			sectionListItems.add(sectionListItemFactory.createSectionListItem(publicationName, s));
		}
		contentModel.setSections(sectionListItems);
		return contentModel;
	}

	protected Map<String, Object> createMapOfFields(Article article) {
		Map<String, Object> map = new HashMap<String, Object>();

		ArticleType articleType = article.getArticleType();
		Field[] fields = articleType.getFields();

		for (Field f : fields) {
			map.put(f.getName(), article.getFieldValue(f.getName()));
		}
		return map;
	}
}