package common;

public class PageBar {
	
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalContents / numPerPage) ; // 전체페이지수 
		int pagebarSize = 5;
		int pagebarStart = (cPage - 1) / pagebarSize * pagebarSize + 1; // 1, 6, 11
		int pagebarEnd = pagebarStart + pagebarSize - 1; // 5, 10, 15 
		int pageNo = pagebarStart;
		
		url += "?cPage=";
		
		// 이전 prev
		if(pageNo == 1) {
			// prev버튼 비활성화
		}
		else {
			// prev버튼 활성화
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>");
			pagebar.append("\n");
		}
		
		// 번호
		while(pageNo <= pagebarEnd && pageNo <= totalPages) {
			if(pageNo == cPage) {
				// 현재페이지인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");
			}
			else {
				// 현재페이지가 아닌 경우(링크필요)
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}
		// 다음 next
		if(pageNo > totalPages) {
			
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>");
			pagebar.append("\n");
		}
		return pagebar.toString();
	}
}
