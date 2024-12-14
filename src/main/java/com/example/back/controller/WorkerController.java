package com.example.back.controller;


import com.example.back.entity.Worker;
import com.example.back.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Integer id) {
        Worker worker = workerService.findById(id);
        return worker != null ? ResponseEntity.ok(worker) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Integer id, @RequestBody Worker updatedWorker) {
        Worker existingWorker = workerService.findById(id);
        if (existingWorker == null) {
            return ResponseEntity.notFound().build();
        }
        existingWorker.setName(updatedWorker.getName());
        existingWorker.setUsername(updatedWorker.getUsername());
        existingWorker.setPassword(updatedWorker.getPassword());
        existingWorker.setPosition(updatedWorker.getPosition());
        return ResponseEntity.ok(workerService.save(existingWorker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Integer id) {
        if (workerService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        workerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
