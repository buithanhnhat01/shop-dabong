package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.repository.AccountRepository;
import edu.poly.shop.repository.CustomerRepository;
import edu.poly.shop.service.AccountService;

@Controller
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	@Autowired
	CustomerRepository customer;
	@Autowired
	private HttpSession session;
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account", new AdminLoginDto());
		
		return "/admin/accounts/login";
	}
	
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model,
			@Valid @ModelAttribute("account")AdminLoginDto dto,
			BindingResult result){
		if(customer.findByName(dto.getUsername())!=null) {
			return new ModelAndView("redirect:/TrangChu", model);
		}
		if (result.hasErrors()) {//neu co loi xay ra
			return new ModelAndView("/admin/accounts/login", model);
		}
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		if (account == null ) {
			model.addAttribute("message" , "Invalid username or password");
			return new ModelAndView("/admin/accounts/login", model);
		}
		session.setAttribute("username", account.getUsername());
		Object ruri = session.getAttribute("redirect-uri");
		
		if (ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
					
		return new ModelAndView("redirect:/admin/index", model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
