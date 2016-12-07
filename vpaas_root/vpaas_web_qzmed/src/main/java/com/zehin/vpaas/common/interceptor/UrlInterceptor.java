/*
 * @(#)UrlInterceptor.java 
 * 
 * Copyright 2016 by 青岛众恒信息科技股份有限公司 . 
 * All rights reserved.
 *
 */
package com.zehin.vpaas.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 *	日期		:	2016年12月1日<br>
 *	作者		:	liuxin<br>
 *	项目		:	vpaas_main<br>
 *	功能		:	登录拦截器<br>
 */
public class UrlInterceptor extends HandlerInterceptorAdapter{
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	Object loginUser = session.getAttribute("userInfo");
    	if(loginUser == null){
    		boolean isAjaxRequest = isAjax(request);
    		if(isAjaxRequest){
    			response.setCharacterEncoding("UTF-8");
    			response.sendError(408, "您已经太长时间没有操作,请刷新页面");
    		}else{
    			response.sendRedirect(request.getContextPath() + "/login");
    		}
    		return false;
    	}
		return true;
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	
    } 
    
    /**
     * 判断是否是ajax请求
     * Description : 
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request){
    	return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest"
				.equals(request.getHeader("X-Requested-With").toString()));
    }
}
