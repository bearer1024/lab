package uk.ac.le.cs.CO3098.cw3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import uk.ac.le.cs.CO3098.cw3.domain.ClassHierarchy;
import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;
import uk.ac.le.cs.CO3098.cw3.service.CLASS_HIERARCHYService;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@Controller
@RequestMapping(value = {"/rest/class"})
public class CLASS_HIERARCHYController{

@Autowired
CLASS_HIERARCHYService hierarchyService;

@RequestMapping(value = {"/create"})
	public @ResponseBody String create(@RequestParam(value = "classname",required = true) String classname,
			@RequestParam(value = "superclass",required = false) String superclass){
		if(!classname.equals(null)){
			if(!superclass.equals(null)){
				hierarchyService.save(new ClassHierarchy(classname,superclass));
			}else{
				hierarchyService.save(new ClassHierarchy(classname));
			}
		return "crated class successfully";
		}
		return "failed to crate class";
	}

@RequestMapping(value = {"/listAllJson"})
public @ResponseBody Object listAllJson(Model model){
    Object o=hierarchyService.findAllClasses();
    return o;
}

@RequestMapping (value="/rest/class/delete?",method = RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam(value = "classname",required = true) String classname ){
		
		return new UMTClass(classname).toString();
}


@RestController
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

  private final ErrorAttributes errorAttributes;

  @Autowired
  public SimpleErrorController(ErrorAttributes errorAttributes) {
    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
    this.errorAttributes = errorAttributes;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping
  public Map<String, Object> error(HttpServletRequest aRequest){
     Map<String, Object> body = getErrorAttributes(aRequest,getTraceParameter(aRequest));
     String trace = (String) body.get("trace");
     if(trace != null){
       String[] lines = trace.split("\n\t");
       body.put("trace", lines);
     }
     return body;
  }

  private boolean getTraceParameter(HttpServletRequest request) {
    String parameter = request.getParameter("trace");
    if (parameter == null) {
        return false;
    }
    return !"false".equals(parameter.toLowerCase());
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }
}
}