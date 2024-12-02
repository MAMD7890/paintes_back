package inventario.back.Controller;


import inventario.back.Entity.Location;
import inventario.back.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Integer id) {
        return locationService.findById(id);
    }

    @PostMapping
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        locationService.deleteById(id);
    }
}
