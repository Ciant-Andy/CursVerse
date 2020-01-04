package by.iba.gomel.service;

import by.iba.gomel.entity.Verse;
import by.iba.gomel.repository.VerseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class VerseService {
    @Autowired
    VerseRepository repository;

    public <S extends Verse> S save(S verse) {
        log.info("Saving Verse" + verse);
        return repository.save(verse);
    }

    @Transactional(readOnly = true)
    public Optional<Verse> findByVerseId(Long aLong) {
        log.info("Getting Verse by Id " + aLong);
        return repository.findById(aLong);
    }
}
