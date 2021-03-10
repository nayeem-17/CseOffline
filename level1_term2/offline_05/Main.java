import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CarDBMS.loadData();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Select one option:");
            System.out.println("\t(1) Search Cars");
            System.out.println("\t(2) Add Car");
            System.out.println("\t(3) Delete Car");
            System.out.println("\t(4) Exit System");
            int option = scan.nextInt();
            if (option == 4) {
                System.out.println("Exiting the system...");
                CarDBMS.saveAllData();
                return;
            } else if (option == 1) {
                while (true) {
                    System.out.println("What criteria(s) you want to use to search for cars?");
                    System.out.println("\t(1) By Registration Number");
                    System.out.println("\t(2) By Car Make and Car Model");
                    System.out.println("\t(3) Back to Main Menu");
                    int searchOption = scan.nextInt();
                    if (searchOption == 3) {
                        break;
                    } else if (searchOption == 1) {
                        System.out.println("Enter the registration Number: ");
                        String regNum = scan.next();
                        List<Car> ar = CarDBMS.searchCars(new String[] { regNum });
                        if (ar.size() != 0) {
                            Car temp = ar.get(0);
                            System.out.println(temp);
                        } else {
                            System.out.println("Car not found!");
                        }
                        break;
                    } else if (searchOption == 2) {
                        System.out.println("Enter the CarMake: ");
                        String carMake = scan.next();
                        System.out.println("Enter the carModel: ");
                        String carModel = scan.next();
                        if (carModel.equals("ANY")) {
                            String[] models = CarDBMS.getCarModels();
                            for (String temp : models) {
                                System.out.println("model: " + temp);
                            }
                            carModel = scan.next();
                        }
                        List<Car> tempCars = CarDBMS.searchCars(new String[] { carModel, carMake });
                        if (tempCars.size() != 0) {
                            for (Car temp : tempCars) {
                                System.out.println(temp);
                            }
                        } else {
                            System.out.println("Car not found!");
                        }

                        break;
                    }
                }
            } else if (option == 2) {
                Car temp = new Car();
                System.out.println("Enter the car's Registration Number: ");
                String regNo = scan.next();
                temp.setRegistrationNumber(regNo);
                System.out.println("Enter the car's YearMade:");
                temp.setYearMade(scan.nextInt());
                System.out.println("Enter the first color:");
                String[] colors = new String[3];
                colors[0] = scan.next();
                System.out.println("Enter the second color:");
                colors[1] = scan.next();
                System.out.println("Enter the third color:");
                colors[2] = scan.next();
                temp.setColorOfCar(colors);
                System.out.println("Enter the CarMake");
                temp.setCarMake(scan.next());
                System.out.println("Enter the carModel");
                temp.setCarModel(scan.next());
                System.out.println("Enter the carPrice");
                temp.setPriceOfCar(scan.nextInt());
                CarDBMS.addCar(temp);

            } else if (option == 3) {
                System.out.println("Enter the registration No. of the car you want to delete: ");
                String regNo = scan.next();
                CarDBMS.deleteCar(regNo);
                System.out.println("Deleted Car info of Register No. " + regNo);
            } else {
                System.out.println("Error-message : Please select any option between 1-4");
            }
        }
    }
}
