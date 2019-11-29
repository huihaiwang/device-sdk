//package net.similarsu.device.sdk.hik;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.params.HttpConnectionParams;
//import org.apache.commons.httpclient.protocol.Protocol;
//import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
//import org.apache.http.*;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import javax.net.SocketFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//import java.io.*;
//import java.net.*;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by WangHao on 2014/10/20.
// */
//public class HttpUtil {
//    public static String requestUrl(String url) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            URL getUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
//            connection.connect();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
//            String lines;
//            while ((lines = reader.readLine()) != null) {
//                sb.append(lines);
//            }
//            reader.close();
//            connection.disconnect();
//            return sb.toString();
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    public static String requestUrl(String url,String charsetName) {
//        try {
//            StringBuilder sb = new StringBuilder();
//            URL getUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
//            connection.connect();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charsetName));
//            String lines;
//            while ((lines = reader.readLine()) != null) {
//                sb.append(lines);
//            }
//            reader.close();
//            connection.disconnect();
//            return sb.toString();
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//    public static String httpClientGet(String url) {
//        try {
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpPost post = new HttpPost(url);
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                String retStr = EntityUtils.toString(res.getEntity(), "UTF-8");
//                LoggerUtil.debug("request result : " + retStr);
//                return retStr;
//            }
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return "";
//        }
//        return "";
//    }
//
//    public static String httpSSLPost(String url, String json, String p12Path, String password) {
//        try {
//            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            FileInputStream instream = new FileInputStream(new File(p12Path));
//            try {
//                keyStore.load(instream, password.toCharArray());
//            } finally {
//                instream.close();
//            }
//
//            // Trust own CA and all self-signed certs
//            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
//            // Allow TLSv1 protocol only
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext, new String[]{"TLSv1"}, null,
//                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//
//            CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//
//            LoggerUtil.debug("request post url:" + url + ",json=" + json);
//            HttpPost post = new HttpPost(url);
//            StringEntity s = new StringEntity(json,"UTF-8");
//            post.setEntity(s);
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                String retStr = EntityUtils.toString(res.getEntity(), "UTF-8");
//                LoggerUtil.debug("request result : " + retStr);
//                return retStr;
//            }
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return "";
//        }
//        return "";
//    }
//
//    public static String httpClientPost(String url, String json) {
//        try {
//            LoggerUtil.debug("request post url:" + url + ",json=" + json);
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpPost post = new HttpPost(url);
//            StringEntity s = new StringEntity(json, "UTF-8");
//            s.setContentEncoding("UTF-8");
//            s.setContentType("application/json");
//            post.setEntity(s);
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                String retStr = EntityUtils.toString(res.getEntity(), "UTF-8");
//                LoggerUtil.debug("request result : " + retStr);
//                return retStr;
//            }
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return "";
//        }
//        return "";
//    }
//
//    public static String httpClientPostXml(String url,String xml) {
//        try {
//            LoggerUtil.info("request post url:", url + ",xml=" + xml);
//            CloseableHttpClient client = HttpClients.createDefault();
//            HttpPost post = new HttpPost(url);
//            StringEntity s = new StringEntity(xml, "UTF-8");
//            s.setContentEncoding("UTF-8");
//            s.setContentType("text/xml");
//            post.setEntity(s);
//
//            HttpResponse res = client.execute(post);
//            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                String retStr = EntityUtils.toString(res.getEntity(), "UTF-8");
//                LoggerUtil.info("request result", retStr);
//                return retStr;
//            }
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return "";
//        }
//        return "";
//    }
//
//    public static String requestPostUrl(String url, String json) {
//        try {
//            LoggerUtil.debug("request post url:" + url + ",json=" + json);
//            URL postUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            connection.setUseCaches(false);
////            connection.setFollowRedirects(true);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
//            connection.connect();
//
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//            out.write(json.getBytes());
//            out.flush();
//            out.close();// flush and close
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));//设置编码,否则中文乱码
//            String line;
//            StringBuilder sb = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            reader.close();
//            connection.disconnect();
//            LoggerUtil.debug("request result : " + sb.toString());
//            return sb.toString();
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return "";
//        }
//    }
//
//    public static InputStream getFilePost(String url, String json) {
//        try {
//            LoggerUtil.debug("get file post url:" + url + ",json=" + json);
//            URL postUrl = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("POST");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setChunkedStreamingMode(8036);
//            connection.connect();
//
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//            out.write(json.getBytes());
//            out.flush();
//            out.close();// flush and close
//
//            InputStream isr = connection.getInputStream();
//            connection.disconnect();
//            return isr;
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return null;
//        }
//    }
//
//    /**
//     * List<Part> parts = Lists.newArrayList();
//     * parts.add(new StringPart("access_token", getToken().getAccess_token()));
//     * parts.add(new FilePart("buffer",file));
//     * @param url
//     * @param parts
//     * @return
//     */
//    public static String requestFormPost(String url,List<Part> parts) {
//        PostMethod post = new PostMethod(url);
//        post.setRequestHeader("Connection", "Keep-Alive");
//        post.setRequestHeader("Cache-Control", "no-cache");
//        HttpClient httpClient = new HttpClient();
//        //信任任何类型的证书
//        Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
//        Protocol.registerProtocol("https", myhttps);
//
//        try {
//            Part[] partArray = new Part[]{};
//            partArray = parts.toArray(partArray);
//
//            MultipartRequestEntity entity = new MultipartRequestEntity(partArray,post.getParams());
//            post.setRequestEntity(entity);
//            int status = httpClient.executeMethod(post);
//            if (status == HttpStatus.SC_OK) {
//                return post.getResponseBodyAsString();
//            } else {
//                LoggerUtil.info("upload Media failure status is:" + status);
//            }
//        } catch (Exception execption) {
//            LoggerUtil.error(execption);
//        }
//        return null;
//    }
//
//    public static String sendFormPost(String url,FormData formData) {
//        try {
//            LoggerUtil.debug("send file post url:" + url + ",form=" + JsonUtil.writeValuesAsString(formData));
//            URL postUrl = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setRequestMethod("POST");
//            conn.setUseCaches(false);
//            conn.setInstanceFollowRedirects(true);
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setRequestProperty("Cache-Control", "no-cache");
//            conn.setChunkedStreamingMode(8036);
//            String boundary = "-----------------------------" + System.currentTimeMillis();
//            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//            conn.connect();
//
//            OutputStream output = conn.getOutputStream();
//            output.write(("--" + boundary + "\r\n").getBytes());
//            for (FileInfo fi : formData.fileInfoList) {
//                output.write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n", fi.getFileKey(), fi.getFilename()).getBytes());
//                //output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
//                output.write(fi.getBytes());
//                output.write(("--" + boundary + "\r\n").getBytes());
//            }
//            for (String key : formData.formData.keySet()) {
//                String json = formData.formData.get(key);
//                output.write(String.format("Content-Disposition: form-data; name=\"%s\";\r\n\r\n",key).getBytes());
//                output.write(json.getBytes());
//                output.write(("--" + boundary + "\r\n").getBytes());
//            }
//            output.write(("--\r\n\r\n").getBytes());
//            output.flush();
//            output.close();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));//设置编码,否则中文乱码
//            String line;
//            StringBuilder sb = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            reader.close();
//            conn.disconnect();
//            LoggerUtil.debug("request result : " + sb.toString());
//            return sb.toString();
//        } catch (Exception e) {
//            LoggerUtil.error(e);
//            return null;
//        }
//    }
//
//    public static class FormData {
//        private List<FileInfo> fileInfoList = Lists.newArrayList();
//        private Map<String,String> formData = Maps.newHashMap();
//
//        public List<FileInfo> getFileInfoList() {
//            return fileInfoList;
//        }
//
//        public void setFileInfoList(List<FileInfo> fileInfoList) {
//            this.fileInfoList = fileInfoList;
//        }
//
//        public Map<String, String> getFormData() {
//            return formData;
//        }
//
//        public void setFormData(Map<String, String> formData) {
//            this.formData = formData;
//        }
//    }
//
//    public static class FileInfo {
//        private String fileKey;
//        private String filename;
//        private String type;
//        private byte[] bytes;
//
//        public String getFileKey() {
//            return fileKey;
//        }
//
//        public void setFileKey(String fileKey) {
//            this.fileKey = fileKey;
//        }
//
//        public String getFilename() {
//            return filename;
//        }
//
//        public void setFilename(String filename) {
//            this.filename = filename;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public byte[] getBytes() {
//            return bytes;
//        }
//
//        public void setBytes(byte[] bytes) {
//            this.bytes = bytes;
//        }
//    }
//
//    public static class MySSLProtocolSocketFactory implements ProtocolSocketFactory {
//
//        private SSLContext sslcontext = null;
//
//        private SSLContext createSSLContext() {
//            SSLContext sslcontext=null;
//            try {
//                sslcontext = SSLContext.getInstance("SSL");
//                sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (KeyManagementException e) {
//                e.printStackTrace();
//            }
//            return sslcontext;
//        }
//
//        private SSLContext getSSLContext() {
//            if (this.sslcontext == null) {
//                this.sslcontext = createSSLContext();
//            }
//            return this.sslcontext;
//        }
//
//        public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
//                throws IOException {
//            return getSSLContext().getSocketFactory().createSocket(
//                    socket,
//                    host,
//                    port,
//                    autoClose
//            );
//        }
//
//        public Socket createSocket(String host, int port) throws IOException {
//            return getSSLContext().getSocketFactory().createSocket(
//                    host,
//                    port
//            );
//        }
//
//
//        public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
//                throws IOException {
//            return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
//        }
//
//        public Socket createSocket(String host, int port, InetAddress localAddress,
//                                   int localPort, HttpConnectionParams params) throws IOException {
//            if (params == null) {
//                throw new IllegalArgumentException("Parameters may not be null");
//            }
//            int timeout = params.getConnectionTimeout();
//            SocketFactory socketfactory = getSSLContext().getSocketFactory();
//            if (timeout == 0) {
//                return socketfactory.createSocket(host, port, localAddress, localPort);
//            } else {
//                Socket socket = socketfactory.createSocket();
//                SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
//                SocketAddress remoteaddr = new InetSocketAddress(host, port);
//                socket.bind(localaddr);
//                socket.connect(remoteaddr, timeout);
//                return socket;
//            }
//        }
//
//        //自定义私有类
//        private static class TrustAnyTrustManager implements X509TrustManager {
//
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            }
//
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[]{};
//            }
//        }
//    }
//
//    /**
//     * 获取response header中Content-Disposition中的filename值
//     * @param response
//     * @return
//     */
//    public static String getFileName(HttpResponse response) {
//        Header contentHeader = response.getFirstHeader("Content-Disposition");
//        String filename = null;
//        if (contentHeader != null) {
//            HeaderElement[] values = contentHeader.getElements();
//            if (values.length == 1) {
//                NameValuePair param = values[0].getParameterByName("filename");
//                if (param != null) {
//                    try {
//                        filename = new String(param.getValue().toString().getBytes(), "utf-8");
//                        filename = URLDecoder.decode(param.getValue(),"utf-8");
////                        filename = param.getValue();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return filename;
//    }
//}
