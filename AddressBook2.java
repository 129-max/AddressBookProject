import java.util.HashMap;
import java.util.Scanner;

public class AddressBook2 {
    //Declaring HasMap to store all contact details
    HashMap <String, ContactDetails> contactList = new HashMap<String, ContactDetails>();

    public static void main(String[] args) {
        AddressBook2 addressBookMain = new AddressBook2();
        //calling add method using addressBookMainObject
        addressBookMain.addNewContact();
    }

    public ContactDetails getDetailsFromUser()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name : ");
        String fName = scanner.next();
        System.out.println("Enter last name : ");
        String lName = scanner.next();
        System.out.println("Enter email id : ");
        String email = scanner.next();
        System.out.println("Enter phone number : ");
        String phone = scanner.next();
        System.out.println("Enter address : ");
        String address = scanner.next();
        System.out.println("Enter zip code : ");
        String zip = String.valueOf(scanner.nextInt());
        System.out.println("Enter city : ");
        String city = scanner.next();
        System.out.println("Enter state : ");
        String state = scanner.next();

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setfName(fName);
        contactDetails.setlName(lName);
        contactDetails.setEmailId(email);
        contactDetails.setPhoneNumber(phone);
        contactDetails.setAddress(address);
        contactDetails.setZip(zip);
        contactDetails.setCity(city);
        contactDetails.setState(state);

        return contactDetails;
    }

    public void addNewContact()
    {
        ContactDetails contactDetails = getDetailsFromUser();
        contactList.put(contactDetails.getEmailId(), contactDetails);
    }
}