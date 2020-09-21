package com.xidian.iot.dataapi.exception.handler;

import com.xidian.iot.common.util.constants.ExceptionEnum;
import com.xidian.iot.dataapi.controller.res.HttpResult;
import com.xidian.iot.common.util.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 自定义全局异常处理器
 * @author: Hansey
 * @date: 2020-09-06 16:20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String FORMAT = "URI: {}, Method: {}, Parameter: {}, DebugMessage: {}";
    /**
     * 未处理的异常,一律报系统错误
     */
    private static final int SYSTEM_ERROR = -1;
    /**
     * 参数错误
     */
    private static final int PARAM_ERROR = -3;

    private static final String STR_FORMAT = "发生未捕获异常! URI: %s, Method: %s, RemoteHost: %s, Error: %s";

    /**
    * 业务异常处理
    * */
    @ExceptionHandler(BusinessException.class)
    public HttpResult<String> handlerServerException(HttpServletRequest request, HttpServletResponse response,
                                                     BusinessException e) {
        response.setStatus(200);// 总是返回200
        setResponse(response);
        log.error("业务异常: code: {}, message: {}", e.getCode(), e.getMessage(), e);
        String paramStr = parameterMap2String(request.getParameterMap());
        log.error(FORMAT, request.getRequestURI(), request.getMethod(), paramStr, e.getBody());
        return build(e.getCode(), e.getMessage());
    }


    /**
     * 所有未捕获的的异常,一律报系统错误
     */
    @ExceptionHandler(Exception.class)
    public HttpResult<String> handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(200);
        setResponse(response);

        String errorMessage = String.format(STR_FORMAT, request.getRequestURI(), request.getMethod(),
                request.getRemoteHost(), e);
        log.error(errorMessage, e);
        return build(SYSTEM_ERROR, "系统错误");
    }

    /**
     * 消息不可读,一般是消息参数错误(如非法字符)引起,提示参数错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpResult<String> handlerHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(200);
        setResponse(response);
        log.error("HttpMessageNotReadableException异常产生! URI: {}, Method: {}, RemoteHost: {}",
                request.getRequestURI(),
                request.getMethod(), request.getRemoteHost(), e);
        return build(PARAM_ERROR, "参数错误,请检查");
    }

    /**
     * post请求，传入对象校验失败时的异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult<String> handlerException(HttpServletRequest request, HttpServletResponse response,
                                               MethodArgumentNotValidException e) {
        response.setStatus(200);
        setResponse(response);
        String message = "参数异常" + getBindResultFirstErrorMessage(e.getBindingResult());

        log.error("MethodArgumentNotValidException异常! URI: {}, Method: {}, RemoteHost: {}  ",
                request.getRequestURI(),
                request.getMethod(), request.getRemoteHost());

        return build(PARAM_ERROR, message);
    }

    /**
     * get请求，接收参数为对象时抛出的异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public HttpResult<String> handlerException(HttpServletRequest request, HttpServletResponse response,
                                               BindException e) {
        response.setStatus(200);
        setResponse(response);
        String message = "参数异常" + getBindResultFirstErrorMessage(e.getBindingResult());
        log.error("BindException异常! URI: {}, Method: {}, RemoteHost: {}", request.getRequestURI(),
                request.getMethod(), request.getRemoteHost(), e);
        return build(PARAM_ERROR, message);
    }

    /**
     * get请求，方法参数直接接收时抛出的异常
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public HttpResult<String> handlerException(HttpServletRequest request, HttpServletResponse response,
                                               ConstraintViolationException e) {
        response.setStatus(200);
        setResponse(response);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        violations.stream().findFirst().ifPresent(violation -> strBuilder
                .append("[" + violation.getPropertyPath().toString() + "]" + violation.getMessage()));

        String message = "参数异常" + strBuilder.toString();
        log.error("ConstraintViolationException异常! URI: {}, Method: {}, RemoteHost: {}", request.getRequestURI(),
                request.getMethod(), request.getRemoteHost(), e);

        return build(PARAM_ERROR, message);
    }

    private void setResponse(final HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
    }

    private String parameterMap2String(Map<String, String[]> map) {
        if (CollectionUtils.isEmpty(map)) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            sb.append(entry.getKey()).append('=').append(Arrays.toString(entry.getValue())).append(',');
        }
        sb.deleteCharAt(sb.length() - 1).append('}');
        return sb.toString();
    }

    private HttpResult<String> build(int code, String message) {
        HttpResult<String> r = new HttpResult<>();
        r.setCode(code);
        r.setMessage(message);
        return HttpResult.oK().code(code).message(message);
    }

    /**
     * 校验错误信息生成
     * @param bindingResult
     * @return
     */
    private String getBindResultFirstErrorMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> "[" + fieldError.getField() + "]" + fieldError.getDefaultMessage()).findFirst()
                .orElse("");
    }
}
