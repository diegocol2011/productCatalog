package com.catalog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.model.Category;
import com.catalog.model.Product;
import com.catalog.model.User;
import com.catalog.repository.CategoryRepository;
import com.catalog.repository.ProductRepository;
import com.catalog.repository.UserRepository;;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private CategoryRepository categoryRepository;

	 @Autowired
	 private ProductRepository productRepository;
	 
	@GetMapping(path = "/")
	public ModelAndView inicio() {
		return new ModelAndView("login").addObject("userdata",new User());
	}
	
	@PostMapping("/pag")
	public String pagePrincipal(User user, Model model) {
		List<User> listUser = new ArrayList<>(); 
		userRepository.findAll().iterator().forEachRemaining(listUser::add);
		
		for (User userBD : listUser) {
			if((userBD.getUsuario().equals(user.getUsuario())) && (userBD.getContrasena().equals(user.getContrasena())) ){
				if(userBD.getTipoUsuario().equals("admin") ) {
					model.addAttribute("categorydata",new Category());
					model.addAttribute("productdata",new Product());
					return "registrar";
				}
				return "index";
			}
		}
		model.addAttribute("userdata",new User());
		return "login";
	}
	
	@PostMapping("/createCategory")
	public String createCategory(Category category, Model model) {
		model.addAttribute("categorydata",new Category());
		model.addAttribute("productdata",new Product());
		 
		if(category.getName().equals("PC")) {
			category.setId(1);
		}else {
			category.setId(2);
		}
		
		String ruta = "/images/"+category.getName()+"/"+category.getImage();
		category.setImage(ruta);
		categoryRepository.save(category);
		
		return "registrar";
	}
	
	@PostMapping("/createProduct")
	public String createProduct(Product product, Model model) {
		model.addAttribute("categorydata",new Category());
		model.addAttribute("productdata",new Product());
		Category category = new Category(); 
		
		
		if(product.getName().equals("PC")) {
			category.setId(1);
			product.setCategory(category); 
			
		}else {
			category.setId(2);
			product.setCategory(category);
		}
		
		product.setImage1("/images/"+product.getName()+"/"+product.getImage1());
		product.setImage2("/images/"+product.getName()+"/"+product.getImage2());
		product.setImage3("/images/"+product.getName()+"/"+product.getImage3());
		productRepository.save(product);
		
		return "registrar";
	}
	
	@GetMapping(path = "/newlogin")
	public ModelAndView newlogin() {
		return new ModelAndView("crearLogin").addObject("nameData",new User());
	}
	
	@PostMapping(path = "/crearlogin")
	public ModelAndView validarlogin(User user) {
		if(user.getTipoUsuario()==null) {
			user.setTipoUsuario("noAdmin");
		}
		
		Iterator<User> it = userRepository. findAll().iterator();
		while (it.hasNext()) {
			if(it.next().getUsuario().equals(user.getUsuario()) ){
				return new ModelAndView("crearLogin").addObject("nameData",new User());
			}
		}
		
		userRepository.save(user);
		return new ModelAndView("login").addObject("userdata",new User());
	}
	
	@GetMapping("/pageCategory")
	public String paginaCategory(
			@RequestParam(defaultValue = "1", name = "id", required = false) int id, Model model) {
		
		Optional<Category> category= categoryRepository.findById(id);
		if (category.isPresent()) {
			model.addAttribute("categorydata", category.get());
			return "index2";
		}
		
		return "index";
		
	}

	

}

