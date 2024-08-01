package BuilderPatternDemo;

class Car {

  // Required parameters
  private String engine;
  private int wheels;

  // Optional parameters
  private boolean airbags;
  private boolean sunroof;
  private boolean gps;
  private int year;

  private Car(CarBuilder builder) {
    this.engine = builder.engine;
    this.wheels = builder.wheels;
    this.airbags = builder.airbags;
    this.sunroof = builder.sunroof;
    this.gps = builder.gps;
    this.year = builder.year;
  }

  // Getters
  public String getEngine() {
    return engine;
  }

  public int getWheels() {
    return wheels;
  }

  public boolean hasAirbags() {
    return airbags;
  }

  public boolean hasSunroof() {
    return sunroof;
  }

  public boolean hasGPS() {
    return gps;
  }

  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return "Car [engine=" + engine + ", wheels=" + wheels + ", airbags=" + airbags + ", sunroof="
        + sunroof + ", GPS=" + gps + ", year=" + year + "]";
  }

  // Static Builder Class
  public static class CarBuilder {

    private String engine;
    private int wheels;

    private boolean airbags;
    private boolean sunroof;
    private boolean gps;
    private int year = 2022;  // Default value

    public CarBuilder(String engine, int wheels) {
      this.engine = engine;
      this.wheels = wheels;
    }

    public CarBuilder setAirbags(boolean airbags) {
      this.airbags = airbags;
      return this;
    }

    public CarBuilder setSunroof(boolean sunroof) {
      this.sunroof = sunroof;
      return this;
    }

    public CarBuilder setGPS(boolean gps) {
      this.gps = gps;
      return this;
    }

    public CarBuilder setYear(int year) {
      this.year = year;
      return this;
    }

    public Car build() {
      return new Car(this);
    }
  }
}

public class CarBuilderDemo {

  public static void main(String[] args) {
    Car car = new Car.CarBuilder("V8", 4)
        .setAirbags(true)
        .setSunroof(true)
        .build();
    System.out.println(car);

    Car anotherCar = new Car.CarBuilder("V6", 9)
        .setYear(2023)
        .build();
    System.out.println(anotherCar);
  }
}
