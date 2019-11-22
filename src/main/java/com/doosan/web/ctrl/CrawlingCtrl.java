package com.doosan.web.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doosan.web.pxy.Box;
import com.doosan.web.pxy.CrawlingProxy;
import com.doosan.web.pxy.PageProxy;

@RestController
@RequestMapping("/crawls")
public class CrawlingCtrl {
	@Autowired CrawlingProxy crawler;
	@Autowired PageProxy pager;
	@Autowired Box<Object> box;
	
	@GetMapping("/naver")
	public ArrayList<HashMap<String, String>>naver(){
		return crawler.engCrawl("https://endic.naver.com/?sLn=kr");
	}
	@GetMapping("/cgv")
	public ArrayList<HashMap<String, String>>cgv() {
		return crawler.cgvCrawling();
	}
	@GetMapping("/bugs/page/{page}")
	public Map<?,?> bugs(@PathVariable String page){
		ArrayList<HashMap<String, String>> list = crawler.bugsCrawling();
		pager.setRowCount(list.size());
		pager.setPageSize(10);
		pager.setBlockSize(5);
		pager.setCurrPage(pager.integer(page));
		pager.paging();
		ArrayList<HashMap<String, String>> temp = new ArrayList<>();
		
		for(int i = 0; i<list.size(); i++) {
			if(i >=pager.getStartRow() && i <= pager.getEndRow()) {
				temp.add(list.get(i));	
			}
		if(i > pager.getEndRow()) {
			break;
			}
		}
		box.put("pager", pager);
		box.put("list", temp);
		return box.get();
	}
}
