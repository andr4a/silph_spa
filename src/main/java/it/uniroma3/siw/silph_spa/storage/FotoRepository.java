package it.uniroma3.siw.silph_spa.storage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.silph_spa.model.Foto;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see Foro
 */
public interface FotoRepository extends JpaRepository<Foto, Long> {

	List<Foto> findByFotografo(String fotografo);
	
	List<Foto> findByAlbum(String album);
	
	
	

}