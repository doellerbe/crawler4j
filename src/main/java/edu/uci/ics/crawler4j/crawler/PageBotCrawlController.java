package edu.uci.ics.crawler4j.crawler;

import java.util.Set;

import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.AdvertiserSeedStore;

/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */
public class PageBotCrawlController {

	public static void main(String[] args) throws Exception {
		//		if (args.length != 2) {
		//			System.out.println("Needed parameters: ");
		//			System.out.println("\t rootFolder (it will contain intermediate crawl data)");
		//			System.out.println("\t numberOfCralwers (number of concurrent threads)");
		//			return;
		//		}

		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		String crawlStorageFolder = "home/dorian/pagebot_data";
		
		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		AdvertiserSeedStore advertiserSeedStore = new AdvertiserSeedStore();
		Set<String> advSeedSet = advertiserSeedStore.getAdvertiserSeedStore();
		
		int seedListSize = advSeedSet.size();
		
		int numberOfCrawlers = seedListSize - 2;
		
		System.out.println("Number of Crawlers : " + numberOfCrawlers);

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);
		
		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(3);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		config.setMaxPagesToFetch(1000);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 * 
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		

//		controller.addSeed("http://www.ics.uci.edu/");
//		controller.addSeed("http://www.ics.uci.edu/~lopes/");
//		controller.addSeed("http://www.ics.uci.edu/~welling/");
		
		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
//		 */
//		for(String seed : advSeedSet) {
//			if(!seed.startsWith("http://")) {
//				seed = "http://" + seed;
//			}
//			System.out.println("adding seed : " + seed);
//			controller.addSeed(seed);
//		}
		
		controller.addSeed("http://www.americangreetings.com/");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(PageBotCrawler.class, numberOfCrawlers);
	}
}
