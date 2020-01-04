package by.iba.gomel.controllers;

import by.iba.gomel.entity.Verse;
import by.iba.gomel.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class VerseController {
    @Autowired
    VerseService verseService;
/*
    @MessageMapping("/sendverse")
    @SendTo("/topic/activity")
    public  String change(String text){
        return text;
    }
    */

    @GetMapping("/verse/{id}")
    public Optional<Verse> getVerse(@PathVariable Long id){
        return verseService.findByVerseId(id);
    }

    @PostMapping("/verse")
    public Verse addVerse(@RequestBody Verse verse){
        return verseService.save(verse);
    }
}
