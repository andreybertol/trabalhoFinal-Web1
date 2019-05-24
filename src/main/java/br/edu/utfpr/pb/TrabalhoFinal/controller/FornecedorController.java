package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Fornecedor;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import br.edu.utfpr.pb.trabalhofinal.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController extends CrudController<Fornecedor, Integer>{

	@Autowired
	private FornecedorService fornecedorService;

	@Override
	protected CrudService<Fornecedor, Integer> getService() {
		return fornecedorService;
	}

	@Override
	protected String getURL() {
		return "fornecedor";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Fornecedor fornecedor) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (fornecedor != null){
			modelAndView.addObject(fornecedor);
		}else {
			modelAndView.addObject(new Fornecedor());
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
	public ResponseEntity<?> save(@Valid Fornecedor entity, BindingResult result) {
		if (result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody
	public Fornecedor edit(@PathVariable Integer id){
		return getService().findOne(id);
	}
}



















