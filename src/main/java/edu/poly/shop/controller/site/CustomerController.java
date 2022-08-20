package edu.poly.shop.controller.site;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.repository.CustomerRepository;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.StorageService;

@Controller //bộ điều khiển
@RequestMapping("/")
public class CustomerController {
	@Autowired
	AccountService accountService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerRepository customer;
	 
	//lấy dữ liệu của category chuyển sang product
	@ModelAttribute("accounts")
	public List<AccountDto> getAccounts() {
		return accountService.findAll().stream().map(item->{
			AccountDto dto = new AccountDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	//thêm
	@GetMapping("add")
	public String add(Model model) {
		CustomerDto dto =  new CustomerDto();
		dto.setIsEdit(false);
		model.addAttribute("customer", dto);
		
		return "site/users/editProfileInfo"; //trả về
	}

    //LISTcutomer
	@GetMapping("site/customers/cus")
	public String customer1(ModelMap model) {
		
		  List<Customer> list = customer.findAll();
		  
		  model.addAttribute("customers", list);
		
		return "site/customer/list";
	}
	
	//thongtinuser
		@GetMapping("TrangChu/info")
		public String info() {
			return "site/users/editprofileInfo";
		}

	
}
/*
 * //sửa
 * 
 * @GetMapping("edit/{productId}") public ModelAndView edit(ModelMap
 * model,@PathVariable("productId") Long productId) {
 * 
 * Optional<Product> opt = productService.findById(productId);//1 ProductDto dto
 * = new ProductDto();//2
 * 
 * if(opt.isPresent()) { Product entity = opt.get();//3.1
 * 
 * BeanUtils.copyProperties(entity, dto);//3.2
 * 
 * dto.setCategoryId(entity.getCategory().getCategoryId());//9
 * dto.setIsEdit(true);//7
 * 
 * model.addAttribute("product", dto);//8
 * 
 * return new ModelAndView("admin/products/addOrEdit",model);//4 }
 * 
 * model.addAttribute("message","Product is not existed");
 * 
 * return new ModelAndView ("forward:/admin/products",model);//5 } //đọc thông
 * tin hình
 * 
 * @GetMapping("/images/{filename:.+}")
 * 
 * @ResponseBody public ResponseEntity<Resource> serveFile(@PathVariable String
 * filename){ Resource file = storageService.loadAsResource(filename);
 * 
 * return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
 * "attachment; filename=\"" + file.getFilename() + "\"").body(file); }
 * 
 * 
 * //xóa
 * 
 * @GetMapping("delete/{productId}") public ModelAndView delete(ModelMap
 * model, @PathVariable("productId") Long productId) throws IOException {
 * 
 * Optional<Product> opt = productService.findById(productId); //kiểm tra thông
 * tin hình ảnh if(opt.isPresent()) {
 * if(!StringUtils.isEmpty(opt.get().getImage())) {
 * storageService.delete(opt.get().getImage()); }
 * productService.delete(opt.get());
 * 
 * model.addAttribute("message","Product is deleted!"); }else {
 * 
 * model.addAttribute("message","Product is not Found!");
 * 
 * } return new ModelAndView("forward:/admin/products",model) ; }
 * 
 * //lưu
 * 
 * @PostMapping("saveOrUpdate") public ModelAndView saveOrUpdate(ModelMap model,
 * 
 * @Valid @ModelAttribute("product") ProductDto dto, BindingResult result ){
 * 
 * if(result.hasErrors()) {
 * 
 * return new ModelAndView("admin/products/addOrEdit"); }
 * 
 * Product entity = new Product(); BeanUtils.copyProperties(dto, entity);
 * 
 * Category category = new Category();
 * category.setCategoryId(dto.getCategoryId()); entity.setCategory(category);
 * 
 * if(!dto.getImageFile().isEmpty()) { UUID uuid = UUID.randomUUID(); String
 * uuString = uuid.toString();
 * 
 * entity.setImage(storageService.getStoredFilename(dto.getImageFile(),
 * uuString)); storageService.store(dto.getImageFile(), entity.getImage()); }
 * 
 * 
 * productService.save(entity);
 * 
 * model.addAttribute("message","Product is saved");
 * 
 * return new ModelAndView("forward:/admin/products", model); }
 * 
 * //danh sách đã lưu
 * 
 * @RequestMapping("") public String list(ModelMap model) { List<Product> list =
 * productService.findAll();
 * 
 * model.addAttribute("products", list);
 * 
 * return "admin/products/list"; }
 * 
 * //tiềm kiếm
 * 
 * @GetMapping("search") public String search(ModelMap model, @RequestParam(name
 * = "name" ,required = false)String name) {
 * 
 * List<Category> list = null;
 * 
 * if(StringUtils.hasText(name)) { list =
 * categoryService.findByNameContaining(name); }else { list =
 * categoryService.findAll(); }
 * 
 * model.addAttribute("products", list);
 * 
 * return "admin/products/search"; }
 * 
 * @GetMapping("searchpaginated") public String search(ModelMap model,
 * 
 * @RequestParam(name = "name" ,required = false)String name,
 * 
 * @RequestParam("page") Optional<Integer> page,
 * 
 * @RequestParam("size") Optional<Integer> size) {
 * 
 * int currentPage = page.orElse(1); int pageSize = size.orElse(5);
 * 
 * Pageable pageable = PageRequest.of(currentPage -1, pageSize,Sort.by("name"));
 * Page<Category> resultPage = null;
 * 
 * 
 * if(StringUtils.hasText(name)) { resultPage =
 * categoryService.findByNameContaining(name, pageable);
 * model.addAttribute("name",name); }else { resultPage =
 * categoryService.findAll(pageable); }
 * 
 * int totalPages = resultPage.getTotalPages(); if(totalPages > 0) { int start =
 * Math.max(1,currentPage - 2); int end = Math.min(currentPage + 2, totalPages);
 * 
 * if(totalPages > 5) { if(end == totalPages) start = end - 5; else if (start ==
 * 1) end = start +5; } List<Integer> pageNumbers = IntStream.rangeClosed(start,
 * end) .boxed() .collect(Collectors.toList());
 * model.addAttribute("pageNumbers",pageNumbers);
 * 
 * }
 * 
 * model.addAttribute("categoryPage", resultPage);
 * 
 * return "admin/products/searchpaginated"; }
 * 
 * }
 * 
 * 
 */


















