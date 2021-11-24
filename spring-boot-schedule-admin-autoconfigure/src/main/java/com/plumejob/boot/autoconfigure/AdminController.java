package com.plumejob.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("plumejobAdminController")
@RequestMapping("ssa")
public class AdminController {

    @Autowired
    private PlumejobManager plumejobManager;

    @RequestMapping("/list")
    public ResponseEntity<List<PlumeJobDTO>> list() {
        return ResponseEntity.ok(plumejobManager.listJob());
    }


    @RequestMapping("/run")
    public ResponseEntity<?> run(String jobName) {
        plumejobManager.run(jobName);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/cancel")
    public ResponseEntity<?> cancel(String jobName) {
        plumejobManager.suspend(jobName);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/start")
    public ResponseEntity<?> start(String jobName) {
        plumejobManager.start(jobName);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/update")
    public Object update(String jobName, String expression) throws ClassNotFoundException, NoSuchMethodException {
        if (!CronSequenceGenerator.isValidExpression(expression)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        plumejobManager.update(jobName, expression);
        return ResponseEntity.ok().build();
    }


}
