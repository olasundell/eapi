package se.svt.api.domain;

import com.escenic.domain.Property;
import com.escenic.domain.index.Element;

import java.util.List;

/**
 * TODO write comment
 */
public class AreaModel {
	private List<Property<Element>> elements;

	public void setElements(List<Property<Element>> elements) {
		this.elements = elements;
	}

	public List<Property<Element>> getElements() {
		return elements;
	}
}
