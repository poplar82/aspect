package com.topolski.testweek9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Start {
    List<Person> personList = new ArrayList<>();
    private final PersonRepo personRepo;

    @Autowired
    public Start(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        readFile();
        saveDB();
        readDB();
    }

    @CountAspect
    public void readFile() {
        File file = new File("DATA.csv");
        String[] strings;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                strings = sc.nextLine().split(",");
                personList.add(new Person(strings[0], strings[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @CountAspect
    public void saveDB() {
        personRepo.saveAll(personList);
    }

    @CountAspect
    public void readDB() {
        personList = personRepo.findAll();
    }
}
