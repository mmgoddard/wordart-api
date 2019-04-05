package nz.net.example.wordart.gen.controller;

import nz.net.example.wordart.gen.domain.Job;
import nz.net.example.wordart.gen.service.WordArtService;
import nz.net.example.wordart.gen.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WordArtController {

    @Autowired
    private WordArtService wordArtService;

    @RequestMapping(value = "/wordart", method = RequestMethod.GET)
    public ResponseEntity<Object> generateWordArtImage(@Valid Job job) {
        return new ServiceResponse()
                .getServiceResponse(job, wordArtService.generateWordArtImage(job));
    }
}