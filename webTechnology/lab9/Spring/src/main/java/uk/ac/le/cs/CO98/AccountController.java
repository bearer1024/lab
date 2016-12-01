package uk.ac.le.cs.CO98;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import uk.ac.le.cs.CO98.Account;
import uk.ac.le.cs.CO98.AccountService;

@Controller
@RequestMapping(value = {"/account"})
public class AccountController {
	  @Autowired
	  AccountService as;
	
	  @RequestMapping(value = {"/listAll"})
	    public ModelAndView listAll(Model model){
	        System.out.println(as.findAllAccounts());
	        return new ModelAndView("listAll","accounts",as.findAllAccounts());
	    }  
	  @RequestMapping(value = {"/listAllJson"})
	    public @ResponseBody Object listAllJson(Model model){
	        Object o=as.findAllAccounts();
	        return o;
	    }
	    @RequestMapping(value = "/create")
	    public ModelAndView create(){	
	    	  return new ModelAndView("create");    
	    } 
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ModelAndView save(Account a){
	        as.save(a); 
	        return new ModelAndView("redirect:listAll");

	    }
	    @RequestMapping(value = "/edit/{id}")
	    public ModelAndView edit(@PathVariable Integer id){
	        return new ModelAndView("edit","account",as.findById(id));
	    }  
	    @RequestMapping(value = "/delete/{id}")
	    public ModelAndView delete(@PathVariable Integer id){
	        as.deleteById(id);
	        return new ModelAndView("redirect:../listAll");
	    }
	    @RequestMapping(value = "/update",method = RequestMethod.POST)
	    public ModelAndView update(Account acc){
	        as.save(acc);
	        return new ModelAndView("redirect:listAll");
	    }

}
