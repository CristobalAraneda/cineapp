package net.cris.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.cris.app.model.Pelicula;
import net.cris.app.service.IBannersService;
import net.cris.app.service.IPeliculasService;
import net.cris.app.util.Utileria;

@Controller
public class HomeController {
	@Autowired
	private IBannersService serviceBanners;
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@RequestMapping(value ="/seach", method = RequestMethod.POST)
	public String Buscar(@RequestParam("fecha") String fecha, Model model){
		
		List<String> listaFecha = Utileria.getNextDays(4);
		System.out.println(listaFecha);

		List<Pelicula> peliculas = servicePeliculas.buscarTodas();

		model.addAttribute("fechas",listaFecha);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners", serviceBanners.buscarTodos());

		System.out.println("Buscando todas las peliculas en exhibicion para la fecha: " + fecha);
		return"home";
		
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		List<String> listaFecha = Utileria.getNextDays(4);
		System.out.println(listaFecha);

		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
//		peliculas.add("Rapido y furioso");
//		peliculas.add("El aro 2");
//		peliculas.add("Aliens");
		model.addAttribute("fechas",listaFecha);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners",serviceBanners.buscarTodos());

		return "home";
	}

	//@RequestMapping(value = "/detail/{id}/{fecha}", method = RequestMethod.GET)
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	//public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha) {
	public String mostrarDetalle(Model model,@RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		
		System.out.println("Buscando Horarios para la pelicula:" + idPelicula);
		System.out.println("para la fecha:" + fecha);
		
		model.addAttribute("pelicula",servicePeliculas.buscarPorTd(idPelicula));
		
		// TODO - Buscar en la base de datos los horarios .
	
		return "detalle";
	}

}
