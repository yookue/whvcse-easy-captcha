package com.github.whvcse.easycaptcha.utils;


import java.awt.*;
import java.io.IOException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.github.whvcse.easycaptcha.base.Captcha;
import com.github.whvcse.easycaptcha.PngCaptcha;


/**
 * 图形验证码工具类
 * <p>
 * Created by 王帆 on 2018-07-27<br/>
 * Modified by David HSing on 2025-03-18
 */
@SuppressWarnings("unused")
public class CaptchaUtil {
    private static final String SESSION_KEY = "captcha";
    private static final int DEFAULT_LEN = 4;  // 默认长度
    private static final int DEFAULT_WIDTH = 130;  // 默认宽度
    private static final int DEFAULT_HEIGHT = 48;  // 默认高度

    /**
     * 输出验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        out(DEFAULT_LEN, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int len, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int width, int height, int len, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        out(width, height, len, null, request, response);
    }

    /**
     * 输出验证码
     *
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(@Nonnull Font font, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_LEN, font, request, response);
    }

    /**
     * 输出验证码
     *
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int len, @Nonnull Font font, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, font, request, response);
    }

    /**
     * 输出验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param len      长度
     * @param font     字体
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(int width, int height, int len, Font font, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        PngCaptcha pngCaptcha = new PngCaptcha(width, height, len);
        if (font != null) {
            pngCaptcha.setFont(font);
        }
        out(pngCaptcha, request, response);
    }

    /**
     * 输出验证码
     *
     * @param captcha  Captcha
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    public static void out(@Nonnull Captcha captcha, @Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws IOException {
        setHeader(response);
        request.getSession().setAttribute(SESSION_KEY, captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }

    /**
     * 验证验证码
     *
     * @param code    用户输入的验证码
     * @param request HttpServletRequest
     * @return 是否正确
     */
    public static boolean verify(String code, HttpServletRequest request) {
        if (code != null) {
            String captcha = (String) request.getSession().getAttribute(SESSION_KEY);
            return code.trim().toLowerCase().equals(captcha);
        }
        return false;
    }

    /**
     * 清除session中的验证码
     *
     * @param request HttpServletRequest
     */
    public static void clear(@Nonnull HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_KEY);
    }

    /**
     * 设置相应头
     *
     * @param response HttpServletResponse
     */
    public static void setHeader(@Nonnull HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
