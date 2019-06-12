package com.ssx.controller;

import com.ssx.tools.QRBarCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: shuaishuai.xiao
 * @date: 2019/6/12 16:45
 * @description: no
 */
@RestController
@RequestMapping("/qrCode")
public class QrCodeController {

    @GetMapping("/create")
    public void getQRCode(@RequestParam("info") String info, HttpServletResponse response) {
        System.out.println("codeContent=" + info);
        try {
            /**
             * 调用工具类生成二维码并输出到输出流中
             */
            QRBarCodeUtil.createCodeToOutputStream(info, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/parse")
    public String parse(@RequestParam("url") String url) {
        try {
            return QRBarCodeUtil.parseQRCodeByUrl(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
