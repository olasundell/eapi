package se.svt.api.factory;

import com.escenic.domain.Property;
import com.escenic.domain.index.Area;
import com.escenic.domain.index.Element;
import com.escenic.domain.index.Group;
import neo.xredsys.api.IndexPage;
import neo.xredsys.api.ListPool;
import neo.xredsys.api.Section;
import se.svt.api.controller.SectionController;
import se.svt.api.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SectionModelFactory {
	private final SectionController sectionController;

	public SectionModelFactory(SectionController sectionController) {
		this.sectionController = sectionController;
	}

	public SectionModel createSectionModel(String publicationName, Section section) {
		SectionModel model = new SectionModel();

		model.setId(section.getId());
		model.setName(section.getName());

		List<SectionListItem> subSectionList = createSubSectionList(publicationName, section.getSubSections());
		model.setSubSections(subSectionList);

		List<ListModel> lists = createListsList(section);
		model.setLists(lists);

		GroupModel groupModel = createGroupModel(section);
		model.setGroup(groupModel);

		return model;
	}

	private GroupModel createGroupModel(Section section) {
		GroupModel groupModel = new GroupModel();

		IndexPage indexPage = section.getActiveIndexPage();
		Group group = indexPage.getElements();

		List<AreaModel> areas = new ArrayList<AreaModel>();

		for (Area area : group.getAreas()) {
			final AreaModel areaModel = new AreaModel();

			areaModel.setElements(area.getElements());

			areas.add(areaModel);
		}

		groupModel.setAreas(areas);

		return groupModel;
	}

	private List<ListModel> createListsList(Section section) {
		final Collection<ListPool> listPools = section.getLists();
		List<ListModel> lists = new ArrayList<ListModel>();

		for (ListPool l : listPools) {
			ListModel listModel = new ListModel();
			listModel.setId(l.getId());
			listModel.setName(l.getName());

			lists.add(listModel);
		}
		return lists;
	}

	private List<SectionListItem> createSubSectionList(String publicationName, Section[] subSections) {

		final SectionListItemFactory sectionListItemFactory = new SectionListItemFactory();
		List<SectionListItem> sectionListItems = new ArrayList<SectionListItem>(subSections.length);
		for (Section sectionItem : subSections) {
			sectionListItems.add(sectionListItemFactory.createSectionListItem(publicationName, sectionItem));
		}
		return sectionListItems;
	}
}