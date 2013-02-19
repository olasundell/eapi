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
	private int homeSection;
	List<Integer> sections = new ArrayList<Integer>();
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
