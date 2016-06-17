//package com.mlick.demo.retrofit;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Type;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//
///**
// * Created by lxx on 2016/6/17 10:00
// */
//final class StringConverterFactory implements Converter<String>.Factory {
//    private StringConverterFactory() {
//    }
//
//    public static StringConverterFactory create() {
//        return new StringConverterFactory();
//    }
//
//    @Override
//    public Converter<String> get(Type type) {
//        Class<?> cls = (Class<?>) type;
//        if (String.class.isAssignableFrom(cls)) {
//            return new StringConverter();
//        }
//        return null;
//    }
//
//    private static class StringConverter implements Converter<String> {
//        private static final MediaType PLAIN_TEXT = MediaType.parse("text/plain; charset=UTF-8");
//
//        @Override
//        public String fromBody(ResponseBody body) throws IOException {
//            return new String(body.bytes());
//        }
//
//        @Override
//        public RequestBody toBody(String value) {
//            return RequestBody.create(PLAIN_TEXT, convertToBytes(value));
//        }
//
//        private static byte[] convertToBytes(String string) {
//            try {
//                return string.getBytes("UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}