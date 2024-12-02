package inventario.back.service;

import inventario.back.Entity.Worker;
import inventario.back.Repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(Integer id) {
        return workerRepository.findById(id).orElse(null);
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public void deleteById(Integer id) {
        workerRepository.deleteById(id);
    }
}
