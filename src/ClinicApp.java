public class ClinicApp {

    private static final int ROLE_ADMIN = 1;
    private static final int ROLE_FRONTDESK = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   Welcome to TownClinic - ClinicOps");
        System.out.println("=========================================");

        boolean exitSystem = false;

        while (!exitSystem) {
            System.out.println("\n----- MAIN MENU -----");
            System.out.println("1. Clinic Admin");
            System.out.println("2. Front Desk Executive");
            System.out.println("3. Exit");
            System.out.print("Select user personal: ");

            int role = ScannerHelper.readInt();

            switch (role) {
                case ROLE_ADMIN:
                    AdminMenu.show();
                    break;
                case ROLE_FRONTDESK:
                    FrontDeskMenu.show();
                    break;
                case EXIT:
                    System.out.println("Thank you for using ClinicOps. Goodbye!");
                    exitSystem = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2 or 3.");
            }
        }
    }
}