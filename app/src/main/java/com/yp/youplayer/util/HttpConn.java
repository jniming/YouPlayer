package com.yp.youplayer.util;

import android.content.Context;
import android.util.Log;

import com.yp.youplayer.down.DigestUtil;
import com.yp.youplayer.down.DownloadProcessDao;
import com.yp.youplayer.down.DownloadProcessInfo;
import com.yp.youplayer.down.MemoryManager;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class HttpConn {
    // post请求
    public static String post(String url, String content) {
        HttpURLConnection conn = null;
        // 创建一个URL对象
        String response = "";
        InputStream is = null;
        OutputStream out = null;

        try {
            URL murl = new URL(url);
            trustAllHosts();
            if (murl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) murl
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) murl.openConnection();
            }
            conn.setRequestMethod("POST");// 设置请求方法为post
            conn.setDoOutput(true);// 设置此方法,允许向服务器输出内容// post请求的参数
            // 获得一个输出流,向服务器写数据,默认情况下,系统不允许向服务器输出内容
            out = conn.getOutputStream();// 获得一个输出流,向服务器写数据out.write(data.getBytes());
            out.write(content.getBytes());
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();// 调用此方法就不必再使用conn.connect()方法if
            LogUtil.d("请求指令码-->" + responseCode); // (responseCode == 200)
            if (responseCode == 200) {
                is = conn.getInputStream();
                response = getStringFromInputStream(is);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return response;

    }

    // get请求
    public static String get(String url) {
        String response = "";
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL murl = new URL(url);
            trustAllHosts();
            if (murl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) murl
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) murl.openConnection();
            }
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                is = conn.getInputStream();
                response = getStringFromInputStream(is);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return response;
    }

    private static String getStringFromInputStream(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String state = "";
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
            state = os.toString().trim();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return state;
    }

    /**
     * 下载图片,无需验证md5值
     *
     * @param context
     * @param image_url
     * @return
     */
    @SuppressWarnings("resource")
    public static File DownImage(final Context context, final String image_url) {
        File downloadedFile = null;
        InputStream in = null;
        FileOutputStream out = null;
        downloadedFile = new File(context.getFilesDir(), getFileName(image_url));
        HttpURLConnection con = null;
        try {
            URL url = new URL(image_url);
            trustAllHosts();
            if (url.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) url
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }
            con.setRequestMethod("GET");
            con.setReadTimeout(5000);
            con.setConnectTimeout(10000);
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                in = con.getInputStream();
                out = new FileOutputStream(downloadedFile);
                byte[] buffer = new byte[1024];
                int len;
                while (-1 != (len = in.read(buffer))) {
                    out.write(buffer, 0, len);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (null != downloadedFile && downloadedFile.exists()) {
                downloadedFile.delete();
            }
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }
        return downloadedFile;
    }

    /**
     * 下载图片，需验证md5值
     *
     * @param image_url
     * @param
     */
    public static File downloadImage(final Context context,
                                     final String image_url, final String fileMd5) throws Exception {

        File downloadedFile = null;
        InputStream in = null;
        FileOutputStream out = null;
        HttpURLConnection con = null;
        try {
            // 判断是否有足够的空间进行存储
            downloadedFile = new File(context.getFilesDir(),
                    getFileName(image_url));
            if (downloadedFile.exists()) {// 若文件已存在且完整，直接返回
                if (null != fileMd5
                        && fileMd5.equals(MD5Util.getFileMd5(downloadedFile))) {
                    LogUtil.d("图片存在-->直接使用");
                    return downloadedFile;
                }
            }

            URL url = new URL(image_url);
            trustAllHosts();
            if (url.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) url
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }
            con.setRequestMethod("GET");
            con.setReadTimeout(5000);
            con.setConnectTimeout(10000);
            int responseCode = con.getResponseCode();
            MessageDigest digest = MessageDigest.getInstance("md5");
            if (responseCode == 200) {
                in = con.getInputStream();
                out = new FileOutputStream(downloadedFile);
                byte[] buffer = new byte[1024 * 10];
                int len;
                while (-1 != (len = in.read(buffer))) {
                    out.write(buffer, 0, len);
                    digest.update(buffer, 0, len);
                }
                out.flush();

                String md5Value = MD5Util.bufferToHex(digest.digest());// 文件md5值

                LogUtil.d(getFileName(image_url) + "complete！ md5："
                        + md5Value);

                if (0 == fileMd5.compareToIgnoreCase(md5Value)) {
                    // MD5 验证正确
                    return downloadedFile;
                } else {
                    LogUtil.d("md5 check wrong, rollback~");
                    if (null != downloadedFile && downloadedFile.exists()) {
                        downloadedFile.delete();
                    }
                    return null;
                }
            } else {
                LogUtil.d("failed to connect");
                if (null != downloadedFile && downloadedFile.exists()) {
                    downloadedFile.delete();
                }
                return null;
            }

        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }
    }

    /**
     * 断点续传下载
     *
     * @param context
     * @param downloadUrl
     * @param fileLength
     * @param fileMd5
     * @param
     * @return
     * @throws ConnectTimeoutException
     * @throws
     * @throws
     * @throws
     * @throws Exception
     */
    public static File download(Context context, String downloadUrl,
                                int fileLength, String fileMd5) {

        RandomAccessFile rdAcessFile = null;
        InputStream in = null;
        File downloadedFile = null;
        int downloadLength = 0;
        HttpURLConnection con = null;

        try {
            URL url = new URL(downloadUrl);
            trustAllHosts();
            if (url.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) url
                        .openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }
            con.setRequestMethod("GET");
            downloadedFile = createFile(context, downloadUrl, fileLength);

            if (null != downloadedFile && downloadedFile.exists()
                    && downloadedFile.length() == fileLength) {// 若文件已存在且完整，直接返回
                if (null != fileMd5
                        && fileMd5
                        .equals(DigestUtil.getFileMd5(downloadedFile))) {
                    LogUtil.d("文件存在且完整-");
                    return downloadedFile;

                }
            }

            rdAcessFile = new RandomAccessFile(downloadedFile, "rwd");
            // 记录或取出下载信息
            DownloadProcessDao processDao = new DownloadProcessDao(context);
            DownloadProcessInfo query = processDao.query(DigestUtil
                    .mMD5Digest(downloadUrl));

            if (null != query) {
                downloadLength = query.getDownloadedLen();
                fileLength = query.getFileLen();
                rdAcessFile.seek(downloadLength);
            } else {
                processDao.insert(DigestUtil.mMD5Digest(downloadUrl),
                        downloadLength, fileLength);
                rdAcessFile.setLength(fileLength);
                rdAcessFile.seek(0);
            }

            LogUtil.d("下载地址为-->" + downloadUrl);

            // 设置请求头
            con.setRequestProperty("Range", "bytes=" + downloadLength + "-"
                    + (fileLength - 1));

            // // 访问资源文件
            // con.addHeader("Range", "bytes=" + downloadLength + "-"
            // + (fileLength - 1));

            int statusCode = con.getResponseCode();
            LogUtil.d("download file  response code：" + statusCode);

            if (206 == statusCode || 200 == statusCode) {
                Log.d("core1", "d---d");
                in = con.getInputStream();
                byte[] buf = new byte[1024];
                int len;
                LogUtil.d("start downloading！");
                long start = System.currentTimeMillis();

                while (-1 != (len = in.read(buf))) {
                    rdAcessFile.write(buf, 0, len);
                    downloadLength += len;
                    processDao.updateProcess(
                            DigestUtil.mMD5Digest(downloadUrl), downloadLength);
                    LogUtil.d("rdAcessFile length=" + rdAcessFile.length()
                            + ",downloadLength=" + downloadLength);
                    // // 回调
                    // if (downloadListener != null) {
                    // downloadListener.onDownloading(downloadUrl,
                    // downloadedFile.getAbsolutePath(),
                    // downloadLength, fileLength,
                    // downloadedFile.getName());
                    // }
                }

                long end = System.currentTimeMillis();
                LogUtil.d("consume time:" + (end - start));

                processDao.delete(DigestUtil.mMD5Digest(downloadUrl));// 下载完成，删除断点下载信息

                // 文件md5值，因为是断点续传，所以不能再下载过程中同步生成md5值
                String md5Value = DigestUtil.getFileMd5(downloadedFile);// 文件md5值

                LogUtil.d(downloadedFile.getName() + " complete！  md5："
                        + md5Value);

                if (0 == fileMd5.compareToIgnoreCase(md5Value)
                        && downloadedFile.length() == downloadLength) {
                    // MD5 验证正确

                    String filePath = downloadedFile.getAbsolutePath();
                    // 修改文件权限 ,否者无法文件无法打开
                    Runtime runtime = Runtime.getRuntime();
                    String command = "chmod 777 " + filePath;
                    try {
                        runtime.exec(command);
                    } catch (IOException e) {
                        downloadedFile.delete();
                        e.printStackTrace();
                    }

                    return downloadedFile;
                    // downloadListener.onDownloadSuccess(downloadUrl,
                    // downloadedFile.getAbsolutePath(),
                    // downloadedFile.getName(), downloadedFile.length());
                } else {
                    LogUtil.d("md5 check wrong, rollback~");
                    if (null != downloadedFile && downloadedFile.exists()) {
                        downloadedFile.delete();
                    }
                    return null;
                }
            } else {
                LogUtil.d(downloadUrl + "  failed to connect！");
                if (null != downloadedFile && downloadedFile.exists()) {
                    downloadedFile.delete();
                }
                return null;
            }
        } catch (Exception e) {
            LogUtil.d("下载出错--" + e.getMessage());
            downloadedFile = null;
            e.printStackTrace();
        } finally {

            if (null != rdAcessFile) {
                try {
                    rdAcessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (downloadedFile != null) {
            Runtime runtime = Runtime.getRuntime();
            String command = "chmod 777 " + downloadedFile.getAbsolutePath();
            try {
                runtime.exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadedFile;
    }

    /**
     * 生成本地文件对象
     *
     * @param context
     * @param image_url
     * @param fileLength
     * @param
     * @param
     * @return
     * @throws
     */
    private static File createFile(final Context context,
                                   final String image_url, final int fileLength) throws Exception {

        File downloadedFile = null;
        String fileName = getFileName(image_url);

        // 判断是否有足够的空间进行存储
        LogUtil.d("mem size: " + MemoryManager.getAvailableInternalMemorySize());
        if (MemoryManager.getAvailableInternalMemorySize() > fileLength) {// 判断内部存储空间是否足够

            downloadedFile = new File(context.getFilesDir(), fileName);

        } else {// 没有足够的空间
            throw new Exception("no enough space");
        }

        return downloadedFile;
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 忽略所有证书 #http://blog.csdn.net/jianglili611/article/details/46290431
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                Log.i(TAG, "checkClientTrusted");
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
                Log.i(TAG, "checkServerTrusted");
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String url) {
        int start = url.lastIndexOf(".");
        String urlMd5Str = MD5Util.mMD5Digest(url);
        return urlMd5Str + url.substring(start);
    }

}
