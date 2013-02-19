package se.svt.api.controller;

import neo.xredsys.api.Article;
import neo.xredsys.api.ObjectLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.domain.ContentModel;
import se.svt.api.factory.ContentModelFactory;

import java.util.Map;

/**
 * TODO write comment
 */
@Controller
public class ContentController {
	private final ContentModelFactory contentModelFactory = new ContentModelFactory();
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

		ContentModel contentModel = contentModelFactory.createContentModel("", article);

		return new ModelAndView("", "model", contentModel);
	}

	public void setObjectLoader(ObjectLoader objectLoader) {
		this.objectLoader = objectLoader;
	}

}
