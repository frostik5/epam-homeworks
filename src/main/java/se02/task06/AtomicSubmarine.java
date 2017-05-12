package se02.task06;

import java.util.Scanner;
import se02.task07.*;

public class AtomicSubmarine {
    @XField
    public static int enemiesTerminated;
    @XField
    public static int enemiesDetected;
    @XField
    private static AtomicSubmarine submarine;

    @XField
    private int autonomy;
    @XField
    private Engine engine;

    @XConstructor(name = "Dmitrii Donskoi")
    private AtomicSubmarine() {
        this.autonomy = 60;
        engine = new Engine(10);
    }

    public static AtomicSubmarine getInstance() {
        if (submarine == null)
            submarine = new AtomicSubmarine();
        return submarine;
    }

    void goToSea() {
        int daysPast = 0;
        int rand;
        System.out.println("---Submarine went into the sea!---");
        while(true) {
            try {
                Thread.sleep(50 * engine.getPower());
                daysPast++;
                if (daysPast%5 == 0) {
                    System.out.printf("%d days past...\n", daysPast);
                }
                if (daysPast == autonomy) {
                    if (!Main.nuclerWarBegan) {
                        System.out.println("---Submarine crew experiences lack of food. Going back to port---");
                    } else {
                        System.out.println("---Submarine crew experiences lack of food. Going back to port, if it still exists...---");
                    }
                    Thread.sleep(2000);
                    break;
                }
                if (daysPast > 15) {
                    rand = (int)(Math.random()*100);
                    if (rand > 93 && rand <= 97) {
                        System.out.println("---Enemy ship detected!!!---");
                        enemiesDetected++;
                        useWeapon(0);
                    }
                    if (rand > 97) {
                        System.out.println("---Enemy submarine detected!!!---");
                        enemiesDetected++;
                        useWeapon(0);
                    }
                }
                if (daysPast > 50 && !Main.launchANuclearWeaponAsked) {
                    useWeapon(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void useWeapon(int torpedoOrAirstrike) {
        Scanner sc = new Scanner(System.in);
        String text;
        int rand;
        String[] torpedoOrNuclerWeaponStr = {
                "Do you wish to launch a torpedo (y/n)? ",
                "Do you wish to launch a nuclear weapon (y/n)? "
        };


        System.out.print(torpedoOrNuclerWeaponStr[torpedoOrAirstrike]);
        if (torpedoOrAirstrike == 1) {
            Main.launchANuclearWeaponAsked = true;
        }

        cycle:
        while (true) {
            text = sc.next();
            switch (text) {
                case "y":
                    if (torpedoOrAirstrike == 0) {
                        rand = (int) (Math.random() * 100);
                        if (rand > 15) {
                            enemiesTerminated++;
                            System.out.println("---You got it!---");
                            break cycle;
                        } else {
                            System.out.println("---You missed!---");
                            break cycle;
                        }
                    } else {
                        Main.nuclerWarBegan = true;
                        System.out.println("---You've started a nuclear war!---");
                        break cycle;
                    }
                case "n":
                    if (torpedoOrAirstrike == 1) {
                        System.out.println("---You've made a right decision!---");
                    }
                    break cycle;
                default:
                    System.out.print("Please, give it another try: ");
                    break;
            }
        }
    }

    class Engine {
        private int power;

        public Engine(int power) {
            this.power = power;
        }

        public int getPower() {
            return power;
        }
    }
}
