package se02.task06;

public class Main {
    public static boolean nuclearStrikeAsked;
    public static boolean nuclerWarBegan;

    public static void main(String[] args) {
        AtomicSubmarine submarine = AtomicSubmarine.getInstance();
        submarine.goToSea();

        System.out.println("\n================ Results =================");
        System.out.println("Enemies detected: " + AtomicSubmarine.enemiesDetected);
        System.out.println("Enemies terminated: " + AtomicSubmarine.enemiesTerminated);
        if (nuclerWarBegan) {
            System.out.println("\nCongratulations!!! The world is destroyed!");
        }
        System.out.println("==========================================");
    }
}