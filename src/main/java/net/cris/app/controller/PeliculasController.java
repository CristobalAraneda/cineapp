package net.cris.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.cris.app.model.Pelicula;
import net.cris.app.service.IDetallesService;
import net.cris.app.service.IPeliculasService;
import net.cris.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IDetallesService serviceDetalles;
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		
		return "peliculas/listPeliculas";
	}
	
	@GetMapping("/create")
	public String Crear(@ModelAttribute Pelicula pelicula, Model model) {
		
		model.addAttribute("generos", servicePeliculas.buscaGeneros());
		
		return "peliculas/formPeliculas";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Existeron errores");
			return "peliculas/formPeliculas";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			pelicula.setImagen(nombreImagen); 
			}
		
//		for(ObjectError error : result.getAllErrors()) {
//			System.out.println(error.getDefaultMessage());
//			
//		}
		
        //System.out.println("Recibiendo objeto pelicula: " +pelicula);
		
		//System.out.println("Elementos en la lista antes de la insersion: " + servicePeliculas.buscarTodas().size());
		
		serviceDetalles.insertar(pelicula.getDetalle());
		
		servicePeliculas.insertar(pelicula);
		
		//System.out.println("Elementos en la lista despues de la insersion: " + servicePeliculas.buscarTodas().size());
		
		attributes.addFlashAttribute("msg", "El registro fue guardado");
		
		//return "peliculas/formPeliculas";
		return "redirect:/peliculas/index";
		
	}
	@GetMapping(value= "/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		
		Pelicula pelicula = servicePeliculas.buscarPorTd(idPelicula);
		model.addAttribute("pelicula", pelicula);
		
		
		return "peliculas/formPeliculas";
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
