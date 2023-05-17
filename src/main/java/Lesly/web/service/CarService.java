package Lesly.web.service;

import Lesly.web.model.Car;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    void saveCar(Car car);
    Car getCarById(long id);
    void deleteCarById(long id);
    Page<Car> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
