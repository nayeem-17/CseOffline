import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarDBMS {
    private static List<Car> cars = new ArrayList<Car>();

    public static void loadData() {
        String fileName = "cars.txt";
        String[] infos;
        String data;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (true) {
                Car temp = new Car();
                data = br.readLine();
                if (data == null)
                    return;
                infos = data.split(",", 8);
                temp.setRegistrationNumber(infos[0]);
                temp.setYearMade(Integer.parseInt(infos[1]));
                temp.setColorOfCar(new String[] { infos[2], infos[3], infos[4] });
                temp.setCarMake(infos[5]);
                temp.setCarModel(infos[6]);
                temp.setPriceOfCar(Integer.parseInt(infos[7]));
                cars.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Car> searchCars(String[] keys) {
        ArrayList<Car> searchedCars = new ArrayList<Car>();
        int datalen = keys.length;
        if (datalen == 1) {
            String registrationNumber = keys[0];
            for (int i = 0; i < cars.size(); i++) {
                if (registrationNumber.toLowerCase().equals(cars.get(i).getRegistrationNumber().toLowerCase())) {
                    searchedCars.add(cars.get(i));
                }
            }
        } else if (datalen == 2) {
            String carModel = keys[0];
            String carMake = keys[1];
            for (int i = 0; i < cars.size(); i++) {
                if (carModel.toLowerCase().equals(cars.get(i).getCarModel().toLowerCase())
                        && carMake.toLowerCase().equals(cars.get(i).getCarMake().toLowerCase())) {
                    searchedCars.add(cars.get(i));
                }
            }
        }
        return searchedCars;
    }

    public static void addCar(Car newCar) {
        for (Car c : cars) {
            if (c.getRegistrationNumber().toLowerCase().equals(newCar.getRegistrationNumber().toLowerCase())) {
                System.out.println("Error-message: " + "The car with same registration Number exists!");
                return;
            }
        }
        cars.add(newCar);
    }

    public static String[] getCarModels() {
        String[] models;
        int size = cars.size();
        models = new String[size];
        for (int i = 0; i < size; i++) {
            models[i] = cars.get(i).getCarModel();
        }
        return models;
    }

    public static void saveAllData() {
        String fileName = "cars.txt";
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < cars.size(); i++) {
                Car temp = cars.get(i);
                String data = temp.getRegistrationNumber() + "," + temp.getYearMade() + "," + temp.getColorOfCar()[0]
                        + "," + temp.getColorOfCar()[1] + "," + temp.getColorOfCar()[2] + "," + temp.getCarMake() + ","
                        + temp.getCarModel() + "," + temp.getPriceOfCar();
                br.write(data);
                br.newLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAllData() {
        int size = cars.size();
        System.out.println(size);
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    public static void deleteCar(String registrationNumber) {
        for (int i = 0; i < cars.size(); i++) {
            if (registrationNumber.equals(cars.get(i).getRegistrationNumber())) {
                cars.remove(i);
            }
        }
    }
}
