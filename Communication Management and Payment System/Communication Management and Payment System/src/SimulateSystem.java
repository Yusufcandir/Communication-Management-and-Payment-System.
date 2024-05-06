
import java.util.*;

public class SimulateSystem {

    private static final ArrayList<Subscriber> SUBSCRIBERS_LIST = new ArrayList<>();
    private static final ArrayList<ServiceProvider> SERVICE_PROVIDER_LIST = new ArrayList<>();
    private static final Scanner INPUT = new Scanner(System.in);
    private static int count = 1;

    public static void main(String[] args) {

        System.out.println("What options do want to choose");
        System.out.println("1- Creating a new Service Provider \n" + "2- Create a new Subscriber\n" + "3- Voice Call: A subscriber calls another subscriber\n"
                + "4- Messaging: A subscriber sends a message to another subscriber\n" + "5- Internet: A subscriber connects to the Internet\n"
                + "6- Pay Invoice: A subscriber pays his/her invoice\n" + "7- Change ServiceProvider: A subscriber changes his/her provider\n"
                + "8- Change Limit: A subscriber changes his/her usage limit for the Invoice\n" + "9- List all Subscribers\n"
                + "10- List all Service Providers\n" + "11- Exit"
        );
        
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        while (option < 1 || option > 11) {
            System.out.println("Please enter a number between 1 and 11");
            option = input.nextInt();
        }
        while (option >= 1 && option <= 11) {
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2();
                    break;
                case 3:
                    option3();
                    break;
                case 4:
                    option4();
                    break;
                case 5:
                    option5();
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                case 8:
                    option8();
                    break;
                case 9:
                    option9();
                    break;
                case 10:
                    option10();
                    break;
                case 11:
                    option11();
                    break;
            }
            System.out.println("What options do want to choose");
            System.out.println("1- Creating a new Service Provider \n" + "2- Create a new Subscriber\n" + "3- Voice Call: A subscriber calls another subscriber\n"
                    + "4- Messaging: A subscriber sends a message to another subscriber\n" + "5- Internet: A subscriber connects to the Internet\n"
                    + "6- Pay Invoice: A subscriber pays his/her invoice\n" + "7- Change ServiceProvider: A subscriber changes his/her provider\n"
                    + "8- Change Limit: A subscriber changes his/her usage limit for the Invoice\n" + "9- List all Subscribers\n"
                    + "10- List all Service Providers\n" + "11- Exit"
            );
            option = input.nextInt();
        }

    }

    public static void option1() {
        int[] values = new int[4];  // to store the values entered by user.
        System.out.println("To create a new service provider you must provide the voice call cost, "
                + "messaging cost,internet cost and discount ratio ");
        System.out.println("Please enter these values respectively!");
        for (int i = 0; i < 4; i++) {
            values[i] = INPUT.nextInt();
        }
        ServiceProvider provider = new ServiceProvider(setNameOfProviders(), values[0], values[1], values[2], values[3]); // since we don't have a name for the provider, we set a name
        SERVICE_PROVIDER_LIST.add(provider);
    }

    public static void option2() {
        double[] values = new double[3];
        String name = "";
        int counter = 0;

        System.out.println("To create a new subscriber you must provide the name, "
                + "age, " + "p_id (500 - 600) " + "and usage limit");
        System.out.println("Please enter these values respectively!");
        for (int i = 0; i < 3; i++) {
            //since we have not learned generics, we used this code to avoid complexity
            while (counter == 0) {
                name = INPUT.next();
                counter++;
            }
            values[i] = INPUT.nextDouble();
        }
        ServiceProvider provider = findProviderById((int) values[1]);
        if (provider == null) {
            System.out.println("A provider with id: " + values[1] + " does not exist!");
        } else {
            Subscriber subscriber = new Subscriber(name, (int) values[0], provider, values[2]);
            SUBSCRIBERS_LIST.add(subscriber);
        }

    }

    public static void option3() {
        System.out.println("To execute a voice call, you must provide the caller's id (1000000 - 9000000), receiver's id (1000000 - 9000000) and and call duration in minutes");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[3];
        for (int i = 0; i < 3; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber1 = findSubscriberById(values[0]);
        Subscriber subscriber2 = findSubscriberById(values[1]);
        if ((subscriber1 == null || subscriber2 == null)) {
            if (subscriber1 == null) {
                System.out.println("A subscriber with id: " + values[0] + " does not exist!");
            }
            if (subscriber2 == null) {
                System.out.println("A subscriber with id: " + values[1] + " does not exist!");
            }
        } else if (values[0] == values[1]) {
            System.out.println("A subscriber with id: " + values[0] + " cannot call himself/herself!");
        } else {
            subscriber1.makeVoiceCall(values[2], subscriber2);
        }

    }

    public static void option4() {
        System.out.println("To execute messaging, you must provide the sender's id, receiver's id and and amount of messages");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[3];
        for (int i = 0; i < 3; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber1 = findSubscriberById(values[0]);
        Subscriber subscriber2 = findSubscriberById(values[1]);
        if ((subscriber1 == null || subscriber2 == null)) {
            if (subscriber1 == null) {
                System.out.println("A subscriber with id: " + values[0] + " does not exist!");
            }
            if (subscriber2 == null) {
                System.out.println("A subscriber with id: " + values[1] + " does not exist!");
            }
        } else if (values[0] == values[1]) {
            System.out.println("A subscriber with id: " + values[0] + " cannot send message to himself/herself!");
        } else {
            subscriber1.sendMessage(values[2], subscriber2);
        }

    }

    public static void option5() {
        System.out.println("To execute connection to internet, you must provide subscriber's id and amount of internet usage");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[2];
        for (int i = 0; i < 2; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber = findSubscriberById(values[0]);
        if (subscriber == null) {
            System.out.println("A subscriber with id: " + values[0] + " does not exist!");
        } else {
            subscriber.connectToInternet(values[1]);
        }

    }

    public static void option6() {
        System.out.println("To pay the invoice , you must provide subscriber's id and amount of money to pay");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[2];
        for (int i = 0; i < 2; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber = findSubscriberById(values[0]);
        if (subscriber == null) {
            System.out.println("A subscriber with id: " + values[0] + " does not exist!");
        } else {
            subscriber.getInvoice().pay(values[1]);
        }
    }

    public static void option7() {
        System.out.println("To change the service provider, you must provide subscriber's id and provider's id of new subscriber");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[2];
        for (int i = 0; i < 2; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber = findSubscriberById(values[0]);
        ServiceProvider provider = findProviderById(values[1]);
        if ((subscriber == null || provider == null)) {
            if (subscriber == null) {
                System.out.println("A subscriber with id: " + values[0] + " does not exist!");
            }
            if (provider == null) {
                System.out.println("There does not exist a provider with id: " + values[1]);
            }
        } else if (subscriber.getS_provider().getP_id() == values[1]) {
            System.out.println("Subscriber with id: " + values[0] + " is already subscribed to this provider!");
        } else {
            subscriber.changeServiceProvider(provider);
          
        }
    }

    public static void option8() {
        System.out.println("To change the usage limit, you must provide subscriber's id and new limit");
        System.out.println("Please enter these values respectively!");
        int[] values = new int[2];
        for (int i = 0; i < 2; i++) {
            values[i] = INPUT.nextInt();
        }
        Subscriber subscriber = findSubscriberById(values[0]);
        if (subscriber == null) {
            System.out.println("A subscriber with id: " + values[0] + " does not exist!");
        } else if (values[1] == subscriber.getInvoice().getUsageLimit()) {
            System.out.println("Subscriber with id: " + values[0] + " already has this usage limit!");
        } else {
            subscriber.getInvoice().changeUsageLimit(values[1]);
        }
    }

    public static void option9() {
        if (SUBSCRIBERS_LIST.isEmpty()) {
            System.out.println("There are no subscribers yet!");
        } else {
            System.out.println("Subscribers:");
        }
        for (Subscriber s : SUBSCRIBERS_LIST) {
            System.out.println("s_id: " + s.getS_id() + ", isActive: " + s.IsActive()
                    + ", s_provider: " + s.getS_provider().toString() + ", invoice: " + s.getInvoice().toString());
        }
    }

    public static void option10() {
        if (SUBSCRIBERS_LIST.isEmpty()) {
            System.out.println("There are no service providers yet!");
        } else {
            System.out.println("Service Providers:");
        }
        for (ServiceProvider s : SERVICE_PROVIDER_LIST) {
            System.out.println("p_id: " + s.getP_id() + ", p_name: " + s.getP_name() + ", voiceCallCost: " + s.getVoiceCallCost() + ", messagingCost: "
                    + s.getMessagingCost() + ", internetCost: " + s.getInternetCost() + ", discountRatio: " + s.getDiscountRatio());
        }
    }

    public static void option11() {
        System.out.println("Providers:");
        outputOfProviders();
        System.out.println("Subscribes:");
        outputOfSubscribers();
        System.out.println("Stats:");
        mostAmountOfVoiceCall();
        mostNumberOfMessages();
        mostInternetUsage();
        System.exit(0);
    }

   //instead of using this code stack in each method, we created a separate method to be used in different methods.
    public static Subscriber findSubscriberById(int id) {
        for (int i = 0; i < SUBSCRIBERS_LIST.size(); i++) {
            if (SUBSCRIBERS_LIST.get(i).getS_id() == id) {
                return SUBSCRIBERS_LIST.get(i);
            }
        }

        return null;
    }
    
    //instead of using this code stack in each method, we created a separate method to be used in different methods.
    public static ServiceProvider findProviderById(int id) {
        for (int i = 0; i < SERVICE_PROVIDER_LIST.size(); i++) {
            if (SERVICE_PROVIDER_LIST.get(i).getP_id() == id) {
                return SERVICE_PROVIDER_LIST.get(i);
            }
        }

        return null;
    }
    
     //Since we have to create a provider with a unique name, we use this method to create a unique name
    public static String setNameOfProviders() {
        String name = "provider" + SimulateSystem.count;
        count++;
        return name;

    }

    public static void outputOfProviders() {
        for (ServiceProvider provider : SERVICE_PROVIDER_LIST) {
            System.out.println(provider.getP_id() + ": " + "total voice call time = " + provider.getTotalVoiceCallTime()
                    + ", total number of messages = " + provider.getTotalNumberOfMessages()
                    + ", total MBs of Internet usage = " + provider.getTotalInternetUsage());
        }
    }

    public static void outputOfSubscribers() {
        for (Subscriber subscriber : SUBSCRIBERS_LIST) {
            System.out.println(subscriber.getS_id() + ": " + "total spending = " + subscriber.getInvoice().getTotalSpending()
                    + ", current spending = " + subscriber.getInvoice().getCurrentSpending());
        }
    }

// methods for statistics
    public static void mostAmountOfVoiceCall() {
        if (SUBSCRIBERS_LIST.size() >= 1) {
            double max = SUBSCRIBERS_LIST.get(0).getTotalVoiceCallTime();
            int index = 0;
            for (int i = 1; i < SUBSCRIBERS_LIST.size(); i++) {
                if (SUBSCRIBERS_LIST.get(i).getTotalVoiceCallTime() > max) {
                    index = i;

                }
            }
            System.out.println(SUBSCRIBERS_LIST.get(index).getS_id() + ": " + SUBSCRIBERS_LIST.get(index).getName()
                    + " had the most amount of total voice call time = " + SUBSCRIBERS_LIST.get(index).getTotalVoiceCallTime());
        } else {
            System.out.println("There are no subscribers to make a voice call yet!");
        }

    }

    public static void mostNumberOfMessages() {
        if (SUBSCRIBERS_LIST.size() >= 1) {
            int max = SUBSCRIBERS_LIST.get(0).getTotalNumberOfMessages();
            int index = 0;
            for (int i = 1; i < SUBSCRIBERS_LIST.size(); i++) {
                if (SUBSCRIBERS_LIST.get(i).getTotalNumberOfMessages() > max) {
                    index = i;

                }
            }
            System.out.println(SUBSCRIBERS_LIST.get(index).getS_id() + ": " + SUBSCRIBERS_LIST.get(index).getName()
                    + " sent the most amount of messages= " + SUBSCRIBERS_LIST.get(index).getTotalNumberOfMessages());
        } else {
            System.out.println("There are no subscribers to send a message yet!");
        }

    }

    public static void mostInternetUsage() {
        if (SUBSCRIBERS_LIST.size() >= 1) {
            double max = SUBSCRIBERS_LIST.get(0).getTotalInternetUsage();
            int index = 0;
            for (int i = 1; i < SUBSCRIBERS_LIST.size(); i++) {
                if (SUBSCRIBERS_LIST.get(i).getTotalInternetUsage() > max) {
                    index = i;
                }
            }
            System.out.println(SUBSCRIBERS_LIST.get(index).getS_id() + ": " + SUBSCRIBERS_LIST.get(index).getName()
                    + " got connecting the internet most= " + SUBSCRIBERS_LIST.get(index).getTotalInternetUsage());
        } else {
            System.out.println("There are no subscribers to connect to internet yet!");
        }

    }

   

}
