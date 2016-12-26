package uk.ac.le.cs.CO3098.cw3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import uk.ac.le.cs.CO3098.cw3.domain.UMTClass;
import uk.ac.le.cs.CO3098.cw3.service.HierarchyService;

@RestController
@RequestMapping(value = {"/rest/class"})
public class HierarchyController {
@Autowired
HierarchyService hierarchyService;

@RequestMapping(value = {"/create"},method = RequestMethod.GET)
	public @ResponseBody 
	String getClassHierarchy(@RequestParam("className") String className){
		System.out.println("className:"+className);
		return new UMTClass(className).toString();
	}
}