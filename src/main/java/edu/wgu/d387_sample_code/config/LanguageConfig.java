package edu.wgu.d387_sample_code.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LanguageConfig {
    // Still in the process of learning spring so i'm just writing this to make sure
    // I don't forget: A spring @Bean annotation tells spring that a method
    // instantiates, configures, and initializes a new object to be managed by the
    // Spring IoC container. Now its time for the rabbit hole muhahhah. A Spring IoC
    // container is basically "a program that injects dependencies into objects to
    // achieve loose coupling and dynamic binding" which is nerd for a program that
    // automatically takes care of creating and manageing objects in your code (the
    // annotations help spring know what is what for this management). In summary
    // a @Bean annotation tells the spring IoC(basically the automatic manager for spring)
    // What the class is.
    @Bean
    // Technically I can make the return type ResourceBundleMessageSource but I have a long comment later explaining why I shouldn't
    public MessageSource languageBundle(){ // Message source is an imported spring framework interface
        // New to this but basically I will be returning an object that points to
        // the bundle containing the relevent languages. The object is also part of
        // a class that extends the messageSource interface.
        ResourceBundleMessageSource languageBundleSource = new ResourceBundleMessageSource(); // Imported spring framework class
        /*
        Below I had to look into a bit, but it seems that I need to set the base
        name for the object so that spring knows which properties files to look for.
        I set the base name to "language" so spring will look for bundles with the name
        of language_xx.properties, language_yy.properties, language_us.properties, and ect.
        it is interesting because this seems like something that would be set with an annotation
        like @Bundle(baseName) but isn't. Anyways, i'm done, just trying to comment a lot to make
        sure I fully understand the code.

        An extra note: This also helps later when I use something like locale.ENGLISH or locale.FRENCH
        because it allows spring to know which bundles/properties files to search (what the baseName is)

         */
        languageBundleSource.setBasename("language");
        languageBundleSource.setDefaultEncoding("UTF-8"); // setting the text encoding
        /*
        something that also confused me is why I return the type "MessageSource" which
        is just an interface that the actual object being returned extends
        (ResourceBundleMessageSource is being returned as MessageSource). I looked into
        why this is a thing and why it should be returned and what I found was that
        the interface type should be returned because it provides greater flexibility
        (Programming to an interface, not an implementation. Which is apparently a
        fundamentle principle in software design). How?? why?? I had the same question!
        It apparently provides greater flexibility because even though this is just a
        program for a school assignment, if it were a profesional application I would want
        the code to be updatable/maintainable. The best example I found was that is Spring
        Boot had an update that released FasterBetterResourceBundleMessageSource that extended
        MessageSource, having the return type of MessageSource will allow me to easily change
        the initilized object in this class without (ideally) having to change code anywhere
        else. Basically both the old and new objects will have the same methods they inherited
        from MessageSource, allowing them to be swapped out easier than if the return type was
        the object itself. From what it seems though, in this situation it is the difference between
        one day haveing to change the return type and initilized object, and just changing the
        initilized object. From what I have looked into though, this seems to be best practice so
        I will leave it alone. This way de-couples and makes the code more modular/easier to update
         */
        return languageBundleSource;
    }
}
/* I just realized I forgot to explain what the program overall does. This basially just creates
   the object responsible for access to the language bundles. I find it wild that this much
   commantary/info goes into just being able to understand the "why's" of writing a program like
   this to basically just create an object with the sole purpose of accessing other files.
   (i'm not saying it's a bad thing, it's actually really cool to know how much goes into writing
   good, maintainable code, just also wild while learning it). Anyhoodles, onto the next.
 */
