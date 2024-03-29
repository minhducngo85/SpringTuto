package com.redcrystal.example.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.redcrystal.example.model.Car;

@Service
@ApplicationScoped
@ManagedBean
public class CarService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6713814427955613904L;

	/** Logger object */
	private static final Logger LOGGER = Logger.getLogger(CarService.class);

	private final static String[] colors;

	private final static String[] brands;

	public CarService() {
		LOGGER.info("CarService Constructor");
	}

	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";

		brands = new String[10];
		brands[0] = "BMW";
		brands[1] = "Mercedes";
		brands[2] = "Volvo";
		brands[3] = "Audi";
		brands[4] = "Renault";
		brands[5] = "Fiat";
		brands[6] = "Volkswagen";
		brands[7] = "Honda";
		brands[8] = "Jaguar";
		brands[9] = "Ford";
	}

	public List<Car> createCars(int size) {
		LOGGER.debug("Create " + size + " new cars");
		List<Car> list = new ArrayList<Car>();
		for (int i = 0; i < size; i++) {
			list.add(new Car(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
		}
		return list;
	}

	private String getRandomId() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}

	private String getRandomColor() {
		return colors[(int) (Math.random() * 10)];
	}

	private String getRandomBrand() {
		return brands[(int) (Math.random() * 10)];
	}

	public int getRandomPrice() {
		return (int) (Math.random() * 100000);
	}

	public boolean getRandomSoldState() {
		return (Math.random() > 0.5) ? true : false;
	}

	public List<String> getColors() {
		return Arrays.asList(colors);
	}

	public List<String> getBrands() {
		return Arrays.asList(brands);
	}
}
