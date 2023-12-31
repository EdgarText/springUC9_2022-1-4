package br.edu.senai.springUC9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.senai.springUC9.orm.Categoria;
import br.edu.senai.springUC9.services.CategoriaServico;

@Controller
public class SpringUC9Controller {
	@Autowired
	private CategoriaServico servico;
	
	@GetMapping({"/"})
	public String path() {
        return "index";
    }
	
	@GetMapping({"/categorias"})
	public String listarCategorias(Model modelo) {
        modelo.addAttribute("categoria", servico.listarCategorias());
		return "categorias";
    }
	
	@GetMapping("/categorias/adicionar")
	public String adicionarCategorias(Model modelo) {
		Categoria objcategoria = new Categoria();
		modelo.addAttribute("categoria", objcategoria);
		return "categoriaform";
	}
	
	@PostMapping("/categoria")
	public String salvarCategorias(@ModelAttribute("categoria") Categoria objcategoria) {
		servico.salvarCategorias(objcategoria);
		return "redirect:/categorias";
	}
	
	@GetMapping({"/categoria/{id}"})
	public String apagarCategoria(@PathVariable Integer id) {
		servico.apagarCategoria(id);
		return "redirect:/categorias";
	}
	
	@GetMapping({"/categoria/editar/{id}"})
	public String editarCategoria(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("categoria", servico.consultarCategoriasId(id));
		return "editarCategoria";
	}
	
	@PostMapping("/categoria/{id}")
	public String atualizarCategoria(@PathVariable Integer id, @ModelAttribute("categoria") Categoria categoria, Model modelo) {
		Categoria cat = servico.consultarCategoriasId(id);
		cat.setId(id);
		cat.setDescricao(categoria.getDescricao());
		cat.setTributacao(categoria.getTributacao());
		cat.setAtivo(categoria.getAtivo());
		servico.atualizarCategorias(cat);
		return "redirect:/categorias";
	}
}