package it.uniroma3.siw.silph_spa;

import it.uniroma3.siw.silph_spa.model.Foto;
import it.uniroma3.siw.silph_spa.model.User;
import it.uniroma3.siw.silph_spa.storage.FotoRepository;
import it.uniroma3.siw.silph_spa.storage.RichiestaRepository;
import it.uniroma3.siw.silph_spa.storage.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FotoRepository fotoRepository;
    
    @Autowired
    private RichiestaRepository richiestaRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all tables from DB...");
        userRepository.deleteAll();
        fotoRepository.deleteAll();
        richiestaRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        User admin = new User(1L, "Paolo", "Merialdo", "merialdo", null, "ADMIN");
        String adminPassword = new BCryptPasswordEncoder().encode("merialdo");
        admin.setPassword(adminPassword);
        admin = this.userRepository.save(admin);

        User guest = new User(1L, "Giuseppe", "Verdi", "giuseppeverdi", null, "GUEST");
        String guestPassword = new BCryptPasswordEncoder().encode("gvpass");
        guest.setPassword(guestPassword);
        guest = this.userRepository.save(guest);
        
        
        System.out.println("Storing fotos...");
        
        
        Foto foto1 = new Foto(1L, "http://i2.res.24o.it/images2010/Editrice/ILSOLE24ORE/ILSOLE24ORE/2017/07/08/Motori24/ImmaginiWeb/Ritagli/Audi-A5-kD3G--835x437@IlSole24Ore-Web.jpg",
        		"giorgio", "porsche", "macchine");
        foto1 = this.fotoRepository.save(foto1);
       
        
        Foto foto2 = new Foto(1L, "https://welcomeinthisuniverse.files.wordpress.com/2014/12/immagini-macchine-sfondi.jpg",
        		"giorgio", "lamborghini", "macchine");
        foto2 = this.fotoRepository.save(foto2);
       
        
        Foto foto3 = new Foto(1L, "https://cdn.calciomercato.com/images/2018-03/FordMustangEcoboost.750x450.jpg",
        		"giorgio", "mustang", "macchine");
        foto3 = this.fotoRepository.save(foto3);
        
        
        Foto foto4 = new Foto(1L, "https://www.maxizoo.it/blog/wp-content/uploads/2017/11/Cosa-dare-da-mangiare-a-un-gattino.jpg",
        		"andrea", "gattino", "gatti");
        foto4 = this.fotoRepository.save(foto4);
        
        
        Foto foto5 = new Foto(1L, "https://www.informazioneambiente.it/wp-content/uploads/2018/07/Svezzamento-gattini-696x473.jpg",
        		"andrea", "gattini", "gatti");
        foto5 = this.fotoRepository.save(foto5);
        
        
        Foto foto6 = new Foto(1L, "https://www.mongrelpet.com/blog/wp-content/uploads/2017/03/dare-il-latte-ai-gattini-con-il-biberon-2.jpg",
        		"andrea", "gatto cucciolo", "gatti");
        foto6 = this.fotoRepository.save(foto6);
        
        
        System.out.println("Storing requests...");
        
        System.out.println("Done.\n");
    }
}
