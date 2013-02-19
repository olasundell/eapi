package se.svt.api.controller;

import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Publication;
import neo.xredsys.api.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.controller.converter.PublicationConverter;
import se.svt.api.domain.SectionListItem;
import se.svt.api.domain.SectionModel;
import se.svt.api.factory.SectionListItemFactory;
import se.svt.api.factory.SectionModelFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SectionController {
	public static final String SECTIONS_URL_PATTERN = "http://svt.se/svtapi/%s/sections/%d";
	public static final String CONTENT_URL_PATTERN = "http://svt.se/svtapi/%s/content/%d";
	private final SectionListItemFactory sectionListItemFactory = new SectionListItemFactory();
	private final SectionModelFactory sectionModelFactory = new SectionModelFactory(this);
	@Autowired
	private ObjectLoader objectLoader;

	@RequestMapping(value = "/sections/", method = RequestMethod.GET)
	public final ModelAndView displayRootSections() {
		Publication[] publications = objectLoader.getPublications();
		List<SectionListItem> sectionListItems = new ArrayList<SectionListItem>(publications.length);
		for(Publication publication : publications) {
			Section rootSection = publication.getRootSection();
			sectionListItems.add(sectionListItemFactory.createSectionListItem(publication.getName(), rootSection));
		}
		return new ModelAndView("", "model", sectionListItems);
	}

	@RequestMapping(value = "/{publication}/sections/{sectionId}", method = RequestMethod.GET)
	public final ModelAndView displaySections(@PathVariable Publication publication,
											  @PathVariable int sectionId) {
		final String publicationName = publication.getName();
		SectionModel model = sectionModelFactory.createSectionModel(publicationName, objectLoader.getSection(sectionId));

		return new ModelAndView("", "model", model);
	}

	public void setObjectLoader(ObjectLoader objectLoader) {
		this.objectLoader = objectLoader;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
		((FormattingConversionService) conversionService).addConverter(new PublicationConverter(objectLoader));
	}
}
