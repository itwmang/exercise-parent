package com.spring.cloud.zuul.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.framework.utils.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CapchaFilter extends OncePerRequestFilter {

    @Value("${security.capcha}")
    private boolean isCapcha;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isCapcha && (StringUtils.contains(request.getRequestURI(), SecurityConstant.refresh_token_url) ||
                StringUtils.contains(request.getRequestURI(), SecurityConstant.oauth_token_url) ||
                StringUtils.contains(request.getRequestURI(), SecurityConstant.mobile_token_url)
        )) {
            //需要校验 验证码
//            checkCode(request,response,filterChain);
        }else{
            //不需要校验 验证码
            filterChain.doFilter(request,response);
        }
    }
//校验验证码逻辑处理
//    private void checkCode(HttpServletRequest httpServletRequest,
//                           HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException,
//            ServletException {
//        String code = httpServletRequest.getParameter("code");
//        String randomStr = httpServletRequest.getParameter("randomStr");
//        if (StringHelper.isBlank(randomStr)) {
//            randomStr = httpServletRequest.getParameter("mobile");
//        }
//        Object codeObj = redisTemplate.opsForValue().get(
//                SecurityConstant.DEFAULT_CODE_KEY + randomStr);
//
//        if (codeObj == null) { throw new ValidateCodeException("验证码为空或已过期"); }
//        String saveCode = codeObj.toString();
//
//        if (StringHelper.isBlank(code)) {
//            redisTemplate.delete(SecurityConstant.DEFAULT_CODE_KEY + randomStr);
//            throw new ValidateCodeException("验证码的值不能为空");
//        }
//
//        if (StringHelper.isEmpty(saveCode)) {
//            redisTemplate.delete(SecurityConstant.DEFAULT_CODE_KEY + randomStr);
//            throw new ValidateCodeException("验证码已过期或已过期");
//        }
//
//        if (!StringHelper.equals(saveCode, code)) {
//            redisTemplate.delete(SecurityConstant.DEFAULT_CODE_KEY + randomStr);
//            throw new ValidateCodeException("验证码不匹配");
//        }
//
//        if (StringHelper.equals(code, saveCode)) {
//            redisTemplate.delete(SecurityConstant.DEFAULT_CODE_KEY + randomStr);
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
//    }
}
