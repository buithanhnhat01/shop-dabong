package edu.poly.shop.controller.site;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.repository.CustomerRepository;
import edu.poly.shop.service.AccountService;

@Controller
@RequestMapping("/")
public class SiteLoginController {
	@Autowired
	private AccountService accountService;
	@Autowired
	CustomerRepository customerRepository;
	
	

//dk
	@GetMapping("TrangChu/sitedk")
	public String sitedk(Model model) {
		model.addAttribute("Customer",new Customer());
		return "/site/site-dk";
	}	
	//thêm
	/*
	 * @GetMapping("sitedk") public String add(Model model) {
	 * model.addAttribute("account", new AccountDto());
	 * 
	 * return "/admin/accounts/login"; //trả về }
	 */
	@PostMapping("admin/accounts")
	public String dk(@ModelAttribute("Customer")Customer customer) {
		System.out.println(customer.getName());
		customerRepository.save(customer);
		return "redirect:/alogin";
	}
	
	/*
	 * //lưu
	 * 
	 * @PostMapping("save") public ModelAndView saveOrUpdate(ModelMap model,
	 * 
	 * @Valid @ModelAttribute("account") AccountDto dto, BindingResult result ){
	 * 
	 * if(result.hasErrors()) {
	 * 
	 * return new ModelAndView("/site/site-dk"); }
	 * 
	 * Account entity = new Account(); BeanUtils.copyProperties(dto, entity);
	 * 
	 * accountService.save(entity);
	 * 
	 * model.addAttribute("message","Account is saved");
	 * 
	 * return new ModelAndView("forward:/alogin", model); }
	 */
	
}
