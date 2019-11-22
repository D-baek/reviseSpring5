package com.doosan.web.pxy;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component("/crawls")
@Lazy
public class CrawlingProxy extends Proxy{
	@Autowired Inventory<HashMap<String, String>> inventory;
	@Autowired Box<String> box;
	
	public ArrayList<HashMap<String, String>> engCrawl(String url){
		url = "https://endic.naver.com/?sLn=kr";
		inventory.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			Elements txtOrigin = rawData.select("div[class=\"txt_origin\"] a");
			Elements txtTrans = rawData.select("div[class=\"txt_trans\"]");
			HashMap<String, String> map = null;
			for(int i = 0; i<txtOrigin.size(); i++) {
				map = new HashMap<>();
				map.put("origin", txtOrigin.get(i).text());
				map.put("trans", txtTrans.get(i).text());
				inventory.add(map);
			}
			System.out.println("inven에 담긴 값: " +inventory);
		}catch(Exception e){
			e.printStackTrace();
		}
		return inventory.get();
	}

	public ArrayList<HashMap<String, String>> cgvCrawling() {
		final String USER_AGENT = "\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		String cgv = "http://www.cgv.co.kr/movies/?lt=3";
		inventory.clear();
		try {
			Connection.Response homePage = Jsoup.connect(cgv)
				.method(Connection.Method.GET)
				.userAgent(USER_AGENT)
				.execute();
			Document temp = homePage.parse();
			Document rawData = Jsoup.connect(cgv).timeout(10*1000).get();
			Elements element = temp.select("div.sect-movie-chart");
			Elements title = element.select("strong.title");
			Elements percent = element.select("strong.percent");
			Elements textInfo= element.select("span.txt-info");
			Elements photo = temp.select("span.thumb-image");
			
			HashMap<String, String> map = null;
			for(int i = 0; i<title.size(); i++) {
				map = new HashMap<>();
				map.put("title", title.get(i).text());
				map.put("percent", percent.get(i).toString());
				map.put("textInfo", textInfo.get(i).text());
				map.put("photo", photo.get(i).select("img").attr("src"));
				inventory.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("========cgv 크롤링==========");
		inventory.get().forEach(System.out::println);
		return inventory.get();
	}

	public ArrayList<HashMap<String, String>> bugsCrawling() {
		inventory.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String bugsUrl = "https://music.bugs.co.kr/chart";
			Connection.Response homePage = Jsoup.connect(bugsUrl)
				.method(Connection.Method.GET)
				.userAgent(USER_AGENT)
				.execute();
			Document temp = homePage.parse();
			Elements tempforTitle = temp.select("p.title");
			Elements tempforContent = temp.select("p.artist");
			Elements tempforphoto = temp.select("a.thumbnail");
			HashMap<String, String>map=null;
			
			for (int i=0; i<tempforTitle.size(); i++) {
				map = new HashMap<>();
				map.clear();
				map.put("seq", string(i+1));
				map.put("title", tempforTitle.get(i).text());
				map.put("artist", tempforContent.get(i).text());
				map.put("thumbnail", tempforphoto.get(i).select("img").attr("src"));
				inventory.add(map);
			}
		}catch(Exception e) {
		}
		System.out.println("====================크롤링 결과===================");
		inventory.get().forEach(System.out :: println);
		return inventory.get();
	}

}






