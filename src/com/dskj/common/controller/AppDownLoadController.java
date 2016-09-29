package com.dskj.common.controller;

import com.dskj.base.Base;
import com.dskj.common.constant.ConstantSite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2016/8/29.
 */
@Controller
public class AppDownLoadController extends Base {

    @RequestMapping(value = "/dl.do", method = RequestMethod.GET)
    public void downLoadApp(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        String userAgent = request.getHeader("user-agent");
        logger.info(userAgent);
        String mobileDevice = isMobileDevice(userAgent);
        goDownLoad(mobileDevice, response);
    }

    private void goDownLoad(String mobileDevice, HttpServletResponse response) throws ServletException, IOException {
        if ("microMessenger".equals(mobileDevice)) {
            logger.info(mobileDevice);
            response.sendRedirect(ConstantSite.DOMAIN);
            return;
        }
        if (mobileDevice != null && mobileDevice.equals("android")) {
            response.sendRedirect(ConstantSite.ANDROID);
        } else if (mobileDevice != null && mobileDevice.equals("mac os")) {
            response.sendRedirect(ConstantSite.IOS);
        } else {
            response.sendRedirect(ConstantSite.DOMAIN);
        }

    }

    /**
     * android : 所有android设备
     * mac os : iphone ipad
     * windows phone:Nokia等windows系统的手机
     */
    private String isMobileDevice(String requestHeader) {
        String[] deviceArray = new String[]{"micromessenger", "android", "mac os", "windows phone"};
        if (requestHeader == null)
            return null;
        requestHeader = requestHeader.toLowerCase();
        for (int i = 0; i < deviceArray.length; i++) {
            if (requestHeader.indexOf(deviceArray[i]) > 0) {
                return deviceArray[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(6 ^ 3);
    }

}
