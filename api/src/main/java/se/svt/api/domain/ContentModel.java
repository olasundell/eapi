package se.svt.api.domain;

import neo.xredsys.api.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* TODO write comment
*/
public class ContentModel {
	private int id;
	private String typeName;
	private SectionListItem homeSection;
	List<SectionListItem> sections = new ArrayList<SectionListItem>();
	private Map<String, Object> fields;

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

	public void setSections(List<SectionListItem> sections) {
		this.sections.clear();
		this.sections.addAll(sections);
	}

	public void setHomeSection(SectionListItem homeSection) {
		this.homeSection = homeSection;
	}

	public SectionListItem getHomeSection() {
		return homeSection;
	}
}
