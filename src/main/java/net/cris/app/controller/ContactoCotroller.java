package net.cris.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.cris.app.model.Contacto;
import net.cris.app.service.IPeliculasService;

@Controller
public class ContactoCotroller {
	
	@Autowired
	IPeliculasService servicePeliculas;
	
    @GetMapping("/contacto")
	public String  mostarFormulario(@ModelAttribute("instanciaContacto") Contacto contacto, Model model) {
    	
    	model.addAttribute("generos", servicePeliculas.buscaGeneros());
    	model.addAttribute("tipos", tipoNotificaciones());
		
		return "formContacto";
	}
    
    @PostMapping("/contacto")
    public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto) {
    	
        System.out.println(contacto);
    	return "redirect:/contacto";
    	
    }
    
    private List<String> tipoNotificaciones(){
		
    	List<String> tipo = new LinkedList<>();
    	tipo.add("Estrenos");
    	tipo.add("Promociones");
    	tipo.add("Noticuas");
    	tipo.add("Preventas");
    	
    	return tipo;
    	
    	
    }
    
}
