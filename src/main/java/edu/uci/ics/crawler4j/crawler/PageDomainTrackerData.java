package edu.uci.ics.crawler4j.crawler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import edu.uci.ics.crawler4j.url.WebURL;

public class PageDomainTrackerData {

	private String url;
	private String tracker;

	public Map<WebURL, Set<String>> pageDomainTrackerMap;

	public PageDomainTrackerData(){
		pageDomainTrackerMap = new ConcurrentHashMap<WebURL, Set<String>>();

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTracker() {
		return tracker;
	}

	public void setTracker(String tracker) {
		this.tracker = tracker;
	}

}
