package br.edu.utfpr.pb.trabalhofinal.controller;

import br.edu.utfpr.pb.trabalhofinal.model.Produto;
import br.edu.utfpr.pb.trabalhofinal.model.Venda;
import br.edu.utfpr.pb.trabalhofinal.model.VendaProduto;
import br.edu.utfpr.pb.trabalhofinal.repository.VendaRepository;
import br.edu.utfpr.pb.trabalhofinal.service.*;
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

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("venda")
public class VendaController extends CrudController<Venda, Integer> {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private VendaProdutoService vendaProdutoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VendaRepository vendaRepository;

    @Override
    protected CrudService<Venda, Integer> getService() {
        return vendaService;
    }

    @Override
    protected String getURL() {
        return "venda";
    }

    @GetMapping("checkout")
    protected ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/checkout");

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
    public ResponseEntity<?> save(@Valid Venda entity, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // validar
    @PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody @Valid Venda entity, BindingResult result, Model model,
                                      RedirectAttributes attributes, Principal principal) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        entity.setUsuario(usuarioService.findByUsername(principal.getName()));

        getService().save(entity);

        // percorre detalhe apenas se houver produtos inseridos
        for (VendaProduto vp : entity.getVendaProdutos()) {
            vp.setProduto(produtoService.findOne(vp.getProduto().getId()));
            vp.setVenda(entity);

            vendaProdutoService.save(vp);
        }

        getService().save(entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    protected ModelAndView form(Venda entity) {
        return null;
    }

    @GetMapping(value = "historico")
    public ModelAndView findVendaByUsuarioId(Principal pricipal) {

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/historico");

        modelAndView.addObject("vendas", vendaRepository.findByUsuarioId(usuarioService.findByUsername(pricipal.getName()).getId()));

        return modelAndView;
    }
}