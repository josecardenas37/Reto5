/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "cabin")
public class Cabin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String brand;
    /**
     * 
     */
    private Integer rooms;
    /**
     * 
     */
    private String description;
    @ManyToOne
    @JoinColumn(name="categoryid")
    @JsonIgnoreProperties("cabins")
    private Categoria category;
      
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cabin")
    @JsonIgnoreProperties({"cabin","client"})
    private List<Mensaje> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "cabin")
    @JsonIgnoreProperties({"cabin","message"})
    public List<Reservacion> reservations;
/**
 * 
 * @return 
 */
    public Integer getId() {
        return id;
    }
/**
 * 
 * @param id 
 */
    public void setId(Integer id) {
        this.id = id;
    }
/**
 * 
 * @return 
 */
    public String getBrand() {
        return brand;
    }
/**
 * 
 * @param brand 
 */
    public void setBrand(String brand) {
        this.brand = brand;
    }
/**
 * 
 * @return 
 */
    public Integer getRooms() {
        return rooms;
    }
/**
 * 
 * @param rooms 
 */
    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Categoria getCategory() {
        return category;
    }
/**
 * 
 * @param category 
 */
    public void setCategory(Categoria category) {
        this.category = category;
    }
/**
 * 
 * @return 
 */
    public String getName() {
        return name;
    }
/**
 * 
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * 
 * @return 
 */
    public String getDescription() {
        return description;
    }
/**
 * 
 * @param description 
 */
    public void setDescription(String description) {
        this.description = description;
    }
/**
 * 
 * @return 
 */
    public List<Mensaje> getMessages() {
        return messages;
    }
/**
 * 
 * @param messages 
 */
    public void setMessages(List<Mensaje> messages) {
        this.messages = messages;
    }
/**
 * 
 * @return 
 */
    public List<Reservacion> getReservations() {
        return reservations;
    }
/**
 * 
 * @param reservations 
 */
    public void setReservations(List<Reservacion> reservations) {
        this.reservations = reservations;
    }
     
}
