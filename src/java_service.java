package com.example.helloworld;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.*;

@SpringBootApplication
public class java_service {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}


@RestController
class Hello {

    @GetMapping("/")
    @ResponseBody

    public String index(@RequestParam Map<String,String> allParams) {

        String con1 = allParams.con;
        String con2 = "{\"data\":"+con1+"}";
        String sec = allParams.sec;
        String id = allParams.iv;

        try {

//            String content1 = AESHelper.Encrypt(con2, sec.getBytes("utf8"), id.getBytes("utf8"));
//            return content1;

            String content = con2;
            byte[] key = sec.getBytes("utf8");
            byte[] ivS = id.getBytes("utf8");
            byte[] raw = key;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            // System.out.println("key:"+key);
            //         System.out.println("raw:"+raw);
            // for (byte i : raw) {
            //     System.out.println(i);
            //   }
            // System.out.println("1end first array copy");


            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(ivS);
            // System.out.println("iv:"+iv.getIV());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf8"));
            byte[] newB = new byte[ivS.length+encrypted.length];
            System.arraycopy(ivS,0,newB,0,ivS.length);
            System.arraycopy(encrypted,0,newB,ivS.length,encrypted.length);
            return Base64.getEncoder().encodeToString(newB);//Base64.encode(newB)


        } catch (Exception e) {

            return "ERROR : "+e.getMessage() ;
        }

        // return con2;
    }
    // public String index(@RequestParam("iv"), @RequestParam("con")) {
    //     String con1 = con;
    //     String con2 = "{\"data\":"+con1+"}";

    //     return "con: " + con2;
    // }

///////////// start
private static final String HMAC_SHA1 = "HmacSHA1";

public static String getSHA1Signature(byte[] api_key, byte[] secret_key)
        throws InvalidKeyException, NoSuchAlgorithmException {

    SecretKeySpec signingKey = new SecretKeySpec(secret_key, HMAC_SHA1);
    Mac mac = Mac.getInstance(HMAC_SHA1);
    mac.init(signingKey);
    byte[] rawHmac = mac.doFinal(api_key);
    return bytesToHexString(rawHmac);
    // System.out.println(rawHmac.toString());
    // return rawHmac.toString();
}


    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    @GetMapping("/signal/")
    @ResponseBody

    public String index2(@RequestParam Map<String,String> allParams) {

        String api_key = allParams.api;
        String sec_key = allParams.sec;

        try {
            byte[] secretKey = sec_key.getBytes();
            byte[] apikey = api_key.getBytes();

            String sha1 = getSHA1Signature(apikey, secretKey);
          return sha1;


        } catch (Exception e) {

            return "ERROR : "+e.getMessage() ;
        }

        // return con2;
    }
    // public String index(@RequestParam("iv"), @RequestParam("con")) {
    //     String con1 = con;
    //     String con2 = "{\"data\":"+con1+"}";

    //     return "con: " + con2;
    // }

    ///////////////////////////////end

    @GetMapping(path = "/para/{iv}")
    public String getPara( @PathVariable(name="iv") String id ){

        return "ERROR : Missing Variable "+id;
    }

    @GetMapping(path = "/para/{iv}/{con}")
    public String getPara2( @PathVariable(name="iv") String id ,@PathVariable(name="con") String con){

        return "ERROR : Missing Variable "+id+con;
    }

    @GetMapping(path = "/para/{iv}/{con}/{sec}")
    public String getPara3( @PathVariable(name="iv") String id ,
                            @PathVariable(name="con") String con,
                            @PathVariable(name="sec") String sec)throws Exception {
        String con1 = con;
        String con2 = "{\"data\":"+con1+"}";

        try {

//            String content1 = AESHelper.Encrypt(con2, sec.getBytes("utf8"), id.getBytes("utf8"));
//            return content1;

            String content = con2;
            byte[] key = sec.getBytes("utf8");
            byte[] ivS = id.getBytes("utf8");
            byte[] raw = key;
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            // System.out.println("key:"+key);
            //         System.out.println("raw:"+raw);
            // for (byte i : raw) {
            //     System.out.println(i);
            //   }
            // System.out.println("1end first array copy");


            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            IvParameterSpec iv = new IvParameterSpec(ivS);
            // System.out.println("iv:"+iv.getIV());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf8"));
            byte[] newB = new byte[ivS.length+encrypted.length];
            System.arraycopy(ivS,0,newB,0,ivS.length);
            System.arraycopy(encrypted,0,newB,ivS.length,encrypted.length);
            return Base64.getEncoder().encodeToString(newB);//Base64.encode(newB)


        } catch (Exception e) {

            return "ERROR : "+e.getMessage() ;
        }
