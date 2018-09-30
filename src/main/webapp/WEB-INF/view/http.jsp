<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #area{
            height:100%;
            width: 100%;
            font-family: ‘微软雅黑’;
            font-size: 100%;
        }
    </style>
</head>
<body>
<input type="button" onclick="back()" value="返回主页">
<textarea id="area" class="run">
package com.fanwei.upcompany.commonutils;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    public static String formPost(String url,Map<String,String> params) {
        return formPost(url, params, "utf-8");
    }

    public static String formPost(String url,Map<String,String> params, String charset) {
        if (StringUtils.isBlank(url)
                || StringUtils.isBlank(charset)
                || null == params) {
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        for (String key:params.keySet()) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            String value = params.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            list.add(new BasicNameValuePair(key, value));
        }
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(list, charset));
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e) {
            LogUtils.error("http_post_form_error", e);
        }finally {
            try {
                httpClient.close();
            }catch (Exception e) {
                LogUtils.error("close_http_client_error", e);
            }
        }
        return null;
    }

    public static String formPostWithHttps(String url,Map<String,String> params,
                                           String credentialPath, String password, String instanceType) throws Exception {
        if (StringUtils.isBlank(url) || null == params || StringUtils.isBlank(credentialPath) || StringUtils.isBlank(password)) {
            return null;
        }
        HttpClient httpClient = getHttpsClient(credentialPath, password, instanceType);
        if(null == httpClient){
            return null;
        }
        String body = JsonUtils.getJsonStringFromMap(params);
        return httpPost(httpClient,body,url,"application/json;charset=UTF-8","UTF-8");
    }

    public static String formPostWithHeader(String url, Map<String, String> headerMap, Map<String, String> body, String charset){
        if (StringUtils.isBlank(url) || null == headerMap|| null == body) {
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        for (Map.Entry<String,String> entry : headerMap.entrySet()) {
            if (null == entry) {
                continue;
            }
            post.setHeader(entry.getKey(), entry.getValue());
        }

        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        for (String key : body.keySet()) {
            if (StringUtils.isBlank(key)) {
                continue;
            }
            String value = body.get(key);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            list.add(new BasicNameValuePair(key, value));
        }
        try{
            post.setEntity(new UrlEncodedFormEntity(list, charset));
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e){
            LogUtils.error("http_post_form_error", e);
        }finally {
            try {
                httpClient.close();
            }catch (Exception e) {
                LogUtils.error("close_http_client_error", e);
            }
        }
        return null;
    }

    public static String formPostWithHeader(String url, Map<String, String> headerMap, Map<String, String> body,
                                            String charset, String credentialPath, String password, String type) throws Exception {
        if (StringUtils.isBlank(url) || null == headerMap|| null == body
                || StringUtils.isBlank(credentialPath) || StringUtils.isBlank(password)) {
            return null;
        }
        HttpClient httpClient = getHttpsClient(credentialPath, password, type);
        if(null == httpClient){
            return null;
        }
        HttpPost post = new HttpPost(url);

        for (Map.Entry<String,String> entry : headerMap.entrySet()) {
            if (null == entry) {
                continue;
            }
            post.setHeader(entry.getKey(), entry.getValue());
        }

        String bodyJson = JsonUtils.getJsonStringFromMap(body);
        StringEntity entity = new StringEntity(bodyJson,charset);
        entity.setContentType("application/json;charset=UTF-8");
        try{
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e){
            LogUtils.error("http_post_form_error", e);
        }
        return null;
    }

    public static String xmlPost(String url, String content, String charset){
        if (StringUtils.isBlank(url) || StringUtils.isBlank(content)) {
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(content, charset));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e){
            LogUtils.error("xml response error",e);
        }
        return null;
    }

