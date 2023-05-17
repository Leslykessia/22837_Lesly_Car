package Lesly.web.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "car_name")
	private String carName;

	@Column(name = "car_plate_number")
	private String carPlateNumber;

	@Column(name = "status")
	private String status;

	@Column(name = "service_fee")
	private String serviceFee;

	@Column(name = "customer_name")
	private String customerName;

	// Constructors

	public Car() {
		// Default constructor
	}

	public Car(String carName, String carPlateNumber, String status, String serviceFee, String customerName) {
		this.carName = carName;
		this.carPlateNumber = carPlateNumber;
		this.status = status;
		this.serviceFee = serviceFee;
		this.customerName = customerName;
	}

	// Getters and Setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarPlateNumber() {
		return carPlateNumber;
	}

	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	// toString method

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", carName='" + carName + '\'' +
				", carPlateNumber='" + carPlateNumber + '\'' +
				", status='" + status + '\'' +
				", serviceFee='" + serviceFee + '\'' +
				", customerName='" + customerName + '\'' +
				'}';
	}
}
