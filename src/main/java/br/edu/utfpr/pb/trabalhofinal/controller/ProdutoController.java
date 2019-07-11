package br.edu.utfpr.pb.trabalhofinal.controller;

import br.edu.utfpr.pb.trabalhofinal.model.Produto;
import br.edu.utfpr.pb.trabalhofinal.service.CategoriaService;
import br.edu.utfpr.pb.trabalhofinal.service.CrudService;
import br.edu.utfpr.pb.trabalhofinal.service.MarcaService;
import br.edu.utfpr.pb.trabalhofinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("produto")
public class ProdutoController extends CrudController<Produto, Integer> {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private CategoriaService categoriaService;

    @Override
    protected CrudService<Produto, Integer> getService() {
        return produtoService;
    }

    @Override
    protected String getURL() {
        return "produto";
    }

    @Override
    @GetMapping("new")
    protected ModelAndView form(Produto produto) {
        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
        if (produto != null) {
            modelAndView.addObject(produto);
        } else {
            modelAndView.addObject(new Produto());
        }
        return modelAndView;
    }

    @Override
    @GetMapping("{id}")
    protected ModelAndView form(@PathVariable Integer id) {
        return null;
    }

    @GetMapping("detalhe/{id}")
    protected ModelAndView detalhe(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/detalhe");

        modelAndView.addObject(this.getService().findOne(id));

        return modelAndView;
    }

    @PostMapping("ajax")
    public ResponseEntity<?> save(@Valid Produto entity, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("ajax/{id}")
    @ResponseBody
    public Produto edit(@PathVariable Integer id) {
        return getService().findOne(id);
    }

    @GetMapping("page")
    public ModelAndView list(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Produto> list = this.getService().findAll(
                PageRequest.of(currentPage - 1, pageSize));

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
        modelAndView.addObject("list", list);

        modelAndView.addObject("marcas", marcaService.findAll());
        modelAndView.addObject("categorias", categoriaService.findAll());

        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(1, list.getTotalPages())
                    .boxed().collect(Collectors.toList());

            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        return modelAndView;
    }

    @GetMapping("comprar")
    public ModelAndView form(@RequestParam("page") Optional<Integer> page,
                                @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
        modelAndView.addObject(this.getService().findOne(id));

        modelAndView.addObject("produto", produtoService.findOne(id));

        return modelAndView;
    }

    //método para salvar com upload de arquivos
    @PostMapping("upload")
    public ResponseEntity<?> save(@Valid Produto entity, BindingResult result,
                                  @RequestParam("anexo") MultipartFile anexo,
                                  @RequestParam("anexos") MultipartFile[] anexos,
                                  HttpServletRequest request) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        getService().save(entity);

        if (anexo != null) {
            saveFile(entity.getId(), anexo);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void saveFile(Integer id, MultipartFile anexo) {
        File dir = new File("C:\\projetoFinal\\imagens\\");
        if (!dir.exists()) { //verifica se o diretório de armazenamento existe
            dir.mkdir(); //não existindo, cria o diretório
        }

        String caminhoAnexo = "C:\\projetoFinal\\imagens\\";
        String extensao = anexo.getOriginalFilename().substring(
                anexo.getOriginalFilename().lastIndexOf("."),
                anexo.getOriginalFilename().length());

        String nomeArquivo = id + extensao;

        try {
            FileOutputStream fileOut = new FileOutputStream(
                    new File(caminhoAnexo + nomeArquivo));

            BufferedOutputStream stream = new BufferedOutputStream(fileOut);

            stream.write(anexo.getBytes());
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



















