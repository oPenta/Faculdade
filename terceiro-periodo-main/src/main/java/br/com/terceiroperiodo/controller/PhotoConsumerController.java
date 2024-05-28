package br.com.terceiroperiodo.controller;

import br.com.terceiroperiodo.service.PhotoConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/photo")
public class PhotoConsumerController {

    @Autowired
    PhotoConsumerService messageConsumerService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarPhotoById(@PathVariable Long id){
        return messageConsumerService.buscarPhotoPorId(id);
    }

}
