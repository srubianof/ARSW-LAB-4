/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author cristian
 */
@RestController
@RequestMapping(value = "/")
public class CinemaAPIController {

    @Autowired
    CinemaServices cinemaServices;

    @GetMapping("/cinemas")
    public ResponseEntity<?> cinemas() {
        try {
            Set<Cinema> data = cinemaServices.getAllCinemas();
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cinemas/{name}")
    public ResponseEntity<?> cinemaByName(@PathVariable String name) {
        try {
            Cinema data = cinemaServices.getCinemaByName(name);
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not cinema found.", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/cinemas/{name}/{date}")
    public ResponseEntity<?> cinemaByNameAndDate(@PathVariable String name, @PathVariable String date) {
        try {
            List<CinemaFunction> data = cinemaServices.getFunctionsByCinemaAndDate(name,date);
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found.", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/cinemas/{name}/{date}/{movieName}")
    public ResponseEntity<?> cinemaByNameDateAndMovieName(@PathVariable String name, @PathVariable String date, @PathVariable String movieName) {
        try {
            CinemaFunction data = cinemaServices.getFunctionByCinemaDateAndMovieName(name,date,movieName);
            //obtener datos que se enviarán a través del API

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cinemas/{name}")
    public ResponseEntity<?> addFunctionToCinema(@RequestBody CinemaFunction cinemaFunction, @PathVariable String name){
        try {
            //registrar dato
            cinemaServices.addFunctionToCinema(name,cinemaFunction);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not possible, failed to create finction",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PutMapping("/cinemas/{name}")
    public ResponseEntity<?> updateCinemaFunction(@RequestBody CinemaFunction cinemaFunction, @PathVariable String name) {
        //@PathVariable
        try{
            return new ResponseEntity<>(cinemaServices.updateOrCreateFuncion(name,cinemaFunction),HttpStatus.CREATED);

        }
        catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not possible, failed to find cinema",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
    

