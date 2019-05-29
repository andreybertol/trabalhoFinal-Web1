package br.edu.utfpr.pb.trabalhofinal.controller;

import br.edu.utfpr.pb.trabalhofinal.model.Categoria;
import br.edu.utfpr.pb.trabalhofinal.service.CategoriaService;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("categoria")
public class CategoriaController extends CrudController<Categoria, Integer>{

	@Autowired
	private CategoriaService categoriaService;

	@Override
	protected CrudService<Categoria, Integer> getService() {
		return categoriaService;
	}

	@Override
	protected String getURL() {
		return "categoria";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Categoria categoria) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (categoria != null){
			modelAndView.addObject(categoria);
		}else {
			modelAndView.addObject(new Categoria());
		}
		return modelAndView;
	}

	@Override
	@GetMapping("{id}")
	protected ModelAndView form(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");

		modelAndView.addObject(this.getService().findOne(id));

		return modelAndView;
	}

	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Categoria entity, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Categoria edit(@PathVariable Integer id){
		return getService().findOne(id);
	}
}



















