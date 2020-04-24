package com.javaweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 瑙ｅ喅鍏ㄧ珯涔辩爜闂锛屽鐞嗘墍鏈夌殑璇锋眰
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //灏嗙埗鎺ュ彛杞负瀛愭帴鍙�
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //鑾峰彇璇锋眰鏂规硶
        String method = request.getMethod();
        //瑙ｅ喅post璇锋眰涓枃鏁版嵁涔辩爜闂
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //澶勭悊鍝嶅簲涔辩爜
        response.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
