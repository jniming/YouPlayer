package com.yp.youplayer.Rxhttpnet;

import com.yp.youplayer.util.AESUtils;
import com.yp.youplayer.util.LogUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/25.
 */
public class RetrofitUtil {

    public static final String URI = "http://gnsp.yabqq.com:9200/mmgl/";
//        public static final String URI = "http://192.168.1.77:8080/mmgl-web/";
    //社交sdk支付地址
//        public static final String URI = "http://192.168.1.77:8080/mmgl-web/";

    private static Retrofit retrofit;
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static RxApi createApi() {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URI)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = builder.build();
                }
            }
        }
        return retrofit.create(RxApi.class);
    }


    //定义一个String类型转换器
    public static class DeconingConverter implements Converter<ResponseBody, String> {

        public static final DeconingConverter INIS = new DeconingConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            String restr = "";
            try {
                restr = value.string().replace("\"", "");
                LogUtil.d("解密前的字符串-->" + restr);

                restr = AESUtils.decode(restr);

                LogUtil.d("解密前的字符串-->" + restr);

                return restr;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return restr;
        }
    }

    //创建一个转换器工厂来注册转换器,以供retrfig使用
    public static class StringConverterFactory extends Converter.Factory {
        public static StringConverterFactory INIFACR = new StringConverterFactory();

        public static StringConverterFactory create() {
            return INIFACR;
        }

        //目前我们只关心响应之间的转换
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == String.class) {

                return DeconingConverter.INIS;
            }
            return super.responseBodyConverter(type, annotations, retrofit);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            if (type == String.class) {
                return EncodeDingConverter.INIS;
            }
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
    }

    public static class EncodeDingConverter implements Converter<String, RequestBody> {
        public static final EncodeDingConverter INIS = new EncodeDingConverter();


        @Override
        public RequestBody convert(String value) throws IOException {
            value = AESUtils.encode(value);
            return RequestBody.create(null, value);
        }
    }

}
