package br.edu.utfpr.pb.trabalhofinal.controller;


import br.edu.utfpr.pb.trabalhofinal.model.Permissao;
import br.edu.utfpr.pb.trabalhofinal.model.Usuario;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import br.edu.utfpr.pb.trabalhofinal.service.PermissaoService;
import br.edu.utfpr.pb.trabalhofinal.service.UsuarioService;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("usuario")
public class UsuarioController
        extends CrudController<Usuario, Long>{

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PermissaoService permissaoService;
    @Override
    protected CrudService<Usuario, Long> getService() {
        return usuarioService;
    }

    @Override
    protected String getURL() {
        return "usuario";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Usuario entity) {
        ModelAndView modelAndView = new ModelAndView(getURL() + "/form");
        if (entity == null) {
            modelAndView.addObject("usuario", new Usuario());
        } else {
            modelAndView.addObject("usuario", entity);
        }
        return modelAndView;
    }

    @Override
    protected ModelAndView form(Long id) {
        return null;
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Usuario edit(@PathVariable Long id) {
        return getService().findOne(id);
    }

    @PostMapping("ajax")
    public ResponseEntity<?> saveAjax(@Valid Usuario entity,
                                      BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        entity.setPassword(
                entity.getEncodedPassword(entity.getPassword()));
        getService().save(entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Usuario> list = this.getService().findAll(
                PageRequest.of(currentPage -1, pageSize) );

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
        modelAndView.addObject("list", list);

        modelAndView.addObject("permissoes", permissaoService.findAll());

        if( list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }
}


// CLIENTE


@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value  = {"new", "novo"})
    public String form(Model model, HttpServletRequest request) {
        model.addAttribute("usuario", new Usuario() );

        return "cliente/cadastro";
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid Usuario usuario, BindingResult result,
                                  Model model, RedirectAttributes attributes,
                                  HttpServletRequest request) {

        if ( result.hasErrors() ) {
            //model.addAttribute("usuario", usuario);
            //return "usuario/form";
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        usuarioService.save(usuario);

        //attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
        //return "redirect:/usuario";
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("{id}")
    public String form(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.findOne(id);

        model.addAttribute("usuario", usuario);

        return "usuario/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, Model model,
                                    RedirectAttributes attributes) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
            //attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            //attributes.addFlashAttribute("erro", "Falha ao remover registro.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //return "redirect:/usuario";
    }
}


    @PostMapping("saveCliente")
    public ResponseEntity<?> saveCliente(@Valid Usuario entity,
                                         BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        entity.setPassword(
                entity.getEncodedPassword(entity.getPassword()));

        entity.setPermissoes((Set<Permissao>) permissaoService.findOne(2));

        getService().save(entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }





















