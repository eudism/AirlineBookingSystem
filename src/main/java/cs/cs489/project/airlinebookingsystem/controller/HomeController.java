package cs.cs489.project.airlinebookingsystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

  @GetMapping(value={"","/"})
  public String home() {
    return "Hello";
  }
}
