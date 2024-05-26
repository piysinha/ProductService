package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Employee;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/greet")
    public String sayGreet(@RequestParam ("name") String name){
        System.out.println("Reached inside sayGreet of HelloController");
        return "Hello, Good Morning "+ name;
    }

    @GetMapping("/")
    public String defaultHello(){
        return "Hello from default";
    }
    @GetMapping("/say/{YourName}/{YourCity}")
    public String sayHello(@PathVariable("YourName") String name, @PathVariable("YourCity") String city){
        return "Hello " + name + " from " + city;
    }
    @PostMapping("/create/something")
    public Integer postExample(@RequestBody Employee employee){
        return employee.getSalary();
    }
}
