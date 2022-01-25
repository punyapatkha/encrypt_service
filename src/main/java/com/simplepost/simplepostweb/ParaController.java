package com.simplepost.simplepostweb;

import org.springframework.web.bind.annotation.*;

import java.io.*;
//@RequestMapping("/para/{con}/{sec}/{iv}")
//@RequestMapping("/para/{iv}")

@RestController
public class ParaController {

    @GetMapping(path = "/para/{iv}")
    public String getPara( @PathVariable(name="iv") String id ){

        return "Missing Variable :"+id;
    }

    @GetMapping(path = "/para/{iv}/{con}")
    public String getPara2( @PathVariable(name="iv") String id ,@PathVariable(name="con") String con){

        return "Missing Variable :"+id+con;
    }

    @GetMapping(path = "/para/{iv}/{con}/{sec}")
    public String getPara3( @PathVariable(name="iv") String id ,
                            @PathVariable(name="con") String con,
                            @PathVariable(name="sec") String sec)throws Exception {
        String con1 = con;
        String con2 = "{\"data\":"+con1+"}";

        try {
            String content1 = AESHelper.Encrypt(con2, sec.getBytes("utf8"), id.getBytes("utf8"));
            return content1;
                } catch (Exception e) {

            return "ERROR : "+e.getMessage() ;
        }

    }
//    @PostMapping()
//    public String PostUsers(){
//        return "HTTP POST request was sent";
//    }

}
