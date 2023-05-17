package Lesly.web.service;

import Lesly.web.model.Car;
import Lesly.web.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public void saveCar(Car car) {
		carRepository.save(car);
	}

	@Override
	public Car getCarById(long id) {
		Optional<Car> optional = carRepository.findById(id);
		Car car = null;
		if (optional.isPresent()) {
			car = optional.get();
		} else {
			throw new RuntimeException("Car not found for id :: " + id);
		}
		return car;
	}

	@Override
	public void deleteCarById(long id) {
		carRepository.deleteById(id);
	}

	@Override
	public Page<Car> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return carRepository.findAll(pageable);
	}
}
