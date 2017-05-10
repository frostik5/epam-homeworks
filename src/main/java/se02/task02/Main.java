package se02.task02;

public class Main {
    /**
     * Напишите приложение, позволяющее вести учет канцелярских товаров на рабочем месте сотрудника. Определите полную стоимость канцтоваров у определенного сотрудника.
     */
    public static void main(String[] args) {
        Employee headOfDepartment = new Employee("Alexandr", "Head of Department", 40, StationeryPack.FULL);
        Employee juniorAccountant = new Employee("Sofia", "Junior Accountant", 25, StationeryPack.EXTRA);
        Employee leadEconomist = new Employee("Roger", "Lead Economist", 33, StationeryPack.MEDIUM);
        Employee juniorEngineer = new Employee("Patrick", "Junior Engineer", 21, StationeryPack.MINIMUM);

        System.out.printf("Head of Department stationery total price:\t %d руб.\n", headOfDepartment.calcStationeryPrice());
        System.out.printf("Junior Accountant stationery total price:\t %d руб.\n", juniorAccountant.calcStationeryPrice());
        System.out.printf("Lead Economist stationery total price:\t\t %d руб.\n", leadEconomist.calcStationeryPrice());
        System.out.printf("Junior Engineer stationery total price:\t\t %d руб.\n", juniorEngineer.calcStationeryPrice());
    }
}
