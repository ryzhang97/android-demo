package com.match.library.utils.http;

import com.match.library.contact.Config;
import com.match.library.utils.EncryptUtils;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpURLHelper {

    /**
     * 设置http Get连接
     *
     * @param http  服务路径
     * @param token https凭证
     * @return
     */
    public HttpURLConnection CreateGETHttpURLConn(String http, String token) {
        // 定义Http连接
        HttpURLConnection conn = null;

        URL url = null;
        try {
            String webUrl = null;
            //api服务地址
            if (http.indexOf("http") == 0) {
                webUrl = http;
            } else {
                webUrl = Config.URL + http;
            }

            // 加密
            String key = EncryptUtils.GetMD5Code(webUrl);
            webUrl = webUrl;
//					+ "&key=" + key;
            // webUrl=URLEncoder.encode(webUrl, "utf-8");
            url = new URL(webUrl);
            System.out.println("url:" + url);

            conn = (HttpURLConnection) url.openConnection();
            // 设置维持长连接:
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            conn.setAllowUserInteraction(true);

            conn.setRequestProperty("Authorization", "Basic " + token);

            // 设置超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 设置请求类型为
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            return conn;
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * 设置http Get连接
     *
     * @param host  域名地址
     * @param http  服务路径
     * @param token https凭证
     * @return
     */
    public HttpURLConnection CreateGETHttpURLConn(String host, String http,
                                                  String token) {
        // 定义Http连接
        HttpURLConnection conn = null;

        URL url = null;
        try {
            String webUrl = host + http;
            webUrl = webUrl.replace('\\', '/');
            url = new URL(webUrl);
            System.out.println("url:" + url);

            conn = (HttpURLConnection) url.openConnection();
            // 设置维持长连接:
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            conn.setAllowUserInteraction(true);

            conn.setRequestProperty("Authorization", "Basic " + token);

            // 设置超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 设置请求类型为
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            return conn;
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public HttpURLConnection CreateSingleDownloadHttpURLConn(String http,
                                                             String token) {
        // 定义Http连接
        HttpURLConnection conn = null;

        URL url = null;
        try {

            url = new URL(http);

            conn = (HttpURLConnection) url.openConnection();
            // 设置维持长连接:
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            conn.setAllowUserInteraction(true);

            conn.setRequestProperty("Authorization", "Basic " + token);

            // 设置超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 设置请求类型为
            conn.setRequestMethod("GET");
            // conn.setDoInput(true);

            return conn;
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public HttpURLConnection CreatePOSTHttpURLConn(String http, String token) {
        // 定义Https连接
        HttpURLConnection conn = null;

        URL url = null;
        try {
            String webUrl = null;
            //api服务地址
            if (http.contains("http")) {
                webUrl = http;
            } else {
                webUrl = Config.URL + http;
            }
//			String key = EncryptHelper.GetMD5Code(webUrl);
//			webUrl = webUrl + "&key=" + key;
            url = new URL(webUrl);
            System.out.println("url:" + url);
            conn = (HttpURLConnection) url.openConnection();
            // 设置维持长连接:
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集
            // conn.setRequestProperty("Charset", "UTF-8");

            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // conn.setRequestProperty("Content-Type",
            // "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Type", "text/json");

            // conn.setAllowUserInteraction(true);

            conn.setRequestProperty("Authorization", "Basic " + token);

            // conn.setRequestProperty("Content-Type", "application/json");
            // conn.setRequestProperty("Accept", "application/json");

            // 设置超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 设置请求类型为
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }


    /**
     * 设置Post连接
     *
     * @param host  域名地址
     * @param http  服务接口地址
     * @param token 验证票据
     * @return 连接对象
     */
    public HttpURLConnection CreatePOSTHttpURLConn(String host, String http, String token) {
        // 定义Https连接
        HttpURLConnection conn = null;

        URL url = null;
        try {
            //数据服务地址
            String webUrl = host + http;
            url = new URL(webUrl);
            //建立连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置维持长连接:
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集
            conn.setRequestProperty("Charset", "UTF-8");

            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type
            conn.setRequestProperty("Content-Type", "text/xml");

            // conn.setAllowUserInteraction(true);

            conn.setRequestProperty("Authorization", "Basic " + token);

            // conn.setRequestProperty("Content-Type", "application/json");
            // conn.setRequestProperty("Accept", "application/json");

            // 设置超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            // 设置请求类型为
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

//
//	/**
//	 * 设置Aspx Get连接
//	 * @return
//	 */
//	public HttpClient getAspxHttpClient() {
//		try {
//			KeyStore trustStore = KeyStore.getInstance(KeyStore
//					.getDefaultType());
//			trustStore.load(null, null);
//
//			CustomSSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
//			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//
//			HttpParams params = new BasicHttpParams();
//			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
//			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
//			ConnManagerParams.setTimeout(params, 10000);//设置连接池超时时间
//			HttpConnectionParams.setConnectionTimeout(params, 10000);//设置请求的超时时间
//			HttpConnectionParams.setSoTimeout(params, 10000);//设置请求的超时时间
//
//			SchemeRegistry registry = new SchemeRegistry();
//			registry.register(new Scheme("http", PlainSocketFactory
//					.getSocketFactory(), 80));
//			registry.register(new Scheme("https", sf, 443));
//
//			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
//					params, registry);
//
//			return new DefaultHttpClient(ccm, params);
//		} catch (Exception e) {
//			System.out.println("getAspxHttpClient exception:"+e.getMessage());
//			return null;
//		}
//	}
}
