package it.uniroma3.siw.silph_spa.storage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.silph_spa.model.Foto;
import it.uniroma3.siw.silph_spa.model.Richiesta;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see Foro
 */
public interface RichiestaRepository extends JpaRepository<Richiesta, Long> {

	
	

}