public class AddressBook1 {
    public static void main(String[] args) {
        ContactDetails1 contactDetails = new ContactDetails1();

        //setting the value of contact details
        contactDetails.setfName("Mohit");
        contactDetails.setlName("Raj");
        contactDetails.setEmailId("rajmohit.129@gmail.com");
        contactDetails.setPhoneNumber("91 6202968551");
        contactDetails.setAddress("Patna");
        contactDetails.setZip(800001);
        contactDetails.setCity("Patna");
        contactDetails.setState("Bihar");

        System.out.println("Details of Person" +contactDetails);
    }
}
