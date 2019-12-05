package com.catalog.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.catalog.model.Category;
import com.catalog.model.User;
import com.catalog.repository.UserRepository;;

@Controller
@RequestMapping("/home")
public class ControllerBasic {
	
	 @Autowired
	 private UserRepository userRepository;
	
	@GetMapping(path = "/")
	public ModelAndView inicio() {
		return new ModelAndView("login").addObject("nameData",new User());
	}
	
	@PostMapping("/pag")
	public ModelAndView paginaPrincipal(User user) {
		List<User> list = new ArrayList<>(); 
		userRepository. findAll().iterator().forEachRemaining(list::add);
		
		for (User userBD : list) {
			if((userBD.getUsuario().equals(user.getUsuario())) && (userBD.getContrasena().equals(user.getContrasena())) ){
				if(userBD.getTipoUsuario().equals("admin") ) {
					return new ModelAndView("registrar").addObject("nameData",new Category());
				}
				return new ModelAndView("index");
			}
		}
		
		return new ModelAndView("login").addObject("nameData",new User());
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
		return new ModelAndView("login").addObject("nameData",new User());
	}
	
	
	
	/*@Autowired
	private PostComponent postComponent;
	
	@GetMapping(path = {"/"})
	public String saludar(Model model) {
		model.addAttribute("nameData", this.postComponent.getPosts());
		return "index2";
	}
	
	
	
	@GetMapping(path="/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Paginas.HOME);
		modelAndView.addObject("nameData", this.postComponent.getPosts());
		return modelAndView;
	}
	
	@GetMapping(path = { "/post" })
	public ModelAndView getPostIndividualParam(
			@RequestParam(defaultValue = "1", name = "id", required = false) int id) {
		ModelAndView modelAndView = new ModelAndView(Paginas.POST);

		List<Post> postFiltadoList = this.postComponent.getPosts().stream().filter(p -> {
			return p.getId() == id;
		}).collect(Collectors.toList());

		modelAndView.addObject("nameData", postFiltadoList.get(0));
		return modelAndView;
	}
	
	@GetMapping(path = { "/post/{ruta}" })
	public ModelAndView getPostIndividualPath(
			@PathVariable(required = true, name = "ruta") int id) {
		ModelAndView modelAndView = new ModelAndView(Paginas.POST);

		List<Post> postFiltadoList = this.postComponent.getPosts().stream().filter(p -> {
			return p.getId() == id;
		}).collect(Collectors.toList());

		modelAndView.addObject("nameData", postFiltadoList.get(0));
		return modelAndView;

	}
	
	//form.html-------------------
	
	@GetMapping(path = {"/postNew1"})
	public String getForm(Model model) {
		model.addAttribute("nameData", new Post());
		return "form";
	}
	
	@GetMapping ("/postNew2")
	public ModelAndView getForm() {
		return new ModelAndView("form").addObject("nameData",new Post());
	}

	//index.html-------------------
	
	@PostMapping("/addNewPost")
	public String addNewPost(Post post, Model model) {
		List<Post> posts = this.postComponent.getPosts();
		posts.add(post);
		model.addAttribute("nameData",posts);
		return "index2";
	}*/
	
	
	

}

