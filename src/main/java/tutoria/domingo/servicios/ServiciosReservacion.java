/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tutoria.domingo.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutoria.domingo.modelo.Reservacion;
import tutoria.domingo.reportes.ContadorClientes;
import tutoria.domingo.reportes.StatusReservas;
import tutoria.domingo.repositorio.RepositorioReservacion;

/**
 *
 * @author USUARIO
 */
@Service
public class ServiciosReservacion {
    @Autowired
    /**
     * 
     */
    private RepositorioReservacion metodosCrud;
    /**
     * 
     * @return 
     */
    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }
/**
 * 
 * @param reservationId
 * @return 
 */
    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }
/**
 * 
 * @param reservation
 * @return 
 */
    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> upd= metodosCrud.getReservation(reservation.getIdReservation());
            if(upd.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    /**
     * 
     * @param reservacion
     * @return 
     */
    public Reservacion update(Reservacion reservacion){
        if(reservacion.getIdReservation()!=null){
            Optional<Reservacion> upd= metodosCrud.getReservation(reservacion.getIdReservation());
            if(!upd.isEmpty()){

                if(reservacion.getStartDate()!=null){
                    upd.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    upd.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    upd.get().setStatus(reservacion.getStatus());
                }
                metodosCrud.save(upd.get());
                return upd.get();
            }else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }
/**
 * 
 * @param reservationId
 * @return 
 */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
     public StatusReservas getReporteStatusReservaciones(){
        List<Reservacion>completed= metodosCrud.ReservacionStatus("completed");
        List<Reservacion>cancelled= metodosCrud.ReservacionStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }
    
    public List<Reservacion> getReportesTiempoReservaciones(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return metodosCrud.ReservacionTiempo(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }  
    
    public List<ContadorClientes> servicioTopClientes(){
        return metodosCrud.getTopClientes();
    }
}
