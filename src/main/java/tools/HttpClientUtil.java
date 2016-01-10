package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Created by weihuang on 2015/10/26.
 */
public class HttpClientUtil {
	// private final static Logger LOGGER =
	// LoggerFactory.getLogger(HttpClientUtil.class);
	private static final int TIMEOUT = 2000;

	public static String getRequestContent(String url) {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
				.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		try {
			// 执行get请求
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			// 获取响应消息实体
			HttpEntity entity = httpResponse.getEntity();
			// 判断响应实体是否为空
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			// LOGGER.warn("HttpClientUtil.class error!");
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				// LOGGER.warn("HttpClientUtil.class IO error!");
			}
		}
		return null;
	}

	public static String getPostContent(String url, Map<String, String> map, String cookie) throws IOException {
		String resString = "";
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		try {

			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Cookie", cookie);
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			if (map != null) {
				for (Entry<String, String> e : map.entrySet()) {
					params.add(new BasicNameValuePair(e.getKey(), e.getValue()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			HttpResponse response = closeableHttpClient.execute(httpPost);

			// 获取响应消息实体
			HttpEntity entity0 = response.getEntity();
			// 响应状态
			// LOGGER.info("status:" + response.getStatusLine());
			// 判断响应实体是否为空
			resString = EntityUtils.toString(entity0);
			httpPost.releaseConnection();

		} catch (IOException e) {
			// LOGGER.error("getUrlEntityList is IOException", e);
		} finally {
			try {
				// 关闭流并释放资源
				closeableHttpClient.close();
			} catch (IOException e) {
				// LOGGER.error("finally getUrlEntityList is IOException", e);
			}
		}

		return resString;
	}
}
