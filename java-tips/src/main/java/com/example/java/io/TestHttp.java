package com.example.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: 封装user-agent和cookie用来访问特殊网站，从而获取节假日的信息
 * @user: yang
 * @Time: 2020/5/8  15:08
 */
public class TestHttp {


    public static String get(String date, String cookies) {
        String value = "";
        URL url = null;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            url = new URL("http://timor.tech/api/holiday/info/" + date.toString().trim());
            System.out.println("http://timor.tech/api/holiday/info/" + date.toString().trim());
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//            //允许写出
//            uc.setDoOutput(true);
//            //允许读入
//            uc.setDoInput(true);
            //设置请求方式
            uc.setRequestMethod("GET");
//            uc.setUseCaches(false);
//            uc.setConnectTimeout(60 * 1000);
//            uc.setReadTimeout(60 * 1000);
            //设置请求头
            //  uc.setRequestProperty("Charsert", "UTF-8");
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");//设置参数类型是json格式
            //  uc.setRequestProperty("Connection", "Keep-Alive");
            // uc.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            //连接网络。请求行，请求头的设置必须放在网络连接前
            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");//（主要是这一句）
            uc.setRequestProperty("Cookie", cookies);
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
            //  user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        String cookies = "__cfduid=d7ce90fe0c0628f0c9f3c3a0baad73ca81588909885; cf_chal_retry_c=1; __yjsv3_shitong=1.0_7_98da957bfbb2178450303f7e813834ed3c35_300_1588920454919_171.113.105.173_ec90fe56; cf_clearance=2b37ca3997c5810791305bf26353177eb00b466b-1588920456-0-150";
        System.out.println(get("2020-05-01", cookies));
    }

}
