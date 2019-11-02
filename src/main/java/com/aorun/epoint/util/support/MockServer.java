/**
 * 
 */
package com.aorun.epoint.util.support;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author zhailiang
 *
 */
public class MockServer {
	
	public static void main(String[] args) {
		
		configureFor("10.10.0.95", 9999);
		
		stubFor(get(urlEqualTo("/book"))
				.willReturn(okJson("{'name':'tom'}")));
		stubFor(get(urlEqualTo("/epoint_service/epoint/epointCollectStatistics")).withQueryParam("sid",equalTo("*")).willReturn(okJson("{\"responseCode\":\"0\",\"data\":{\"comeInWorkertime\":\"2019年06月04日\",\"weekRank\":4,\"workerMemberName\":\"卡卡\",\"totalRank\":2,\"imgPath\":\"\",\"epointLevel\":\"1\",\"workerName\":\"澳润工会\",\"epoint\":107},\"msg\":\"请求数据成功!\"}")));

		//访问链接：
		//http://10.10.0.95:9999/book
		//TODO:访问问题.
		//http://10.10.0.95:9999/epoint_service/epoint/epointCollectStatistics?sid=Z3k6os3Q9Rf748UcW7I292I6w1o7as6p


		
	}

}
