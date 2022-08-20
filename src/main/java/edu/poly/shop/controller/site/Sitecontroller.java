package edu.poly.shop.controller.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.domain.Product;
import edu.poly.shop.repository.OderDetailRepository;
import edu.poly.shop.repository.ProductRepository;

@Controller
@RequestMapping("/")
public class Sitecontroller {
		
	@Autowired
	ProductRepository product;
	@Autowired
	OderDetailRepository order;
	
	@GetMapping("TrangChu")
	public String index() {
		return "site/site-index";
	}
	@GetMapping("TrangChu/Cart")
	public String cart() {
		return "site/site-index";
	}
	
	@GetMapping("TrangChu/Shop")
	public String shop(Model model) {
		List<Product> list = product.findAll();
		model.addAttribute("list",list);
		float total=0;
		for(OrderDetail x:order.findAll()){
			total += x.getQuantity()*x.getProduct().getUnitPrice();
		}
		model.addAttribute("total",total);
		model.addAttribute("cart",order.findAll());
		return"site/site-orders";
	}
	@GetMapping("TrangChu/Shop/Cart")
	public String cart(Model model,@RequestParam("name")Long id) {
		OrderDetail details = order.findByDetail(id);
		if(details==null){
		Product pro = product.findId(id);
		OrderDetail detail = new OrderDetail();
		detail.setProduct(pro);
		detail.setQuantity(1);		
		order.save(detail);
		}else{
			details.setQuantity(details.getQuantity()+1);		
		    order.save(details);
		}				
		return "redirect:/TrangChu/Shop";
	}
	@GetMapping("TrangChu/CartItem")
	public String itemcart(Model model) {
		model.addAttribute("cart",order.findAll());
		return "site/site-orderdetail";
	}
	@GetMapping("TrangChu/Cart/Remove")
	public String remove(@RequestParam("id")int id){
		order.deleteById(id);
		return "redirect:/TrangChu/Shop";
	}
	
	
	//about
		@GetMapping("TrangChu/about")
		public String about() {
			return "site/site-about";
		}

	//change
	@GetMapping("TrangChu/change")
	public String change() {
		return "site/users/change_password";
	}	
	//forgot
	@GetMapping("TrangChu/forgot")
	public String forgot() {
		return "site/users/forgotpassword";
	}
	//regis
		@GetMapping("TrangChu/regis")
		public String regis() {
			return "site/users/registration";
		}
		//sale
				@GetMapping("TrangChu/sale")
				public String sale() {
					return "site/sale";
				}
	//giay
				@GetMapping("TrangChu/giay")
				public String giay() {
					return "site/giay";
				}	
				
				
				

//chitiet
	@GetMapping("TrangChu/chitietsp")
	public String chitietsp() {
		return "site/chitietsp";
	}	
	//chitiet1
		@GetMapping("TrangChu/chitietsp1")
		public String chitietsp1() {
			return "site/chitietsp1";
		}	
}
















