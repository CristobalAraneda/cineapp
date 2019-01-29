package net.cris.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.cris.app.model.Banner;
import net.cris.app.service.IBannersService;
import net.cris.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannersController  {
	
	@Autowired
	private IBannersService serviceBanners;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Banner> banners =  serviceBanners.buscarTodos();
		
		model.addAttribute("banners",banners);
		
		return "banners/listBanners";
	}
	/**
	 * Metodo para mostar el formulario para crear un nuevo Banner
	 * @return
	 */
	 @GetMapping("/create")
	 public String crear() {
	 	return "banners/formBanners";
	 }
	/**
	 * Metodo para guardar el objeto modelo de tipo Banner
	 * @param banner
	 * @param result
	 * @param attributes
	 * @param multiPart
	 * @param request
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, RedirectAttributes attributes,
		@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request
					) {
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			return "banners/formBanner";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			banner.setArchivo(nombreImagen);
		}
		
		serviceBanners.insertar(banner);
    	attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/banners/index";
		
	}



}
