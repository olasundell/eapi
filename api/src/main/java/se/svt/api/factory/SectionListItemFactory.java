package se.svt.api.factory;

import neo.xredsys.api.Section;
import se.svt.api.controller.SectionController;
import se.svt.api.domain.SectionListItem;

public class SectionListItemFactory {
	public SectionListItem createSectionListItem(String publicationName, Section sectionItem) {
		return new SectionListItem(sectionItem.getName(),
				String.format(SectionController.SECTIONS_URL_PATTERN, publicationName, sectionItem.getId()),
				String.format(SectionController.CONTENT_URL_PATTERN, publicationName, sectionItem.getId()));
	}
}