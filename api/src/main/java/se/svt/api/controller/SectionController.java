package se.svt.api.controller;

import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.domain.SectionListItem;

import java.util.ArrayList;

@Controller
public class SectionController {
	@Autowired
	private ObjectLoader objectLoader;


	@RequestMapping(value = "/sections/", method = RequestMethod.GET)
	public final ModelAndView displayRootSections() {
		return displaySections(1);
	}

	@RequestMapping(value = "/sections/{sectionId}", method = RequestMethod.GET)
	public final ModelAndView displaySections(@PathVariable int sectionId) {
		Section section = objectLoader.getSection(sectionId);
		Section[] subSections = section.getSubSections();
		ArrayList<SectionListItem> sectionListItems = new ArrayList<SectionListItem>(subSections.length);
		for(Section sectionItem : subSections) {
			sectionListItems.add(new SectionListItem(sectionItem.getName()));
		}

		return new ModelAndView("", "model", sectionListItems);
	}

	public void setObjectLoader(ObjectLoader objectLoader) {
		this.objectLoader = objectLoader;
	}
}
