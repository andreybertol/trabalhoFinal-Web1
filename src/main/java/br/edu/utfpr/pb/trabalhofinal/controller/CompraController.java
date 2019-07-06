package br.edu.utfpr.pb.trabalhofinal.controller;

import br.edu.utfpr.pb.trabalhofinal.model.Compra;
import br.edu.utfpr.pb.trabalhofinal.model.CompraProduto;
import br.edu.utfpr.pb.trabalhofinal.repository.CompraRepository;
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
import java.math.BigDecimal;
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
    private CompraProdutoService compraProdutoService;
    @Autowired
    private ProdutoService produtoService;
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

        modelAndView.addObject("usuarios", usuarioService.findAll());
        modelAndView.addObject("fornecedores", fornecedorService.findAll());
        modelAndView.addObject("produtos", produtoService.findAll());

        if (compra != null) {

            modelAndView.addObject(compra);

        } else {
            modelAndView.addObject(new Compra());
        }
        return modelAndView;
    }

    // arrumar
    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Compra> list = this.getService().findAll(PageRequest.of(currentPage -1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
        modelAndView.addObject("list", list);

        modelAndView.addObject("usuarios", usuarioService.findAll());
        modelAndView.addObject("fornecedores", fornecedorService.findAll());
        modelAndView.addObject("produtos", produtoService.findAll());

        if(list.getTotalPages()>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
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
        if ( result.hasErrors() ) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Compra edit(@PathVariable Integer id) {
        return getService().findOne(id);
    }

    // validar
    @PostMapping("json")
    public ResponseEntity<?> saveJson(@RequestBody @Valid Compra entity, BindingResult result, Model model,
                                      RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        entity.setUsuario(usuarioService.findOne(entity.getUsuario().getId()));
        entity.setFornecedor(fornecedorService.findOne(entity.getFornecedor().getId()));

        getService().save(entity);

        Double valorTotal = 0.0;

        // percorre detalhe apenas se houver produtos inseridos
        for (CompraProduto cp : entity.getCompraProdutos()) {
            cp.setProduto(produtoService.findOne(cp.getProduto().getId()));
            valorTotal = cp.getQuantidade() * cp.getProduto().getValor();
            cp.setValor(valorTotal);
            cp.setCompra(entity);

            valorTotal =+ cp.getValor();

            compraProdutoService.save(cp);
        }

        entity.setValor_total(valorTotal);

        getService().save(entity);

        return new ResponseEntity<>(HttpStatus.OK);
}
}


















