package se.svt.api.domain;

public class SectionListItem {
	private final String name;
	private final String url;
	private final String contentUrl;

	public SectionListItem(String name, String url, String contentUrl) {
		this.name = name;
		this.url = url;
		this.contentUrl = contentUrl;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getContentUrl() {
		return contentUrl;
	}
}
