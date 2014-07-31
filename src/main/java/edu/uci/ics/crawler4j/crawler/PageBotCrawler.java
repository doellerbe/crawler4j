/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.uci.ics.crawler4j.crawler;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.parser.DomainTrackerParser;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 * 
 * Extended for the purposes at Dstillery
 */
public class PageBotCrawler extends WebCrawler {
	
private Set<String> domainTrackerSet = new ConcurrentSkipListSet<String>();
	
	private PageDomainTrackerData pgDomainTrackerData;

//	private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
//			+ "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private static final Pattern ASSET_FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?))$");

	private static final Pattern TAG_FILTERS = Pattern.compile(".*(fls.doubleclick.net).*");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	//	@Override
	//	public boolean shouldVisit(WebURL url) {
	//		String href = url.getURL().toLowerCase();
	//		return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
	//	}

	/**
	 * PageBot needs to consider js, image assets, and tag containers. These are where most, 
	 * if not, all trackers should have a presence.
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		String hrefDomain = url.getDomain().toLowerCase();

		System.out.println("href : " + href);
		System.out.println("hrefDomain : " + hrefDomain);

		boolean fileExtFilter = ASSET_FILTERS.matcher(href).matches();
		boolean tagManagerFilter = TAG_FILTERS.matcher(href).matches();

		return !fileExtFilter && tagManagerFilter;
	}


	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		String domain = page.getWebURL().getDomain();
		String path = page.getWebURL().getPath();
		String subDomain = page.getWebURL().getSubDomain();
		String parentUrl = page.getWebURL().getParentUrl();

		System.out.println("Docid: " + docid);
		System.out.println("URL: " + url);
		System.out.println("Domain: '" + domain + "'");
		System.out.println("Sub-domain: '" + subDomain + "'");
		System.out.println("Path: '" + path + "'");
		System.out.println("Parent page: " + parentUrl);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());

			for(WebURL webUrl : links) {
				String urlString = webUrl.getURL().toString();

				try {
					if(urlString != null && DomainTrackerParser.containsTracker(urlString)) {
						System.out.println("added a tracker to the set : " + urlString);

						if(true){

						}
						domainTrackerSet.add(urlString);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("WebUrl to be crawled : " + webUrl);
			}
		}

		System.out.println("=============");
	}
}
