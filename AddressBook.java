import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
        }
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        }
    }
}

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("\nAddress Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name to add: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number to add: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address to add: ");
                    String emailAddress = scanner.nextLine();

                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;

                case 2:
                    System.out.print("Enter name to remove: ");
                    String removeName = scanner.nextLine();
                    Contact removeContact = addressBook.searchContact(removeName);

                    if (removeContact != null) {
                        addressBook.removeContact(removeContact);
                        System.out.println("Contact removed successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    Contact searchResult = addressBook.searchContact(searchName);

                    if (searchResult != null) {
                        System.out.println("Contact found: " + searchResult);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    System.out.println("All Contacts:");
                    addressBook.displayAllContacts();
                    break;

                case 5:
                    System.out.print("Enter file name to save: ");
                    String saveFileName = scanner.nextLine();
                    try {
                        addressBook.saveToFile(saveFileName);
                        System.out.println("Address book saved to file successfully!");
                    } catch (IOException e) {
                        System.out.println("Error saving to file: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter file name to load: ");
                    String loadFileName = scanner.nextLine();
                    try {
                        addressBook.loadFromFile(loadFileName);
                        System.out.println("Address book loaded from file successfully!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error loading from file: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting Address Book System.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
