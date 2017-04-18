package com.yp.youplayer.util;


import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class AESUtils {

    public final static String pwd = "ieNAanODF7651";
    public final static String key = "CZqkPj4iu29DE&oWFbjdG^s1IRv%A0ivHJLpB4'ehcvMgXV'5Nr8-enO#QxtSyThfKU@Y_";
    public final static int[] position = {3, 35, 57, 65, 39, 43, 46, 20, 52, 9, 26, 21, 44};
    public final static int[] position2 = {59, 35, 28, 28, 13, 52, 22, 52, 43, 60, 52, 3, 35, 57};
    public final static int[] position3 = {5, 30, 6, 22, 29, 9, 13, 56, 10, 38, 5, 39, 40, 6, 64, 26};

    @SuppressLint("TrulyRandom")
    public static String encode(String in) {
        String hex = "";
        try {
            byte[] bytIn = in.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(getPassword().getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] bytOut = cipher.doFinal(bytIn);
            hex = byte2hexString(bytOut);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hex;
    }

    public static String decode(String hex) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        String rr = "";

        byte[] bytIn = hex2Bin(hex);

        SecretKeySpec skeySpec = new SecretKeySpec(getPassword().getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] bytOut = cipher.doFinal(bytIn);
        try {
            rr = new String(bytOut, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return rr;
    }

    private static byte[] hex2Bin(String src) {
        if (src.length() < 1)
            return null;
        byte[] encrypted = new byte[src.length() / 2];
        for (int i = 0; i < src.length() / 2; i++) {
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
            encrypted[i] = (byte) (high * 16 + low);
        }
        return encrypted;
    }

    private static String byte2hexString(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            strbuf.append(Integer.toString((buf[i] >> 4) & 0xf, 16) + Integer.toString(buf[i] & 0xf, 16));
        }

        return strbuf.toString();
    }

    public static String getPassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < position3.length; i++) {
            builder.append(key.charAt(position3[i]));
        }
        LogUtil.d("加密密钥-->" + builder.toString());
        return builder.toString();
    }
}
