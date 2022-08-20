package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Account;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;

@Controller //bộ điều khiển
@RequestMapping("admin/accounts")
public class AccountController {
	@Autowired
	AccountService accountService; 
	 
	
	//thêm
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());
		
		return "admin/accounts/addOrEdit"; //trả về
	}
	//lưu
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap  model,
			@Valid @ModelAttribute("account") AccountDto dto, BindingResult result  ){
		
		if(result.hasErrors()) {
			
			return new ModelAndView("admin/accounts/addOrEdit");
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		
		accountService.save(entity);
		
		model.addAttribute("message","Account is saved");
		
		return new ModelAndView("forward:/admin/accounts", model);
	}
	//danh sách đã lưu
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Account> list =  accountService.findAll();//trả về các account hiện có
		
		model.addAttribute("accounts", list);
		
		return "admin/accounts/list";
	}
	//sửa
	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model,@PathVariable("username") String username) {
		
		Optional<Account> opt = accountService.findById(username);//1
		AccountDto dto = new AccountDto();//2
		
		if(opt.isPresent()) {
			Account entity = opt.get();//3.1
			
			BeanUtils.copyProperties(entity, dto);//3.2
			dto.setIsEdit(true);//7
			
			dto.setPassword("");
			model.addAttribute("account", dto);//8
			
			return new ModelAndView("admin/accounts/addOrEdit",model);//4 
		}
		
		model.addAttribute("message","Categoy is not existed");
		
		return new ModelAndView ("forward:/admin/accounts",model);//5
	}
	
	//xóa
	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
		
		accountService.deleteById(username);
		
		model.addAttribute("message","Account is deleted!");
		
		return new ModelAndView("forward:/admin/accounts",model) ;
	}

	



//	//tiềm kiếm
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name" ,required = false)String name) {
//		
//		List<Account> list = null;
//		
//		if(StringUtils.hasText(name)) {
//			list = categoryService.findByNameContaining(name);
//		}else {
//			list = categoryService.findAll();
//		}
//		
//		model.addAttribute("accounts", list);
//		
//		return "admin/accounts/search";
//	}
//	
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model, 
//			@RequestParam(name = "name" ,required = false)String name,
//			@RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//		
//		Pageable pageable = PageRequest.of(currentPage -1, pageSize,Sort.by("name"));
//		Page<Account> resultPage = null;
//		
//		
//		if(StringUtils.hasText(name)) {
//			resultPage = categoryService.findByNameContaining(name, pageable);
//			model.addAttribute("name",name);
//		}else {
//			resultPage = categoryService.findAll(pageable);
//		}
//		
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages > 0) {
//			int start = Math.max(1,currentPage - 2);
//			int end = Math.min(currentPage + 2, totalPages);	
//			
//		 if(totalPages > 5) {
//			if(end == totalPages) start = end - 5; 
//			else if (start == 1) end = start +5;	
//		 }	
//		 List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
//				 .boxed()
//				 .collect(Collectors.toList());
//		 model.addAttribute("pageNumbers",pageNumbers);
//				
//		}
//		
//		model.addAttribute("categoryPage", resultPage);
//		
//		return "admin/accounts/searchpaginated";
//	}
}





















