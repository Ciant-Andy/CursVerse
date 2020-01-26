package by.iba.gomel.controllers;

import by.iba.gomel.entity.Verse;
import by.iba.gomel.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class VerseController {
    @Autowired
    VerseService verseService;

    @MessageMapping("/sendverse")
    @SendTo("/topic/activity")
    public  Verse change(Verse verse){
        return verseService.save(verse);
    }


    @GetMapping("/verse/{id}")
    public Verse getVerse(@PathVariable Long id){
        return verseService.findByVerseId(id).get();
    }

    @PostMapping("/verse")
    public Verse addVerse(@RequestBody Verse verse){
        return verseService.save(verse);
    }
}
