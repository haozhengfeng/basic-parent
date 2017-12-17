package org.haozf.basic.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

public class WapAliap extends AbstractAlipay {

	@Override
	public String pay() throws AlipayApiException {
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
		alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("{" + 
		"\"out_trade_no\":\"20150320010101002\"," + 
		"\"total_amount\":88.88," + 
		"\"subject\":\"Iphone6 16G\"," + 
		"\"seller_id\":\"2088102852583202\"," + 
		"\"product_code\":\"QUICK_WAP_PAY\"" + 
		"}");// 填充业务参数
		String form = getAlipayClient().pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		return form;
	}

	@Override
	public String query() throws AlipayApiException {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
		request.setBizContent("{"+
		    "\"out_trade_no\":\"20150320010101002\","+
		    "\"trade_no\":\"\""+
		  "}");
		AlipayTradeQueryResponse response = getAlipayClient().execute(request);//通过alipayClient调用API，获得对应的response类
		
		return response.getBody();
	}

	@Override
	public String refund() throws AlipayApiException {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
		request.setBizContent("{"+
		    "\"out_trade_no\":\"20150320010101002\","+
		    "\"trade_no\":\"\""+
		    "\"out_request_no\":\"1000001\""+
		    "\"refund_amount\":\"2014112611001004680073956707\""+
		  "}");//设置业务参数
		AlipayTradeRefundResponse response = getAlipayClient().execute(request);//通过alipayClient调用API，获得对应的response类
		// TODO 根据response中的结果继续业务逻辑处理
		return null;
	}

	@Override
	public String billDownloadurlQuery() throws AlipayApiException {
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();//创建API对应的request类
		request.setBizContent("{"+
		    "\"bill_type\":\"trade\","+
		    "\"bill_date\":\"2016-04-05\""+
		  "}");
		AlipayDataDataserviceBillDownloadurlQueryResponse response = getAlipayClient().execute(request);		return null;
	}
	
	public static void main(String[] args) {
		WapAliap wapAliap = new WapAliap();
		try {
			wapAliap.pay();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
