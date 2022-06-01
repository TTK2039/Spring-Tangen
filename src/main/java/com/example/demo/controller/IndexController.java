package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.form.LoginForm;
import com.example.demo.entity.Categories;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class IndexController {

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    ProductService pdService;
	    
	    @Autowired
	    CategoryService categoryService;
	    
	    @Autowired
	    HttpSession session;
	    
	String msg = "";

	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("login") LoginForm form, Model model) {

		return "index";
	}

	@RequestMapping({ "login" })
	public String login(@Validated @ModelAttribute("login") LoginForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
				
		User user = new User(form.getLoginId(), form.getPassword());
		
		user = userService.loginCheck(user);
		if(user != null) {
			session.setAttribute("user", user);
						
			List<Product> list = pdService.findAll();
			
			model.addAttribute("count", list.size());
			
			model.addAttribute("list", list);
			
			return "menu";
		}else {
			model.addAttribute("error", 1);
			return "index";
		}
	}
	
	@RequestMapping({ "menu" })
	public String menu(@ModelAttribute("login") LoginForm form, Model model) {
				
		User user = (User) model.getAttribute("user");
		if (user != null) {
			return "index";
		}
		
		List<Product> list = pdService.findAll();
		
		model.addAttribute("list", list);
		
		return "menu";
		
	}
	
	@RequestMapping({ "detail" })
	public String detail(@ModelAttribute("login") LoginForm form, @RequestParam int id) {

		if(session.getAttribute("user") == null) {
			return "index";
		}
		
		if (id == 0) {
			this.menu(null, null);
		}
		
		Product pd = pdService.findById(id);
				
		session.setAttribute("product", pd);
		
		return "detail";
		
	}
	
	@RequestMapping({ "insert" })
	public String insert() {
		List<Categories> category = categoryService.categoryList();
		
		session.setAttribute("cdList", category);
		
		return "insert";
		
	}
	
	@RequestMapping({ "logout" })
	public String logout(@ModelAttribute("login") LoginForm form,Model model) {
		
		session.invalidate();
	
		return "index";
		
	}
	
	
	//    @RequestMapping(value = "/serch", method = RequestMethod.POST)
	//    public String result(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model) {
	//    	
	//        if (bindingResult.hasErrors()) {
	//            return "top";
	//        }
	//    	
	//    	String name = form.getProduct_name();
	//    	Integer price = form.getPrice();
	//    	
	//    	if(name.equals("") && price == null) {
	//    		List<Product> pd = pdService.findAll();
	//    		
	//    		model.addAttribute("list", pd);
	//            model.addAttribute("msg", "全件検索結果");
	//    	}else if (name.equals("")) {
	//    		List<Product> pd = pdService.findByPrice(price);
	//    		
	//    		model.addAttribute("list", pd);
	//    		model.addAttribute("msg", "値段で検索しました");
	//    		
	//    	}else if (price == null) {
	//    		List<Product> pd = pdService.findByName(name);
	//    		
	//    		model.addAttribute("list", pd);
	//    		model.addAttribute("msg", "名前で検索しました");
	//    	}else {
	//    		List<Product> pd = pdService.findByNamePrice(name, price);
	//    		
	//    		model.addAttribute("list", pd);
	//    		model.addAttribute("msg", "名前と値段のAND検索です");
	//    	}
	//
	//        return "result";
	//    }
	//    
	//    @RequestMapping(value = "/register", method = RequestMethod.POST)
	//    public String resgister(@ModelAttribute("index") IndexForm form, Model model) {
	//        //なんか違う気がする
	//        Product pd = new Product();
	//    	
	//    	pd.setPrice(form.getPrice());
	//        pd.setProduct_name(form.getProduct_name());
	//        
	//        pdService.insert(pd);
	//        
	//        model.addAttribute("msg", "登録されました");
	//        
	//        return "result";
	//    }

}
