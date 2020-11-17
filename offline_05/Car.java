public class Car {
    private String registrationNumber;
    private int yearMade;
    private String[] colorOfCar;
    private String carMake;
    private String carModel;
    private int priceOfCar;

    public Car() {
        colorOfCar = new String[3];
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getPriceOfCar() {
        return priceOfCar;
    }

    public void setPriceOfCar(int priceOfCar) {
        this.priceOfCar = priceOfCar;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String[] getColorOfCar() {
        return colorOfCar;
    }

    public void setColorOfCar(String[] colorOfCar) {
        // this.colorOfCar = new String[3];
        this.colorOfCar = colorOfCar;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "The regNo is " + registrationNumber + ",yearMade is " + yearMade + ",colors are  1. " + colorOfCar[0]
                + ", 2. " + colorOfCar[1] + ", 3. " + colorOfCar[2] + ", carMake is " + carMake + ", carModel is "
                + carModel + " and the price is " + priceOfCar;
    }
}
