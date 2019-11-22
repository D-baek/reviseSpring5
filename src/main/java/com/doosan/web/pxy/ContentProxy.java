package com.doosan.web.pxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

@Component("ctx")
@SessionAttributes({"ctx", "js"})

public class ContentProxy extends Proxy{
	 @Autowired HttpSession session;
	 @Autowired HttpServletRequest request;
	 
	 public void execute() {
		 String ctx = request.getContextPath();
		 session.setAttribute("ctx", ctx);
		 session.setAttribute("js", ctx + "/resources/js/");
	 }
}
