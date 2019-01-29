package net.cris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.cris.app.model.Noticia;
import net.cris.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	@GetMapping(value ="/create")
	public String crear() {
		return "noticias/formNoticias";
	} 
	
	@PostMapping(value = "/save")
	public String guardar(Noticia noticia) {
		
		
		// Pendiente: Guardar el objeto noticia en la BD		
		
		serviceNoticias.guardar(noticia);
		
		return "noticias/formNoticias";
	} 

}
