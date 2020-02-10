package xyz.ther.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorController extends BasicErrorController {

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

//    自定义错误页，需要模板引擎支持
//    @Override
//    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
//        HttpStatus status = getStatus(request);
//        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        model.put("customMsg", "出错啦!");
//        ModelAndView modelAndView = new ModelAndView("myErrorPage", model, status);
//        return modelAndView;
//    }

    /**
     * 自定义 JSON 返回
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        body.put("customMsg", "自定义错误信息，出错啦!");
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }
}