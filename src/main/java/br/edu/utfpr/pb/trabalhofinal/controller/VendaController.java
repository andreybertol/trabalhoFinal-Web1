package br.edu.utfpr.pb.trabalhofinal.controller;

import br.edu.utfpr.pb.trabalhofinal.model.Produto;
import br.edu.utfpr.pb.trabalhofinal.model.Venda;
import br.edu.utfpr.pb.trabalhofinal.model.VendaProduto;
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

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Venda edit(@PathVariable Integer id) {
        return getService().findOne(id);
    }

    // validar
    @PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody @Valid Venda entity, BindingResult result, Model model,
                                      RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        entity.setUsuario(usuarioService.findOne(entity.getUsuario().getId()));

        getService().save(entity);

        Double valorTotal = 0.0;

        // percorre detalhe apenas se houver produtos inseridos
        for (VendaProduto cp : entity.getVendaProdutos()) {
            cp.setProduto(produtoService.findOne(cp.getProduto().getId()));
            valorTotal = cp.getQuantidade() * cp.getProduto().getValor();
            cp.setValor(valorTotal);
            cp.setVenda(entity);

            valorTotal = +cp.getValor();

            vendaProdutoService.save(cp);
        }

        entity.setValor_total(valorTotal);

        getService().save(entity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    protected ModelAndView form(Venda entity) {
        return null;
    }
}


















