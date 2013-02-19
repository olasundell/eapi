package se.svt.api.domain;

/**
 * TODO write comment
 */
public class ContentTypeModel {
	private String name;
	private String label;
	private String url;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
