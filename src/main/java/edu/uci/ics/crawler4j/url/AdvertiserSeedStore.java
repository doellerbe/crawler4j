package edu.uci.ics.crawler4j.url;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public final class AdvertiserSeedStore {
	private final Set<String> advertiserSeedStoreSet;

	public AdvertiserSeedStore(){
		advertiserSeedStoreSet = new LinkedHashSet<String>();

		String fileName = "/home/dorian/dstillery-workspace/PageBotV2/src/main/resources/advertiser-urls.txt";
		File file = new File(fileName);
		
		try {
			final List<String> advSeedList = FileUtils.readLines(file);
			
			for(final String advSeed : advSeedList){
				advertiserSeedStoreSet.add(advSeed);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public Set<String> getAdvertiserSeedStore(){
		return this.advertiserSeedStoreSet;
	}
}
