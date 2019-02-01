package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	 public static String getRequestUrl(HttpServletRequest request) {
			String url = "";
			String requestUrl = request.getRequestURL().toString();
			String parameterUrl = request.getQueryString();
			if(parameterUrl != null && parameterUrl.length() > 0) {
				url = requestUrl + "?" + parameterUrl;
			} else {
				url = requestUrl;
			}
			logger.info("HttpUtils getRequestUrl:"+url);
			return url;
	 }
	 
	 /**
	     * post请求 ，超时默认10秒
	     * @param url
	     * @param params
	     * @return
	     * @throws Exception 
	     */

	    public static String post(String url, Map<String, String> params) throws Exception {
	        return post(url, params, 30);
	    }
	    /**
	     * post请求
	     * @param url
	     * @param params
	     * @param timeout 超时时间，秒
	     * @return
	     * @throws Exception 
	     */
		public static String post(String url, Map<String, String> params, int timeout) throws Exception {
	        HttpClient httpclient = new DefaultHttpClient();
	        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
	        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
	        String retVal = "";
	        try {
	            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	            if (params != null) {
	                for (Map.Entry<String, String> param : params.entrySet()) {
	                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
	                }
	            }
	            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
	            HttpPost httppost = new HttpPost(url);
	            httppost.setEntity(entity);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            //retVal = new String(httpclient.execute(httppost, responseHandler).getBytes(HTTP.ISO_8859_1),HTTP.UTF_8);
	            retVal = httpclient.execute(httppost, responseHandler);
	        } catch (IOException e) {
	            logger.error("post IOException : ", e);
	        } catch (Exception ex){
	        	 logger.error("post IOException : ", ex);
	        }finally {
	            httpclient.getConnectionManager().shutdown();
	        }
	        return retVal;
	    }
	    
	    public static String postResp(String url, Map<String, Object> params) throws Exception {
	        HttpClient httpclient = new DefaultHttpClient();
	        httpclient.getParams().setIntParameter("http.socket.timeout", 10 * 1000);
	        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
	        String retVal = "";
	        try {
	            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	            if (params != null) {
	                for (Map.Entry<String, Object> param : params.entrySet()) {
	                    formparams.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
	                }
	            }
	            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
	            HttpPost httppost = new HttpPost(url);
	            httppost.setEntity(entity);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            //retVal = new String(httpclient.execute(httppost, responseHandler).getBytes(HTTP.ISO_8859_1),HTTP.UTF_8);
	            retVal = httpclient.execute(httppost, responseHandler);
	        } catch (IOException e) {
	            logger.error("post IOException : ", e);
	        } catch (Exception ex){
	        	 logger.error("post IOException : ", ex);
	        }finally {
	            httpclient.getConnectionManager().shutdown();
	        }
	        return retVal;
	    }
	    
	    /**
	     * post请求
	     * @param url
	     * @param params
	     * @param timeout 超时时间，秒
	     * @return
	     * @throws Exception 
	     */
	    public static String post(String url, Map<String, String> params, int timeout,String charset) throws Exception {
	        HttpClient httpclient = new DefaultHttpClient();
	        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
	        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
	        String retVal = "";
	        try {
	            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	            if (params != null) {
	                for (Map.Entry<String, String> param : params.entrySet()) {
	                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
	                }
	            }
	            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
	            HttpPost httppost = new HttpPost(url);
	            httppost.setEntity(entity);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            retVal = new String(httpclient.execute(
	                    httppost, responseHandler).getBytes(HTTP.ISO_8859_1),
	                    charset);
	        } catch (IOException e) {
	            throw e;
	        } catch (Exception ex){
	            throw ex;
	        }finally {
	            httpclient.getConnectionManager().shutdown();
	        }
	        return retVal;
	    }
	    /**
	     * get请求
	     * @param url
	     * @param params
	     * @return
	     * @throws IOException
	     */
	    public static String get(String url, Map<String, String> params) throws IOException {
	        HttpClient httpclient = new DefaultHttpClient();
	        httpclient.getParams().setIntParameter("http.socket.timeout", 100000);
	        String retVal = "";
	        try {
	            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
	            if (params != null) {
	                for (Map.Entry<String, String> param : params.entrySet()) {
	                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
	                }
	            }
	            String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
	            if (StringUtils.isNotEmpty(paramstr)) {
	                url = url + "?" + paramstr;
	            }
	            HttpGet httpget = new HttpGet(url);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            retVal = httpclient.execute(httpget, responseHandler);
	        } catch (IOException e) {
	            throw e;
	        } finally {
	            httpclient.getConnectionManager().shutdown();
	        }
	        return retVal;
	    }
	    
	    public static String getBody(String url, Map<String, Object> params) throws IOException {
	        HttpClient httpclient = new DefaultHttpClient();
	        httpclient.getParams().setIntParameter("http.socket.timeout", 100000);
	        String retVal = "";
	        try {
	            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
	            if (params != null) {
	                for (Map.Entry<String, Object> param : params.entrySet()) {
	                    qparams.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
	                }
	            }
	            String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
	            if (StringUtils.isNotEmpty(paramstr)) {
	                url = url + "?" + paramstr;
	            }
	            HttpGet httpget = new HttpGet(url);
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            retVal = httpclient.execute(httpget, responseHandler);
	        } catch (IOException e) {
	            throw e;
	        } finally {
	            httpclient.getConnectionManager().shutdown();
	        }
	        return retVal;
	    }
}
