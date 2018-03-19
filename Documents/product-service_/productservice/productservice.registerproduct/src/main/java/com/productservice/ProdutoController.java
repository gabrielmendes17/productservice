package com.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Produto;
import repository.ProdutoRepository;

@Controller
@RequestMapping(name = "produtoController", value = "produtoController")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	/*@Autowired
	private IStorageService storageService;*/

	@RequestMapping("cadastroproduto")
	public String cadastroProduto() {
		return "cadastro-produto";
	}
	
	@RequestMapping("pesquisaproduto")
	public String pesquisaProduto() {
		return "pesquisa-produtos";
	}
	
	@RequestMapping("/")
	public ModelAndView listaProdutos(Produto produto) {
		ModelAndView mv = new ModelAndView("/listaprodutos");
		Iterable<Produto> produtos = repository.findAll();
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Produto produto, BindingResult result, Model model, RedirectAttributes attributes/*, @RequestParam("file") MultipartFile file*/) {
		if (result.hasErrors()) {
			Iterable<Produto> produtos = repository.findAll();
			model.addAttribute("produtos", produtos);
			model.addAttribute("mensagen", "Erro no formulário");
			return listaProdutos(produto);
		}
		repository.save(produto);
		/*storageService.store(file);
		attributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");*/
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produtoController/");
	}
	
	@RequestMapping(value = "/Excluir/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
		Produto produto = new Produto();
		produto.setId(id);
		repository.delete(produto);
		attributes.addFlashAttribute("mensagem", "Produto removido com sucesso!");
		return new ModelAndView("redirect:/produtoController/");
	}
	
	@RequestMapping(value = "/Editar/{id}", method = RequestMethod.POST)
	public ModelAndView editar(@PathVariable("id") Long id,  Model model) {
		Produto produto = new Produto();
		produto.setId(id);
		model.addAttribute("produto", repository.findOne(produto.getId()));
		return listaProdutos(produto);
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}

