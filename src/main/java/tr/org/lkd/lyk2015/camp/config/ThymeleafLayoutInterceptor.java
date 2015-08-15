package tr.org.lkd.lyk2015.camp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter 
{
	static final String MAIN_LAYOUT="layout/main";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		String originalViewName = modelAndView.getViewName();
		//eğer redirect ve forward ile başlıyorsa normal flowuna devam etsin
		//interceptor işlem yapmasın
		if(isRedirectOrForward(originalViewName))
		{
			return;
		}
		
		modelAndView.setViewName(MAIN_LAYOUT);
		modelAndView.addObject("layout_main", originalViewName);	
	}

	private boolean isRedirectOrForward(String originalViewName) {
		return originalViewName.startsWith("redirect:") || originalViewName.startsWith("forward:");
	}
	
	

}
