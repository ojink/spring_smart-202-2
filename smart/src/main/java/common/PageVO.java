package common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {
	//검색관련
	private String search="all", keyword="", viewType="list";
//	페이지당 보여질 목록 수 : 10
//	블럭당 보여질 페이지의 수 : 10
	private int pageList = 10;
	private int blockPage = 10;
	private int totalList, totalPage, totalBlock;
	
	public void setTotalList(int totalList) {
		this.totalList = totalList;
//	총 목록수 : 352
//	총 페이지수 : 36 페이지 = 352 / 10 = 35 ... 2
//	총 블록수 : 3 블록 = 36 / 10 = 3 ... 6
		totalPage = totalList / pageList;
		if( totalList % pageList >0 ) ++totalPage;
		
		totalBlock = totalPage / blockPage;
		if( totalPage % blockPage > 0 ) ++totalBlock;
		
//	 각 페이지의 끝 목록번호 :  총 목록수 - (페이지번호-1) * 페이지당 보여질 목록수  
//	 각 페이지의 시작 목록번호 :  끝 목록번호 - (페이지당 보여질 목록수-1)
		endList = totalList - (curPage-1) * pageList;
		beginList = endList - (pageList-1);
		
//	현재블록번호 : 페이지번호 / 블록당 보여질 페이지수
//	각 블럭의 끝 페이지번호 : 블록번호 * 블록당 보여질 페이지수
//	각 블럭의 시작 페이지번호 : 끝 페이지번호 - (블럭당 보여질 페이지수-1)
		curBlock = curPage / blockPage;
		if( curPage % blockPage > 0 ) ++curBlock;
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
//		끝 페이지번호가 총 페이지수보다 클 수 없으므로 총 페이지수를 끝 페이지번호로 한다.
		if( totalPage < endPage ) endPage = totalPage;
	}

	private int curPage = 1; //현재페이지번호
	private int beginList, endList;
	
	private int curBlock, endPage, beginPage;	
	
	
	
	
}
