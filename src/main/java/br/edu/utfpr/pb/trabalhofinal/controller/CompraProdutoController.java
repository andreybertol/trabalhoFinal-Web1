package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Compra;
import br.edu.utfpr.pb.trabalhofinal.model.CompraProduto;
import br.edu.utfpr.pb.trabalhofinal.service.CompraProdutoService;
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
@RequestMapping("compraProduto")
public class CompraProdutoController extends CrudController<CompraProduto, Integer> {

    @Autowired
    private CompraProdutoService compraProdutoService;


    @Override
    protected CrudService<CompraProduto, Integer> getService() {
        return compraProdutoService;
    }

    @Override
    protected String getURL() {
        return "compraProduto";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(CompraProduto compraProduto) {
        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
        if (compraProduto != null) {
            modelAndView.addObject(compraProduto);
        } else {
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
    public ResponseEntity<?> save(@Valid CompraProduto entity, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody // transforma o retorno de objeto para json
    public CompraProduto edit(@PathVariable Integer id) {
        return getService().findOne(id);
    }

    @Override
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<CompraProduto> list = this.getService().findAll(PageRequest.of(currentPage - 1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
        modelAndView.addObject("list", list);


        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }
}



















