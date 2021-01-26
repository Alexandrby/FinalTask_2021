package com.senla.controllers;

import com.senla.dto.PageDTO;
import com.senla.dto.SeasonTicketDTO;
import com.senla.services.SeasonTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeasonTicketController {

    private Logger logger = LoggerFactory.getLogger(SeasonTicketController.class);

    private SeasonTicketService service;

    @Autowired
    public SeasonTicketController(SeasonTicketService service) {
        this.service = service;
    }

    @GetMapping("/user/seasonTicket/{id}")
    public ResponseEntity<SeasonTicketDTO> getSeasonTicketById(@PathVariable int id) {
        logger.info("find a seasonTicket by id {}", id);
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/user/seasonTicket")
    public ResponseEntity<PageDTO> getAllSeasonTickets(@RequestParam(value = "page") int page) {
        logger.info("find all seasonTickets");
        return new ResponseEntity<>(service.getAll(page), HttpStatus.OK);
    }

    @PostMapping("/admin/seasonTicket")
    public ResponseEntity<SeasonTicketDTO> saveSeasonTicket(@RequestBody SeasonTicketDTO seasonTicketDTO) {
        logger.info("save seasonTicket -- {}", seasonTicketDTO);
        return new ResponseEntity<>(service.save(seasonTicketDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admin/seasonTicket")
    public ResponseEntity<SeasonTicketDTO> updateSeasonTicket(@RequestBody SeasonTicketDTO seasonTicketDTO) {
        logger.info("update seasonTicket -- {}", seasonTicketDTO);
        return new ResponseEntity<>(service.update(seasonTicketDTO), HttpStatus.OK);
    }


    @DeleteMapping("/admin/seasonTicket/{id}")
    public ResponseEntity<String> deleteSeasonTicketById(@PathVariable int id) {
        logger.info("delete seasonTicket with id {}", id);
        service.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
