package se.svt.api.domain;

import java.util.List;

/**
 * TODO write comment
 */
public class SectionModel {
	private int id;
	private String name;
	private List<SectionListItem> subSections;
	private List<ListModel> lists;
	private List<AreaModel> areas;
	private GroupModel group;

	public void setSubSections(List<SectionListItem> subSections) {
		this.subSections = subSections;
	}

	public List<SectionListItem> getSubSections() {
		return subSections;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLists(List<ListModel> lists) {
		this.lists = lists;
	}

	public List<ListModel> getLists() {
		return lists;
	}

	public void setAreas(List<AreaModel> areas) {
		this.areas = areas;
	}

	public List<AreaModel> getAreas() {
		return areas;
	}

	public void setGroup(GroupModel group) {
		this.group = group;
	}

	public GroupModel getGroup() {
		return group;
	}
}
