package edu.wgu.d387_sample_code.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.Locale;

/* Still learning spring so i'm just writing this to make sure I remember correctly.
*  The point of @Component below is so that the class is automatically instantiated and
*  managed by the spring IoC as a Bean. This allows it to be injected by other classes
*  easier (you don't have to manually initilize it). This also allows for loose coupling
*  because if this changes later because you don't have to worry about how object is
*  instantiated or it's "life cycle" because that will be managed by spring. This will also
*  allow other classes to @Autowire instances of this class. It seems like a good comparision
*  for this would be "short-hand" for object/class instantiation (in the case of just using
*  component/autowired, there is a lot more to it once you start to use other annotations).
* */
@Component
public class LanguageService {

    @Autowired // It seems that Autowired here just allows the below to automatically be instantiated
    private MessageSource languageBundle;

    /*
        Below I use a "CopyOnWriteArrayList" because it is considered a thread safe ArrayList.
        I also had to look into final a bit on this one, apparently since this is a reference
        variable (the variable references the arraylist object), final will just prevent "messages"
        from being changed to another object, but the object itself can still be changed (messages.add).
        I just wanted to write this since for some reason I had in my head that final meant you could'nt
        change it at all, like with a primitive variable (final x = 5;). Final in this case is considered
        thread safe as it prevents "messages" from being re-assigned to another arraylist.

     */
    private final CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();

    public String[] getWelcomeMessages(){

        // The English thread
        Thread englishThread = new Thread(() -> {
            /*
             I briefly forgot languageBundle is an object type that extends the interface MessageSource.
             .getMessage is a method enforced by MessageSource and defined by ResourceBundleMessageSource.
             It takes three parameters: code - This is the key to the message I want to retrieve. If you go to
             the language_en.properties file you will see the key to the message I want to retrieve is "welcome"
             Args - This is for any arguments passed to the parameter, in this case I have none
             (ex: welcom = hello {0}, and goodmorning {1} I would pass {world,baltimore}). Lastly it takes
             an instance of the Locale java class. Locale.ENGLISH in this case is a pre-defined constant for
             the language English. Inside of LanguageConfig MessageSource was initilized with the @Bean annotation,
             beacuase of this, the locale.ENGLISH parameter allows spring to start looking for the basename I defined
             earlier {basname}_{two_letter_code_relevent_to_locale}.properties (ex: language_en.properties). I didn't
             relize something but MessageSource is a part of the spring framework and must be used with spring.

            */
            String message = languageBundle.getMessage("welcome", null, Locale.ENGLISH); // this can take four parameters if you add a default message in case of failure to the third parameter spot
            messages.add(message); // Adding to thread safe arraylist
            System.out.println("English Thread: " + message);
        });

        // Le fil français
        Thread frenchThread = new Thread(() -> {
            String message = languageBundle.getMessage("welcome", null, Locale.FRENCH);
            messages.add(message);
            System.out.println("French Thread: " + message);
        });
        // Below is so that if this method gets ran again, it is started with the arraylist empty
        messages.clear();
        // This code is pretty straight forward (It starts the threads)
        englishThread.start(); // This thread will more often finsish first as it is called/started first, but it won't ALLWAYS finish first
        frenchThread.start();

        // Wait for both threads to finish
        try {
            englishThread.join(); // .join stops the main thread until the attached thread finishs executing
            frenchThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // I am doing this for convience as this is not very efficient for large amounts of data,
        // but because I know it is only two messages I decided to convert and return messages to
        // an array rather than return a CopyOnWriteArrayList
        return messages.toArray(new String[messages.size()]);
    }
}
