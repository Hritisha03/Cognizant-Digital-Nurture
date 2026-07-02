class Computer {

    private String cpu;
    private int ram;
    private int storage;

    private Computer(Builder builder) {
        cpu = builder.cpu;
        ram = builder.ram;
        storage = builder.storage;
    }

    void display() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Storage: " + storage + " GB");
        System.out.println();
    }

    static class Builder {
        private String cpu;
        private int ram;
        private int storage;

        Builder setCPU(String cpu) {
            this.cpu = cpu;
            return this;
        }

        Builder setRAM(int ram) {
            this.ram = ram;
            return this;
        }

        Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        Computer build() {
            return new Computer(this);
        }
    }
}

public class ExThree {

    public static void main(String[] args) {

        Computer c1 = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM(8)
                .setStorage(512)
                .build();

        Computer c2 = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM(16)
                .setStorage(1024)
                .build();

        System.out.println("Computer 1");
        c1.display();

        System.out.println("Computer 2");
        c2.display();
    }
}