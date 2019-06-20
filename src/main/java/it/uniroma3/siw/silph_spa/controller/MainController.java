package it.uniroma3.siw.silph_spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;

import it.uniroma3.siw.silph_spa.model.Foto;
import it.uniroma3.siw.silph_spa.model.Richiesta;
import it.uniroma3.siw.silph_spa.service.FotoService;
import it.uniroma3.siw.silph_spa.service.RichiestaService;
import it.uniroma3.siw.silph_spa.storage.FotoRepository;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
/**
 * The MainController is a Spring Boot Controller to handle
 * the generic interactions with the home pages, and that do not refer to specific entities
 */
@Controller
public class MainController {
	
	@Autowired
    private FotoService fotoService;
	
	@Autowired
    private RichiestaService richiestaService;
	

    public MainController() {
        super();
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/" or "/index".
     * This method prepares and dispatches the home view.
     *
     * @return the name of the home view
     */
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
		model.addAttribute("fotos", this.fotoService.tutti());
		model.addAttribute("fotografi",this.fotoService.getFotografi());
		model.addAttribute("albums",this.fotoService.getAlbums());
        return "home";
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/welcome".
     * This method prepares and dispatches the welcome view.
     *
     * @return the name of the welcome view
     */
    @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);
        return "welcome";
    }
    
    
    @RequestMapping(value = { "/aggiungiFoto" }, method = RequestMethod.GET)
    public String aggiungiFoto(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();    
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);
        model.addAttribute("foto", new Foto());
        return "aggiungiFoto";
    }
    
    
    
    @RequestMapping(value = { "/inserisciRichiesta" }, method = RequestMethod.GET)
    public String inserisciRichiesta(Model model, HttpSession session) {
        
		model.addAttribute("richiesta", new Richiesta());
		return "form";
    }
    
    
    @RequestMapping(value = "/inserimentoRichiesta", method = RequestMethod.POST)
	public String inserimentoRichiesta(@Valid @ModelAttribute("foto") Richiesta r, Model model,HttpSession session) {
    		List<Foto> f = (List<Foto>) session.getAttribute("foto_scelte");
    		r.setFotoScelte(f);
    		this.richiestaService.inserisci(r);
    		System.out.println("tutto ok");
			model.addAttribute("fotos", this.fotoService.tutti());
			model.addAttribute("fotografi",this.fotoService.getFotografi());
			model.addAttribute("albums",this.fotoService.getAlbums());
			return "home";
    }
    
    
    
    
    
    @RequestMapping(value = "/aggiungiRichiesta/{id}", method = RequestMethod.GET)
	public String aggiungiRichiesta(@PathVariable ("id") Long id, Model model, HttpSession session) {
        
    	if (session.getAttribute("lista_foto") != null) {
    		this.richiestaService.tutti().get(0).addFoto(this.fotoService.trovaId(id));
    	} else {
    		List<Foto> foto_richieste = new ArrayList<>();
    		foto_richieste.add(this.fotoService.trovaId(id));
    	}
    	
    	
    	model.addAttribute("fotos", this.fotoService.tutti());
		model.addAttribute("fotografi",this.fotoService.getFotografi());
		model.addAttribute("albums",this.fotoService.getAlbums());
        return "home";
    }
    
    
    @RequestMapping(value = "/allRequest", method = RequestMethod.GET)
	public String allRequest(Model model) {
			model.addAttribute("richieste", this.richiestaService.tutti());
			return "visualizzaRichieste";
    }
    
    
    @RequestMapping(value = "/rimuoviId/{id}", method = RequestMethod.GET)
	public String rimuoviRichiestaId(@PathVariable ("id") Long id, Model model) {
		this.richiestaService.rimuovi(id);	 
		model.addAttribute("richieste", this.richiestaService.tutti());
		return "visualizzaRichieste";
	}
    
    
    @RequestMapping(value = "/allFotos", method = RequestMethod.GET)
	public String newUtente(Model model) {
			model.addAttribute("fotos", this.fotoService.tutti());
			return "visualizzaFoto";
    }
    
    
    @RequestMapping(value = "/visualizzaFoto", method = RequestMethod.POST)
	public String newUtente(@Valid @ModelAttribute("foto") Foto foto, Model model) {
			this.fotoService.inserisci(foto);
			model.addAttribute("fotos", this.fotoService.tutti());
			return "visualizzaFoto";
    }
    
    
    
    @RequestMapping(value = "/byFotografo/{fotografo}", method = RequestMethod.GET)
	public String fotoByFotografo(@PathVariable ("fotografo") String fotografo, Model model) {
		
    	model.addAttribute("fotos",this.fotoService.tutteByFotografo(fotografo));
    	model.addAttribute("albums",this.fotoService.getAlbums());
    	model.addAttribute("fotografi", fotografo);
    	return "home";
	}
    
    
    @RequestMapping(value = "/byAlbum/{album}", method = RequestMethod.GET)
	public String fotoByAlbum(@PathVariable ("album") String album, Model model) {
		
    	model.addAttribute("fotos",this.fotoService.tutteByAlbum(album));
    	model.addAttribute("fotografi",this.fotoService.getFotografi());
    	model.addAttribute("albums", album);
    	return "home";
	}
    
    
    @RequestMapping(value = "/foto/byID/{id}", method = RequestMethod.GET)
	public String utenteById(@PathVariable ("id") Long id, Model model) {
		
		if (id != null) {	
			model.addAttribute(this.fotoService.trovaId(id));
			return "foto";
		} else {
			model.addAttribute("fotos", this.fotoService.tutti());
			return "visualizzaFoto";
		}
	}
    
    
    @RequestMapping(value = "/foto/removeByID/{id}", method = RequestMethod.GET)
	public String removeById(@PathVariable ("id") Long id, Model model) {
		
		if (id != null) {	
			this.fotoService.rimuovi(id);	
		} 
		model.addAttribute("fotos", this.fotoService.tutti());
		return "visualizzaFoto";
	}

    /**
     * This method is called when a GET request is sent by the user to URL "/admin".
     * This method prepares and dispatches the admin view.
     *
     * @return the name of the admin view
     */
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();    // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "admin";
    }
}