//    public static void main(String[] args) {
//            String json = "{\"subject\":[\"23e9cf2bd149422eb2257c09c7804e5f\"],\"sign_type\":[\"RSA2\"],\"buyer_logon_id\":[\"133****3815\"],\"auth_app_id\":[\"2018031502380951\"],\"notify_type\":[\"trade_status_sync\"],\"out_trade_no\":[\"23e9cf2bd149422eb2257c09c7804e5f\"],\"version\":[\"1.0\"],\"point_amount\":[\"0.00\"],\"fund_bill_list\":[[{\"amount\":\"20.00\",\"fundChannel\":\"ALIPAYACCOUNT\"}]],\"buyer_id\":[\"2088522343035162\"],\"total_amount\":[\"20.00\"],\"trade_no\":[\"2018051121001004160556739100\"],\"notify_time\":[\"2018-05-11 16:43:02\"],\"charset\":[\"GBK\"],\"invoice_amount\":[\"20.00\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"gmt_payment\":[\"2018-05-11 16:39:11\"],\"sign\":[\"rhWMPDnCDHvDzQVpK4wWMTpg+POaqC18IXhaBRcb8jLUQPZbqiQkIkwJFkfDmIrVCTPA8u3buTLp+kngJ3abDzm8YuVe5gWJLvIp0LcTZccFc9mCuAwGMx3c4rhL6oQDOiXFJyX92ftdBxoR3He3BOTI7ttaUzl6TUgWNZKBUPV5smN4yakgjsSE0dlVlXSsvFBSb6wMq3tAeqMrNNYCppbtQjW2p9dyAvdynYtXJyR8Wtg+HPdBqyGui81mmpSmbTnzfJHsJLSeswmlBPNKF5HKv+hulSHbleZXgU0kB7Hh10msnrj01HXyTczhOVG4/OdSy2DeanJipJYss4E8PQ==\"],\"gmt_create\":[\"2018-05-11 16:39:09\"],\"buyer_pay_amount\":[\"20.00\"],\"receipt_amount\":[\"20.00\"],\"app_id\":[\"2018031502380951\"],\"seller_id\":[\"2088031558604129\"],\"seller_email\":[\"game@letvgames.com\"],\"notify_id\":[\"ec88dcdc853cb4fae4db35c330dc6c8h8l\"]}";
//            json = json.replace("[[", "[");
//            json = json.replace("]]", "]");
//            json = json.replace("[\"", "\"");
//            json = json.replace("\"]", "\"");
//            try {
//                String s = formPost("http://callback.jubaopay.com/api/common/apls/apls_alipaywap_sdk_q1352/callback.htm", JsonUtils.getMap(json));
//                System.out.print(s);
//            }catch (Exception e) {
//
//            }
//    }

    public static HttpClient getHttpsClient(String credentialPath, String credentialPassword, String instanceType) throws Exception{
        if (StringUtils.isBlank(credentialPassword)
                || StringUtils.isBlank(credentialPath) || StringUtils.isBlank(instanceType)) {
            return null;
        }
        KeyStore keyStore = KeyStore.getInstance(instanceType);
        FileInputStream inputStream = new FileInputStream(credentialPath);
        try {
            keyStore.load(inputStream, credentialPassword.toCharArray());
        } finally {
            inputStream.close();
        }
        SSLContext sslContext = SSLContexts
                .custom()
                .loadKeyMaterial(keyStore, credentialPassword.toCharArray())
                .build();
        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(
                sslContext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setSSLSocketFactory(factory)
                .build();
        return httpClient;
    }

    public static String httpGet(String url,Map<String,String> map, int timeOut) {
        if (StringUtils.isBlank(url)
                || null == map) {
            return null;
        }
        String queryString = ParseUtils.getQueryString(map);
        if (StringUtils.isBlank(queryString)) {
            return null;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        timeOut = timeOut == 0 ? 60 : timeOut;
        try {
            url = url + "?" + queryString;
            HttpGet httpGet = new HttpGet(url);
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeOut * 1000)
                    .setSocketTimeout(timeOut * 1000)
                    .build();
            httpGet.setConfig(config);
            HttpResponse response =  httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e) {
            LogUtils.error("http_get_error", e);
        }finally {
            try {
                httpClient.close();
            }catch (Exception e) {
                LogUtils.error("close_http_client_error", e);
            }
        }
        return null;
    }

    public static String httpsGetWithHeader(Map<String, String> headerMap, String url,
                                            Map<String,String> map, int timeOut, String credentialPath, String password, String instanceType) throws Exception {
        if (null == headerMap || StringUtils.isBlank(url) || null == map
                || StringUtils.isBlank(credentialPath) || StringUtils.isBlank(password)) {
            return null;
        }
        String queryString = ParseUtils.getQueryString(map);
        if (StringUtils.isBlank(queryString)) {
            return null;
        }
        HttpClient httpClient = getHttpsClient(credentialPath, password, instanceType);
        if(null == httpClient){
            return null;
        }
        timeOut = timeOut == 0 ? 60 : timeOut;
        url = url + "?" + queryString;
        HttpGet httpGet = new HttpGet(url);
        for (Map.Entry<String,String> entry : headerMap.entrySet()) {
            if (null == entry) {
                continue;
            }
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeOut * 1000)
                    .setSocketTimeout(timeOut * 1000)
                    .build();
            httpGet.setConfig(config);
            HttpResponse response =  httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e) {
            LogUtils.error("http_get_error", e);
        }
        return null;
    }


    public static String httpPost(HttpClient httpClient, String body, String url, String contentType, String charset) {
        if (StringUtils.isBlank(url)
                || StringUtils.isBlank(body)
                || StringUtils.isBlank(contentType)
                || StringUtils.isBlank(charset)
                || null == httpClient ){
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity entity = new StringEntity(body, charset);
            entity.setContentType(contentType);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (null == responseEntity) {
                return null;
            }
            return EntityUtils.toString(responseEntity);
        }catch (Exception e) {
             LogUtils.error("http_post_error", e);
        }
        return null;
    }

    public static String sendGetRequest(String url,String contentType) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (url.contains(" ")) {
            url = url.replaceAll(" ", "%20");
        }
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        if (StringUtils.isBlank(contentType)) {
            httpGet.setHeader("Content-Type", "text/htmls;charset=UTF-8");
        }else {
            httpGet.setHeader("Content-Type", contentType);
        }

        BufferedReader bufferedReader = null;

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder responseString = new StringBuilder();
            String result = bufferedReader.readLine();
            while (result != null) {
                responseString.append(result);
                result = bufferedReader.readLine();
            }
            return responseString.toString();
        } catch (Exception e) {
            LogUtils.error("http_get_request_error", e);
            return null;
        }finally{
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                LogUtils.error("IOException http_get_turrn_off_is_error",e);
            }
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {
        private DefaultTrustManager() {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }


    /**
     * 发送contentType = application/json 请求
     * @param url
     * @param paramsMap
     * @return
     */
    public static String httpPostWithApplicationJson(String url,Map<String,String> paramsMap){
        if (StringUtils.isBlank(url) || null == paramsMap){
            return null;
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String jsonParams  = JsonUtils.getJsonStringFromMap(paramsMap);
        if (StringUtils.isBlank(jsonParams)){
            return null;
        }

        return HttpUtils.httpPost(httpClient,jsonParams,url,"application/json","utf-8");
    }
}




</textarea>


<script>
    function back() {
        window.location.href = "/start.htm";

    }
</script>
</body>
</html>
