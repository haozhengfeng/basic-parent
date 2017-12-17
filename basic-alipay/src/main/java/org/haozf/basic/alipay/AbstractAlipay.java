package org.haozf.basic.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public abstract class AbstractAlipay {

	private AlipayClient alipayClient;

	public AlipayClient getAlipayClient() {
		return alipayClient;
	}

	public AbstractAlipay() {
		if (alipayClient == null) {
			alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", SystemContext.APP_ID, SystemContext.APP_PRIVATE_KEY, "json", SystemContext.CHARSET, SystemContext.ALIPAY_PUBLIC_KEY);
		}
	}

	public abstract String pay() throws AlipayApiException;
	
	public abstract String query() throws AlipayApiException;
	
	public abstract String refund() throws AlipayApiException;
	
	public abstract String billDownloadurlQuery() throws AlipayApiException;
	

}
