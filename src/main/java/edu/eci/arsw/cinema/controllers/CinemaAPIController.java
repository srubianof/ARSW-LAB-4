/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cristian
 */
@RestController
@RequestMapping(value = "/")
public class CinemaAPIController {

    @Autowired
    CinemaServices cinemaServices;

    @GetMapping("/cinema")
    public ResponseEntity<?> cinemas() {
        try {
            Set<Cinema> data = cinemaServices.getAllCinemas();
            for (Cinema c : data
            ) {
                System.out.println(c);
            }
            //obtener datos que se enviarán a través del API
            System.out.println("mamabisha");

            return new ResponseEntity<Set<Cinema>>(data, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }
}
    

