import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    //Declaring HasMap to store all contact details
    HashMap<String, ContactDetails> contactList = new HashMap<String, ContactDetails>();

    public AddressBook(){
        contactList = detailLoader();
    }

    Scanner scanner = new Scanner(System.in);

    public ContactDetails getDetailsFromUser() {
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
        String zip = scanner.next();
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

    public void addNewContact() {
        System.out.println("Select 1 : for add manual" +
                "Select 2 : for add from test data");
        int selectedOption = scanner.nextInt();
        if (selectedOption == 1){
            ContactDetails contactDetails = getDetailsFromUser();
            contactList.put(contactDetails.getEmailId(), contactDetails);
            writeAddressInToFile(contactList);
        }
        else if(selectedOption == 2){
            testDataProvider testDataProviders = new testDataProvider();
            contactList =  testDataProviders.sampleData();
            writeAddressInToFile(contactList);
        }
        else{
            System.out.println("Please Select valid option");
            addNewContact();
        }

    }

    public void updateContactDetail(){
        System.out.println("Enter email id too update : ");
        String email = scanner.next();

        ContactDetails contactDetails = contactList.get(email);

        if(!contactList.containsKey(email)) {
            System.out.println("Invalid email id");
            updateContactDetail();
        }

        System.out.println("1 : For first name \n" +
                "2 : For Last Name \n" +
                "3 : For phone number \n" +
                "4 : For address \n" +
                "5 : For zip code \n" +
                "6 : For city \n" +
                "7 : For state");
        int selectOption = scanner.nextInt();

        switch (selectOption){
            case 1:
                System.out.println("Enter the first name : ");
                String fName = scanner.next();
                contactDetails.setfName(fName);
                break;

            case 2:
                System.out.println("Enter the last name : ");
                String lName = scanner.next();
                contactDetails.setlName(lName);
                break;

            case 3:
                System.out.println("Enter phone number : ");
                String phone = scanner.next();
                contactDetails.setPhoneNumber(phone);
                break;

            case 4:
                System.out.println("Enter address : ");
                String address = scanner.next();
                contactDetails.setAddress(address);
                break;

            case 5:
                System.out.println("Enter zip code : ");
                String zipCode = scanner.next();
                contactDetails.setZip(zipCode);
                break;

            case 6:
                System.out.println("Enter City");
                String city = scanner.next();
                contactDetails.setCity(city);
                break;

            case 7:
                System.out.println("Enter state");
                String state = scanner.next();
                contactDetails.setState(state);
                break;

            default:
                System.out.println("please select valid option");
                break;
        }
        writeAddressInToFile(contactList);
    }

    public void deleteContact() {
        System.out.println("Enter the email id to delete : ");
        String email = scanner.next();

        if (!contactList.containsKey(email)) {
            System.out.println("Please provide valid email id");
            deleteContact();
        }
        contactList.remove(email);
        writeAddressInToFile(contactList);
    }

    //This method is used to print the contact details
    public void printAllDetails() {
        for (Object allContacts : contactList.values()) {
            System.out.println(allContacts);
        }
    }

    public void searchByCityOrState(){

        System.out.println("Enter city name : ");
        String city = scanner.next();
        System.out.println("Enter state name : ");
        String state = scanner.next();

        Map<String, ContactDetails> filterDetail = contactList.entrySet()
                .stream()
                .filter(map -> map.getValue().getState().equals(state))
                .filter(map -> map.getValue().getCity().equals(city))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        System.out.println(filterDetail);
    }

    public void countByCityOrState(){

        System.out.println("Enter city name : ");
        String city = scanner.next();
        System.out.println("Enter state name : ");
        String state = scanner.next();

        Map<ContactDetails, Long> filteredCountContact = contactList.values()
                .stream()
                .filter(map -> map.getState().contains(state))
                .filter(map -> map.getCity().contains(city))
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        System.out.println(filteredCountContact);
    }


    public void storeInDictIfBelongToSameCityOrState(){

        System.out.println("Enter city name : ");
        String city = scanner.next();
        System.out.println("Enter state name : ");
        String state = scanner.next();

        List <ContactDetails> filterValueToStore = contactList.values()
                .stream()
                .filter(map -> map.getState().contains(state))
                .filter(map -> map.getCity().contains(city))
                .collect(Collectors.toList());

        System.out.println(filterValueToStore);
    }

    public void shortContactListByFName() {
        List <ContactDetails> valueList=new ArrayList<ContactDetails>(contactList.values());

        valueList.sort((ContactDetails obj1, ContactDetails obj2) -> obj1.getfName().compareTo(obj2.getfName()));
        valueList.forEach((shortedContact)->System.out.println(shortedContact));
    }

    public void shortContactListByCity() {
        List <ContactDetails> valueList=new ArrayList<ContactDetails>(contactList.values());

        valueList.sort((ContactDetails obj1, ContactDetails obj2) -> obj1.getCity().compareTo(obj2.getCity()));
        valueList.forEach((shortedContact) -> System.out.println(shortedContact));
    }

    public void shortContactListByState() {
        List <ContactDetails> valueList=new ArrayList<ContactDetails>(contactList.values());

        valueList.sort((ContactDetails obj1, ContactDetails obj2) -> obj1.getState().compareTo(obj2.getState()));
        valueList.forEach((shortedContact) -> System.out.println(shortedContact));
    }

    public void shortContactListByZipCode() {
        List <ContactDetails> valueList=new ArrayList<ContactDetails>(contactList.values());

        valueList.sort((ContactDetails obj1, ContactDetails obj2) -> obj1.getZip().compareTo(obj2.getZip()));
        valueList.forEach((shortedList) -> System.out.println(shortedList));
    }

    // This function will be used to ask the user choice
    public void getUserChoice() {
        boolean isTerminate = false;

        while (!isTerminate){
            System.out.println("1: For add new contact \n" +
                    "2: For update existing contact \n" +
                    "3: For print contact list \n" +
                    "4: For delete contact \n" +
                    "5: For search by city name or state : \n" +
                    "6: For count number of address belong to same city or state \n" +
                    "7: For sort by first name \n" +
                    "8: For sort by state \n" +
                    "9: For sort by city \n" +
                    "10: For sort by zipCode \n" +
                    "11: For store in dictionary \n" +
                    "0: For terminate the program \n");
            int selectedOption = scanner.nextInt();

            switch (selectedOption){
                case 1:
                    addNewContact();
                    break;

                case 2:
                    updateContactDetail();
                    break;

                case 3:
                    printAllDetails();
                    break;

                case 4:
                    deleteContact();
                    break;

                case 5:
                    searchByCityOrState();
                    break;

                case 6:
                    countByCityOrState();
                    break;

                case 7:
                    shortContactListByFName();
                    break;

                case 8:
                    shortContactListByState();
                    break;

                case 9:
                    shortContactListByCity();
                    break;

                case 10:
                    shortContactListByZipCode();
                    break;

                case 11:
                    storeInDictIfBelongToSameCityOrState();
                    break;

                case 12:
                    this.detailLoader();
                    break;

                case 0:
                    isTerminate = true;
                    break;

                default:
                    System.out.println("Please select valid option");
                    break;
            }
        }
    }
    public void writeAddressInToFile(HashMap<String, ContactDetails> contactDetailsList)
    {

        File file = new File("/home/pawan/Desktop/PlayGround/AddressBook.txt");
        if(file.exists()){
            file.delete();
        }
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (Map.Entry<String, ContactDetails> entry : contactList.entrySet()){
                bufferedWriter.write(entry.getKey() + ":" +entry.getValue());

                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            }
            catch (Exception e){}
        }
    }

    public HashMap detailLoader(){
        try (InputStream inputStream = new FileInputStream("/home/pawan/Desktop/PlayGround/AddressBook.txt")){
            HashMap<String, String> loadedDataOfTextFile = new HashMap<>();
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.forEach((key, value) -> loadedDataOfTextFile.put(String.valueOf(key.toString()), value.toString()));
            loadedDataOfTextFile.forEach((key, value) -> {
                ContactDetails contactDetails = new ContactDetails();

                String [] words= value.toLowerCase().split("'");
                String details[] = words[10].split("=");
                String pin[] = details[1].split(",");
                String []phoneNumber = details[2].split(",");

                contactDetails.setfName(words[1]);
                contactDetails.setlName(words[3]);
                contactDetails.setAddress(words[5]);
                contactDetails.setCity(words[7]);
                contactDetails.setState(words[9]);
                contactDetails.setPhoneNumber(phoneNumber[0]);
                contactDetails.setEmailId(key);
                contactDetails.setZip(pin[0]);
                contactList.put(key, contactDetails);

            });

            return loadedDataOfTextFile;
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
        return null;
    }

}