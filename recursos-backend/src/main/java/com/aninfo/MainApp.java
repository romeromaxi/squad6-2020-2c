package com.aninfo;

import com.aninfo.recursos.model.Resource;
import com.aninfo.recursos.service.ResourceService;

import com.aninfo.hours.model.Hours;
import com.aninfo.hours.service.HoursService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Collection;
import java.util.Vector;

@RestController
@SpringBootApplication
public class MainApp extends SpringBootServletInitializer {
    private ResourceService resourceService = new ResourceService();

    @Autowired
    private HoursService hoursService;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @GetMapping("/resources")
    public Vector<Resource> readFromExternalSystem() {
        return resourceService.readFromExternalSystem();
    }

    @PostMapping("/hours")
    @ResponseStatus(HttpStatus.CREATED)
    public Hours createAccount(@RequestBody Hours hours) {
        return hoursService.saveHours(hours);
    }

    @GetMapping("/hours")
    public Collection<Hours> getHours(){
        return hoursService.getHours();
    }
}
