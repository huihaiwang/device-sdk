//package net.similarsu.device.sdk.hik;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpStatus;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.AuthCache;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.auth.DigestScheme;
//import org.apache.http.impl.client.BasicAuthCache;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.protocol.HTTP;
//import org.springframework.util.StringUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//
//public class HttpDigestUtils {
//
//    public static String sendHttpClientAuth(String url, String username, String password, String parm){
//        CloseableHttpClient client = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        BufferedReader in = null;
//        try {
//            URI serverUri = new URI(url);
//            //host
//            HttpHost httpHost = new HttpHost(serverUri.getHost(), serverUri.getPort());
//            //post
//            HttpGet post = new HttpGet(url);
//            // 创建认证缓存
//            AuthCache authCache = new BasicAuthCache();
//            // 创建基础认证机制 添加到缓存
//            DigestScheme digestAuth = new DigestScheme();
//            authCache.put(httpHost, digestAuth);
//
//            //context
//            //基础凭证提供器,明文传输数据
//            CredentialsProvider credsProvider = new BasicCredentialsProvider();
//            credsProvider.setCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()), new UsernamePasswordCredentials(username,password));
//
//            // 将认证缓存添加到执行环境中  即预填充
//            HttpClientContext context = HttpClientContext.create();
//            context.setCredentialsProvider(credsProvider);
//            context.setAuthCache(authCache);
//            response = client.execute(post, context);
//            System.out.println(response.getStatusLine());
//            StringBuilder result = new StringBuilder();
//            if(response.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK){
//                 HttpEntity entity = response.getEntity();
//                 if(entity!=null){
//                    in = new BufferedReader(new InputStreamReader(entity.getContent()));
//                    String line = null;
//                    while((line = in.readLine())!=null){
//                        System.out.println(result.toString());
//                        result.append(line);
//                    }
//                 }
//            }
//
//            System.out.println(result.toString());
//            return "";
//        }catch(Exception e){
//            e.printStackTrace();
//            return "httpclient异常:"+e.getMessage();
//        }finally{
//            try {
//                if(response!=null){
//                    response.close();
//                }
//                if (in != null)
//                    in.close();
//                if(client!=null){
//                    client.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}