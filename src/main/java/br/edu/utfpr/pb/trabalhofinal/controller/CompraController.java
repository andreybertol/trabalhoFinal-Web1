package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Compra;
import br.edu.utfpr.pb.trabalhofinal.repository.CompraRepository;
import br.edu.utfpr.pb.trabalhofinal.service.*;
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
public class CompraController extends CrudController<Compra, Integer> {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FornecedorService fornecedorService;

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
        if (compra != null) {
            modelAndView.addObject(compra);
            modelAndView.addObject("usuarios", usuarioService.findAll());
            modelAndView.addObject("fornecedores", fornecedorService.findAll());
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
}


















