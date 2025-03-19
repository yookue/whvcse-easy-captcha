package io.github.whvcse.easycaptcha.servlet;


import java.io.IOException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.github.whvcse.easycaptcha.utils.CaptchaUtil;


/**
 * 验证码 servlet
 *
 * Created by 王帆 on 2018-07-27
 * Modified by David HSing on 2025-03-18
 */
@SuppressWarnings("unused")
public class CaptchaServlet extends HttpServlet {
    public void doGet(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws ServletException, IOException {
        CaptchaUtil.out(request, response);
    }

    public void doPost(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
