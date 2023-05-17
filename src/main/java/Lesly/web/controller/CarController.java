package Lesly.web.controller;

import Lesly.web.model.Car;
import Lesly.web.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

	@Autowired
	private CarService carService;

	// Display list of cars
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "carName", "asc", model);
	}

	// Show form for creating a new car
	@GetMapping("/showNewCarForm")
	public String showNewCarForm(Model model) {
		// Create model attribute to bind form data
		Car car = new Car();
		model.addAttribute("car", car);
		return "new_car";
	}

	// Save a new car
	@PostMapping("/saveCar")
	public String saveCar(@ModelAttribute("car") Car car) {
		// Save car to the database
		carService.saveCar(car);
		return "redirect:/";
	}

	// Show form for updating a car
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// Get car from the service
		Car car = carService.getCarById(id);
		// Set car as a model attribute to pre-populate the form
		model.addAttribute("car", car);
		return "update_car";
	}

	// Update a car
	@PostMapping("/showFormForUpdate/{id}")
	public String updateCar(@PathVariable(value = "id") long id, @ModelAttribute("car") Car updatedCar) {
		// Get the existing car from the service
		Car existingCar = carService.getCarById(id);

		// Set the updated values to the existing car
		existingCar.setCarName(updatedCar.getCarName());
		existingCar.setCarPlateNumber(updatedCar.getCarPlateNumber());
		existingCar.setStatus(updatedCar.getStatus());
		existingCar.setServiceFee(updatedCar.getServiceFee());
		existingCar.setCustomerName(updatedCar.getCustomerName());

		// Save the updated car to the database
		carService.saveCar(existingCar);

		return "redirect:/";
	}

	// Delete a car
	@GetMapping("/deleteCar/{id}")
	public String deleteCar(@PathVariable(value = "id") long id) {
		// Call delete car method
		carService.deleteCarById(id);
		return "redirect:/";
	}

	// Paginate and display the list of cars
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {
		int pageSize = 5;

		Page<Car> page = carService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Car> listCars = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listCars", listCars);
		return "index";
	}
}
