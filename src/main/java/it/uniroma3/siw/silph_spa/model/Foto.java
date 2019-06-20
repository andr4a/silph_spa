package it.uniroma3.siw.silph_spa.model;

import javax.persistence.*;

/**
 * A User is a generic person who can use our application.
 * Subclasses of User include Admin and Customer.
 * @see Foto
 */
@Entity
@Table(name = "foto")
public class Foto {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    protected Long id;
    
    
    @Column(name = "url")
    protected String url;

    
    @Column(name = "fotografo")
    protected String fotografo;

    
    @Column(name = "titolo")
    protected String titolo;

    
    @Column(name = "album")
    protected String album;
    

    /**
     * Constructor
     */
    public Foto(Long id, String url, String fotografo, String titolo, String album) {
        this.id = id;
        this.url = url;
        this.fotografo = fotografo;
        this.titolo = titolo;
        this.album = album;
    }

    /**
     * Empty Constructor
     */
     public Foto() {
    }

    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getFotografo() {
        return fotografo;
    }

    public void setFotografo(String fotografo) {
        this.fotografo = fotografo;
    }

 
    public String getTitolo() {
        return titolo;
    }

    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    
}
