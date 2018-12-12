package ui;

import dao.ContactDao;
import dao.impl.ContactCollectionDao;
import services.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] Args) throws IOException {

        ContactDao dao = new ContactCollectionDao();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ContactService contactService = new ContactService(dao);
        CommandLineService service = new CommandLineService(contactService, br);
        service.run();
    }
}