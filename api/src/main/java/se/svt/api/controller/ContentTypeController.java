package se.svt.api.controller;

import neo.xredsys.api.IOAPI;
import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Publication;
import neo.xredsys.api.util.ArticleIterator;
import neo.xredsys.content.type.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.domain.ContentModel;
import se.svt.api.domain.ContentTypeModel;
import se.svt.api.factory.ContentModelFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO write comment
 */
@Controller
public class ContentTypeController {
	@Autowired
	private ObjectLoader objectLoader;

	@RequestMapping(value = "/contenttype/", method = RequestMethod.GET)
	public ModelAndView listPublications() {
		return new ModelAndView();
	}
	@RequestMapping(value = "/contenttype/{publicationName}/", method = RequestMethod.GET)
	public ModelAndView listContentTypes(@PathVariable String publicationName) {
		List<ContentTypeModel> contentTypes = new ArrayList<ContentTypeModel>();

		Collection<ArticleType> types = objectLoader.getPublication(publicationName).getArticleTypes();

		for (ArticleType type: types) {
			final ContentTypeModel contentTypeModel = new ContentTypeModel();
			contentTypeModel.setName(type.getName());
			contentTypeModel.setLabel(type.getLabel());
			contentTypeModel.setUrl(String.format("http://www.svt.se/svtapi/contenttype/%s/%s/?format=xml",
					publicationName,
					type.getName()));
			contentTypes.add(contentTypeModel);
		}

		return new ModelAndView("", "model", contentTypes);
	}

	@RequestMapping(value = "/contenttype/{publicationName}/{contentType}/", method = RequestMethod.GET)
	public ModelAndView listBasedOnContentType(@PathVariable String publicationName,
											   @PathVariable String contentType) {
		final Publication publication = objectLoader.getPublication(publicationName);
		ArticleIterator iterator = objectLoader.getArticlesByType(contentType, publication.getId());
		List<ContentModel> list = new ArrayList<ContentModel>();
		final ContentModelFactory factory = new ContentModelFactory();

		int i = 0;

		while (iterator.hasNext()) {
			list.add(factory.createContentModel(publicationName, iterator.nextArticle()));

			if (i++ > 50) {
				break;
			}
		}

		return new ModelAndView("", "model", list);
	}
}
