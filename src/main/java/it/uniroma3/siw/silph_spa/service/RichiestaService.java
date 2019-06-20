package it.uniroma3.siw.silph_spa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.silph_spa.model.Foto;
import it.uniroma3.siw.silph_spa.model.Richiesta;
import it.uniroma3.siw.silph_spa.storage.FotoRepository;
import it.uniroma3.siw.silph_spa.storage.RichiestaRepository;


@Service 
public class RichiestaService  {

	@Autowired
	public RichiestaRepository richiestaRepository; 
	

	@Transactional
	public List<Richiesta> tutti() {
		return (List<Richiesta>) richiestaRepository.findAll();
	}
	
	
	@Transactional 
	public Richiesta inserisci(Richiesta richiesta) {
		return richiestaRepository.save(richiesta);
	}
	
	public void rimuovi(Long id) {
		richiestaRepository.delete(richiestaRepository.findById(id).get());
	}
	
	public Richiesta trovaId(Long id) {
		return (Richiesta) richiestaRepository.findById(id).get();
	}
	
		
}