package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Compra;
import br.edu.utfpr.pb.trabalhofinal.model.CompraProduto;
import br.edu.utfpr.pb.trabalhofinal.repository.CompraRepository;
import br.edu.utfpr.pb.trabalhofinal.service.CompraService;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("compra")
public class CompraController extends CrudController<Compra, Integer>{

	@Autowired
	private CompraService compraService;


	@Override
	protected CrudService<Compra, Integer> getService() {
		return compraService;
	}

	@Override
	protected String getURL() {
		return "compra";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Compra compra) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if(compra != null){
			modelAndView.addObject(compra);
		}else {
			modelAndView.addObject(new Compra());
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
	public ResponseEntity<?> save(@Valid Compra entity, BindingResult result){
		if(result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("ajax/{id}")
	@ResponseBody // transforma o retorno de objeto para json
	public Compra edit(@PathVariable Integer id){
		return getService().findOne(id);
	}

	@Override
	@GetMapping("page")
	public  ModelAndView list(@RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Page<Compra> list = this.getService().findAll(PageRequest.of(currentPage -1, pageSize));

		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);

		if(list.getTotalPages()>0){
			List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}

	//lixo abaixo
	//lixo abaixo
	//lixo abaixo
	//lixo abaixo
	//lixo abaixo
	@Autowired
	private CompraRepository compraRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/compraproduto")
	public ModelAndView inicio(){
		ModelAndView modelAndView = new ModelAndView("compra/compraproduto");
		modelAndView.addObject("compra", new Compra());
		Iterable<Compra> comprasIt = compraRepository.findAll();
		modelAndView.addObject("compras", comprasIt);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarproduto")
	public ModelAndView salvar(Compra compra) {
		compraRepository.save(compra);

		ModelAndView andView = new ModelAndView("compra/compraproduto");
		Iterable<Compra> comprasIt = compraRepository.findAll();
		andView.addObject("compras", comprasIt);
		andView.addObject("compra", new Compra());

		return andView;
	}
}



















