/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.domingo.modelo.Cabin;
import tutoria.domingo.repositorio.RepositorioCabanas;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosCabanas {
     @Autowired
    private RepositorioCabanas metodosCrud;
    
    public List<Cabin> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Cabin> getLacabana(int idLacabana){
        return metodosCrud.getLacabana(idLacabana);
    }
    
    public Cabin save(Cabin lacabana){
        if(lacabana.getId()==null){
            return metodosCrud.save(lacabana);
        }else{
            Optional<Cabin> evt=metodosCrud.getLacabana(lacabana.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(lacabana);
            }else{
                return lacabana;
            }
        }
    }
    public Cabin update(Cabin cabin){
        if(cabin.getId()!=null){
            Optional<Cabin> e=metodosCrud.getLacabana(cabin.getId());
            if(!e.isEmpty()){
                if(cabin.getName()!=null){
                    e.get().setName(cabin.getName());
                }
                if(cabin.getBrand()!=null){
                    e.get().setBrand(cabin.getBrand());
                }
                if(cabin.getRooms()!=null){
                    e.get().setRooms(cabin.getRooms());
                }
                if(cabin.getDescription()!=null){
                    e.get().setDescription(cabin.getDescription());
                }
                if(cabin.getCategory()!=null){
                    e.get().setCategory(cabin.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return cabin;
            }
        }else{
            return cabin;
        }
    }


    public boolean deleteCabin(int cabinId) {
        Boolean aBoolean = getLacabana(cabinId).map(cabin -> {
            metodosCrud.delete(cabin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
