package uk.ac.le.cs.CO3098.cw3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;
import uk.ac.le.cs.CO3098.cw3.service.UMTClassService;

@Controller
@RequestMapping(value = {"/rest/class"})
public class UMTClassController {
@Autowired
UMTClassService umtclassService;

@RequestMapping(value = {"/create"})
	public ModelAndView CreateClass(Model model){
	System.out.println(umtclassService);
}
}
