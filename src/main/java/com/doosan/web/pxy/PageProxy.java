package com.doosan.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("pager")
@Lazy @Data
public class PageProxy extends Proxy{
	@Autowired CrawlingProxy crawler;
	private int rowCount, startRow, endRow,
				      pageCount, pageSize, startPage, endPage, currPage,
				      blockCount, blockSize, prevBlock, nextBlock, currBlock
				      ;
	private boolean existPrev, existNext;

	public void paging() {
		//rowCount, pageSize, blockSize, currPage = 외부 주입 받을 값
		pageCount = (rowCount%pageSize != 0) ? rowCount/pageSize+1 : rowCount/pageSize;
		blockCount = (pageCount%blockSize != 0)? pageCount/blockSize+1 : pageCount/blockSize;
		startRow = currPage * pageSize;
		endRow = (currPage != (pageCount-1))? startRow + (pageSize -1) : rowCount -1;
		currBlock = currPage / blockSize;
		startPage = currBlock * blockSize;
		endPage = (currBlock != (blockCount-1)) ? startPage + (blockSize -1) : pageCount -1;
		nextBlock = startPage + blockSize;
		prevBlock = startPage - blockSize;
		existPrev = (currBlock != 0) ;
		existNext = currBlock !=(blockCount-1) ;
	}
}
