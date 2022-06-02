package com.example.demo.controller;

import java.sql.Timestamp;

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
import com.example.demo.controller.form.RegisterForm;
import com.example.demo.controller.form.UpdateForm;
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
    
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    
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
				
		User user = userService.loginCheck(form.getLoginCheck());
		if(user != null) {
			session.setAttribute("user", user);
			
			model.addAttribute("list", pdService.findAll());
						
			//カテゴリ一覧の項目
			session.setAttribute("cdList", categoryService.categoryList());
			
			return "menu";
		}else {
			model.addAttribute("error", 1);
			return "index";
		}
	}
	
	@RequestMapping({ "menu" })
	public String menu(Model model) {
				
		model.addAttribute("list",pdService.findAll());
		
		return "menu";
		
	}
	
	@RequestMapping({ "detail" })
	public String detail(@RequestParam int id) {
		
		session.setAttribute("product", pdService.findById(id));
		
		return "detail";
		
	}
	
	@RequestMapping({ "insert" })
	public String insert(@ModelAttribute("insert") RegisterForm form, BindingResult bindingResult,Model model) {
		//セレクトボタンの項目

				
		return "insert";
		
	}
	
	@RequestMapping({ "register" })
	public String register(@Validated @ModelAttribute("insert") RegisterForm form, BindingResult bindingResult,Model model) {		
    	
        if (bindingResult.hasErrors()) {
            return "insert";
        }
		
		if (pdService.register(form.getAll()) == 1) {
			model.addAttribute("list",pdService.findAll());
			model.addAttribute("msg", "登録完了");
			return "menu";
		}else {
			model.addAttribute("msg", "登録失敗");
		}
		return "insert";		
	}	
	
	@RequestMapping({ "delete" })
	public String delete(@RequestParam int id,Model model) {
		//セレクトボタンの項目		
		if (pdService.delete(id) == 1) {			
			model.addAttribute("list",pdService.findAll());
			model.addAttribute("msg", "削除完了");
		}else {
			model.addAttribute("msg", "削除失敗");
			return "detail";
		}
		
		return "menu";
		
	}
	
	@RequestMapping({ "update" })
	public String update(@ModelAttribute("update") UpdateForm form,@RequestParam int id,Model model) {
				
		return "update";
		
	}
	
	@RequestMapping({"serch"})
	public String serch(@RequestParam String key,Model model) {
		
		model.addAttribute("list", pdService.findByKey(key));
		
		return "menu";
	}
	
	
	@RequestMapping({ "logout" })
	public String logout(@ModelAttribute("login") LoginForm form,Model model) {
		
		session.invalidate();
	
		return "index";
		
	}
	
}
