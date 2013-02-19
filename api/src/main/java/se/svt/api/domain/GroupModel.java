package se.svt.api.domain;

import java.util.List;

/**
 * TODO write comment
 */
public class GroupModel {
	private List<AreaModel> areas;

	public void setAreas(List<AreaModel> areas) {
		this.areas = areas;
	}

	public List<AreaModel> getAreas() {
		return areas;
	}
}
