package com.boonya.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@ThreadSafe
@SuppressWarnings("deprecation")
public class UsefulHttpClient{
	
	private  HttpClient httpClient = null;// 普通HTTP访问
	
	private  HttpClient httpsClient = null;// 基于SSL的HTTPS访问
	
	private static class SingletonHttpClient {
		/**
		 * 单例对象实例
		 */
		static final UsefulHttpClient INSTANCE = new UsefulHttpClient();
	}

	private UsefulHttpClient() {
		if(httpClient==null){
			httpClient = new DefaultHttpClient();
		}
		if(httpsClient==null){
			httpsClient = new DefaultHttpClient();
			initHttps();
		}
	}
	
	/**
	 * 单例实例
	 */
	public static UsefulHttpClient getInstance() {
		return SingletonHttpClient.INSTANCE;
	}
	
	/**
	 * 设置忽略安全验证
	 * 
	 * @MethodName: initHttps
	 * @Description:
	 * @throws
	 */
	private void initHttps() {
		// ==设置忽略访问SSL==
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
		try {
			ctx.init(null, new TrustManager[] { xtm }, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		SSLSocketFactory sf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme("https", 443, sf);
		httpsClient.getConnectionManager().getSchemeRegistry().register(sch);
	}
	
	
	public void post(String url,String requestBody){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,String requestBody,String charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,String requestBody,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,String requestBody,ContentType contentType){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody, contentType);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public void post(String url,String requestBody,String mimeType,String charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody, mimeType, charset);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,List<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,List<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,Iterable<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void post(String url,Iterable<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			httpClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,String requestBody){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,String requestBody,String charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,String requestBody,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,String requestBody,ContentType contentType){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody, contentType);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public void postSSL(String url,String requestBody,String mimeType,String charset){
		HttpPost post=new HttpPost(url);
		try {
			HttpEntity entity=new StringEntity(requestBody, mimeType, charset);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,List<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,List<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,Iterable<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void postSSL(String url,Iterable<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			httpsClient.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
