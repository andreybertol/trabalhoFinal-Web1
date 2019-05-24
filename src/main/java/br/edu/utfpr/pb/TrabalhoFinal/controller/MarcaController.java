package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Marca;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import br.edu.utfpr.pb.trabalhofinal.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("marca")
public class MarcaController extends CrudController<Marca, Integer>{

	@Autowired
	private MarcaService marcaService;

	@Override
	protected CrudService<Marca, Integer> getService() {
		return marcaService;
	}

	@Override
	protected String getURL() {
		return "marca";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Marca marca) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (marca != null){
			modelAndView.addObject(marca);
		}else {
			modelAndView.addObject(new Marca());
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
	public ResponseEntity<?> save(@Valid Marca entity, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Marca edit(@PathVariable Integer id){
		return getService().findOne(id);
	}
}



















