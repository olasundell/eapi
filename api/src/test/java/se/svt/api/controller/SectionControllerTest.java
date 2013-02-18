package se.svt.api.controller;

import neo.xredsys.api.ObjectLoader;
import neo.xredsys.api.Publication;
import neo.xredsys.api.Section;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import se.svt.api.domain.SectionListItem;
import se.svt.test.stubs.StubObjectLoaderBuilder;
import se.svt.test.stubs.StubPublicationBuilder;
import se.svt.test.stubs.StubSectionBuilder;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class SectionControllerTest {

	@Test
	public void shouldReturnAModelContainingAllSubSections() throws Exception {
		SectionController sectionController = new SectionController();

		Publication publication = new StubPublicationBuilder().setId(1).build();
		int publicationId = publication.getId();

		Section subsection = new StubSectionBuilder().setName("subsection").setId(2).build();
		Section section = new StubSectionBuilder().setName("section").setId(1).addSubSection(subsection).build();

		ObjectLoader objectLoader = new StubObjectLoaderBuilder().setPublicationId(publicationId).addSection(section).addSection(subsection).build();
		sectionController.setObjectLoader(objectLoader);

		ModelAndView modelAndView = sectionController.displaySections(1);
		List<SectionListItem> sections = (List<SectionListItem>) modelAndView.getModel().get("sections");
		assertEquals(1, sections.size());
		SectionListItem sectionListItem = sections.get(0);
		assertEquals(subsection.getName(), sectionListItem.getName());
	}
}
