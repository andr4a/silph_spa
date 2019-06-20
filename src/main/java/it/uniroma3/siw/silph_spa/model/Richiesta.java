package it.uniroma3.siw.silph_spa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "richiesta")
public class Richiesta {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    protected Long id;
    
    
    @Column(name = "nome")
    protected String nome;

    
    @Column(name = "cognome")
    protected String cognome;

    
    @Column(name = "email")
    protected String email;

    
    @OneToMany(fetch = FetchType.EAGER)
    protected List<Foto> fotoScelte;
    

    /**
     * Constructor
     */
    public Richiesta(Long id, String nome, String cognome, String email, List<Foto> fotoScelte) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.fotoScelte = fotoScelte;
    }

    /**
     * Empty Constructor
     */
    public Richiesta() {
    	this.fotoScelte = new ArrayList<Foto>();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void addFoto(Foto f) {
    	this.fotoScelte.add(f);
    }
    
    public List<Foto> getFotoScelte() {
        return fotoScelte;
    }
    
    public void setFotoScelte(List<Foto> fotoScelte) {
    	this.fotoScelte = fotoScelte;
    }
    
 }