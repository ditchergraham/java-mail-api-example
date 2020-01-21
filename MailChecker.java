import java.util.Properties;
import java.util.Scanner;

import javax.mail.*;

public class MailChecker {

    public static void receiveMail(String userName, String password) {
        try {
            // Connect with GMAIL using javaMail API and IMAP
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);
            Store emailStore = null;
            try {
                emailStore = emailSession.getStore("imaps");
                emailStore.connect("imap.gmail.com", userName, password);
            } catch(AuthenticationFailedException e){
                System.out.println("INVALID CREDENTIALS");
                e.printStackTrace();
            }

                // Get the inbox folder
            Folder inboxFolder = emailStore.getFolder("INBOX");
            inboxFolder.open(Folder.READ_ONLY);

            // Get all the messages from the inbox
            Message messages[] = inboxFolder.getMessages();

            // Only fetch the top 3 messages for now
            for (int i = messages.length-3; i < messages.length ; i++) {
                System.out.println("Email number: " + i+1);
                System.out.println("Subject: " + messages[i].getSubject());
                System.out.println("From: " + messages[i].getFrom()[0]);
                System.out.println("Sent date: " + messages[i].getSentDate() + "\n");
            }

            // Close the emailStore and inboxFolder
            inboxFolder.close();
            emailStore.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Whats's your gmail?");
        String email = sc.nextLine();
        System.out.println("What's your password?");
        String password = sc.nextLine();
        receiveMail(email, password);
    }
}
