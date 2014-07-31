package edu.uci.ics.crawler4j.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author dorian
 *
 * Utility to read from the domain-trackers.txt file and data store for crawled data.
 * ConcurrentHashMap of ensures that all reads and writes are thread-safe.
 */
public class DomainTrackerParser {

	private static final File file = new File("/home/dorian/dstillery-workspace/PageBotV2/src/main/resources/domain-trackers.txt");
	private static final String ENCODING = "UTF-8";

	private static List<String> domainTrackerList;
	private static Set<String> trackerList = new HashSet<String>();
	
	static {
		try {
			domainTrackerList = FileUtils.readLines(file, ENCODING);
		}catch(IOException io) {
			io.printStackTrace();
			throw new RuntimeException();
		}
	}

//	public static boolean isTracker(String resourceText) throws IOException {
//		for(String tracker : domainTrackerList) {
//			if(resourceText.contains(tracker)) {
//				return true;
//			}
//		}
//		return false;
//	}

	public static boolean containsTracker(String resourceText) throws IOException {
		if(!trackerList.isEmpty() || trackerList == null){
			trackerList.clear();
		}

		for(String tracker : domainTrackerList){
			if(resourceText.contains(tracker)) {
				trackerList.add(tracker);
				System.out.println("Added : " + tracker);
			}
		}
		if(trackerList.size() > 0) {
			return true;
		}
		return false;
	}

	public static Set<String> getDomainTrackers(){ //change back to public after testing
		return trackerList;
	}

	public static void main(String[] args) throws IOException {
		String jsResource = "http://dsa.csdata1.com/ http://agkn";
		//		String jsResource2 = "dsa.csdata1.com";

		boolean hasTracker1 = DomainTrackerParser.containsTracker(jsResource);
		//		boolean hasTracker2 = DomainTrackerParser.containsTracker(jsResource2);

		System.out.println(hasTracker1);
		//		System.out.println(getDomainTrackers());
	}
}
