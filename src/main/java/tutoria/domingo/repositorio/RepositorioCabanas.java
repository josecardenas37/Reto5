/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tutoria.domingo.modelo.Mensaje;
import tutoria.domingo.modelo.Cabin;
import tutoria.domingo.interfaces.InterfaceCabin;

/**
 *
 * @author USUARIO
 */
@Repository
public class RepositorioCabanas {
     @Autowired
    private InterfaceCabin crud;
    

    public List<Cabin> getAll(){
        return (List<Cabin>) crud.findAll();       
    }
    
    public Optional <Cabin> getLacabana(int id){
        return crud.findById(id);
    }
    
    public Cabin save(Cabin cabin){
        return crud.save(cabin);
    }
      public void delete(Cabin cabin){
        crud.delete(cabin);
    }
    
    
}
