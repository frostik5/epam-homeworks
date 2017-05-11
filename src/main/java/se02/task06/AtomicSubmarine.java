package se02.task06;

import java.util.Scanner;
import se02.task07.*;

public class AtomicSubmarine implements Runnable {
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
        Thread t = new Thread(this);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
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
                        launchTorpedo();
                    }
                    if (rand > 97) {
                        System.out.println("---Enemy submarine detected!!!---");
                        enemiesDetected++;
                        launchTorpedo();
                    }
                }
                if (daysPast > 50 && !Main.nuclearStrikeAsked) {
                    launchNuclearWeapon();
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    private void launchTorpedo() {
        Scanner sc = new Scanner(System.in);
        String text;
        int rand;
        System.out.print("Do you wish to launch a torpedo (y/n)? ");
        while(true) {
            text = sc.next();
            if (!text.equals("y") && !text.equals("n")) {
                System.out.print("Please, give it another try: ");
            } else if (text.equals("y")) {
                rand = (int)(Math.random()*100);
                if (rand > 15) {
                    enemiesTerminated++;
                    System.out.println("---You got it!---");
                    break;
                } else {
                    System.out.println("---You missed!---");
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void launchNuclearWeapon() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String text;
        int rand;
        System.out.print("Do you wish to launch a nuclear weapon (y/n)? ");
        Main.nuclearStrikeAsked = true;
        while(true) {
            text = sc.next();
            if (!text.equals("y") && !text.equals("n")) {
                System.out.print("Please, give it another try: ");
            } else if (text.equals("y")) {
                Main.nuclerWarBegan = true;
                System.out.println("---You've started a nuclear war!---");
                break;
            } else {
                System.out.println("---You've made a right decision!---");
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

        public void setPower(int power) {
            this.power = power;
        }
    }
}
