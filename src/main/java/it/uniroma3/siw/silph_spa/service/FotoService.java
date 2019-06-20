package it.uniroma3.siw.silph_spa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph_spa.model.Foto;
import it.uniroma3.siw.silph_spa.storage.FotoRepository;


@Service 
public class FotoService  {

	@Autowired
	public FotoRepository fotoRepository; 
	

	@Transactional
	public List<Foto> tutti() {
		return (List<Foto>) fotoRepository.findAll();
	}
	
	
	@Transactional 
	public Foto inserisci(Foto foto) {
		return fotoRepository.save(foto);
	}
	
	public void rimuovi(Long id) {
		fotoRepository.delete(fotoRepository.findById(id).get());
	}
	
	public Foto trovaId(Long id) {
		return (Foto) fotoRepository.findById(id).get();
	}
	
	
	public List<Foto> tutteByFotografo(String fotografo) {
		return (List<Foto>) fotoRepository.findByFotografo(fotografo);
	}
	
	public List<Foto> tutteByAlbum(String album) {
		return (List<Foto>) fotoRepository.findByAlbum(album);
	}
	
	public List<String> getFotografi(){
		
		List<String> list_Fotografi = new ArrayList<>();
		
		for (Foto f: fotoRepository.findAll()) {
			if (!list_Fotografi.contains(f.getFotografo())) {
				list_Fotografi.add(f.getFotografo());
			}
		}		
		return list_Fotografi;
	}
	
	
	public List<String> getAlbums(){
		
		List<String> list_album = new ArrayList<>();
		
		for (Foto f: fotoRepository.findAll()) {
			if (!list_album.contains(f.getAlbum())) {
				list_album.add(f.getAlbum());
			}
		}		
		return list_album;
	}
	
	
	
	
	

	
}