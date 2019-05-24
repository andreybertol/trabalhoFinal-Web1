package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import br.edu.utfpr.pb.trabalhofinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends CrudController<Usuario, Integer>{

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected CrudService<Usuario, Integer> getService() {
        return usuarioService;
    }

    @Override
    protected String getURL() {
        return "usuario";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
        if (usuario != null){
            modelAndView.addObject(usuario);
            /*modelAndView.addObject("permissao", permissaoService.findAll());*/
        }else {
            modelAndView.addObject(new Usuario());
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
    public ResponseEntity<?> save(@Valid Usuario entity, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Usuario edit(@PathVariable Integer id){
        return getService().findOne(id);
    }
}



















