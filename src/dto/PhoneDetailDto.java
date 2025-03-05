package dto;

public class PhoneDetailDto {
    private int phone_id;
    private String processor;
    private String ram;
    private String storage;
    private int battery;
    private int weight;

    public PhoneDetailDto(int phone_id, String processor, String ram, String storage, int battery, int weight) {
        this.phone_id = phone_id;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.battery = battery;
        this.weight = weight;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
