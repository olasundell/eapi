package se.svt.api.controller;

import com.escenic.domain.ContentSummary;
import neo.xredsys.api.Article;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Section;
import neo.xredsys.content.type.ArticleType;
import neo.xredsys.content.type.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.domain.SectionListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO write comment
 */
@Controller
public class ContentController {
	@Autowired
	private ObjectLoader objectLoader;

	@RequestMapping(value = "/content/", method = RequestMethod.GET)
	public final ModelAndView displayItem() {
		// TODO should return a documentation view
		return displayContent(1);
	}

	@RequestMapping(value = "/content/{contentId}", method = RequestMethod.GET)
	public final ModelAndView displayContent(@PathVariable int contentId) {
		Article article = objectLoader.getArticle(contentId);
		// TODO add id, type, home section, published in sections and so on

		ContentModel contentModel = new ContentModel();

		Map<String, Object> map = createMapOfFields(article);

		contentModel.setFields(map);
		contentModel.setId(contentId);
		contentModel.setTypeName(article.getArticleType().getLabel());
		contentModel.setHomeSection(article.getHomeSection().getId());
		contentModel.setSections(article.getSections());

		return new ModelAndView("", "model", contentModel);
	}

	private Map<String, Object> createMapOfFields(Article article) {
		Map<String, Object> map = new HashMap<String, Object>();

		ArticleType articleType = article.getArticleType();
		Field[] fields = articleType.getFields();

		for (Field f: fields) {
			map.put(f.getName(), article.getFieldValue(f.getName()));
		}
		return map;
	}

	public void setObjectLoader(ObjectLoader objectLoader) {
		this.objectLoader = objectLoader;
	}

	public static class ContentModel {
		private Map<String, Object> fields;
		private int id;
		private String typeName;
		List<Integer> sections = new ArrayList<Integer>();
		private int homeSection;

		public void setFields(Map<String,Object> fields) {
			this.fields = fields;
		}

		public Map<String, Object> getFields() {
			return fields;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setSections(Section[] sections) {
			for (Section s: sections) {
				this.sections.add(s.getId());
			}
		}

		public void setHomeSection(int homeSection) {
			this.homeSection = homeSection;
		}

		public int getHomeSection() {
			return homeSection;
		}
	}
}
