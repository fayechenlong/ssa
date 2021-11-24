package com.plumejob.boot.autoconfigure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlumejobPageController {
    @RequestMapping("/ssa")
    public String index() {
        return "ssa";
    }
}
