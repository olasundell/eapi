package se.svt.api.factory;

import neo.xredsys.api.Article;
import neo.xredsys.content.type.ArticleType;
import neo.xredsys.content.type.Field;
import se.svt.api.domain.ContentModel;

import java.util.HashMap;
import java.util.Map;

public class ContentModelFactory {
	public ContentModel createContentModel(Article article) {
		ContentModel contentModel = new ContentModel();

		Map<String, Object> map = createMapOfFields(article);

		contentModel.setFields(map);
		contentModel.setId(article.getId());
		contentModel.setTypeName(article.getArticleType().getLabel());
		contentModel.setHomeSection(article.getHomeSection().getId());
		contentModel.setSections(article.getSections());
		return contentModel;
	}

	public Map<String, Object> createMapOfFields(Article article) {
		Map<String, Object> map = new HashMap<String, Object>();

		ArticleType articleType = article.getArticleType();
		Field[] fields = articleType.getFields();

		for (Field f : fields) {
			map.put(f.getName(), article.getFieldValue(f.getName()));
		}
		return map;
	}
}