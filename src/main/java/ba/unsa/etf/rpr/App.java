package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Adna Herak
 */

public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addPlays = new Option("p","add-plays",false, "Adding a new play to project database");
    private static final Option addWriters = new Option("w","add-writers",false, "Adding a new writer to project database");
    private static final Option getPlays = new Option("getP", "get-plays",false, "Printing all plays from project database");
    private static final Option getWriters = new Option("getW", "get-writers",false, "Printing all writers from project database");
    private static final Option writerDefinition = new Option(null, "Writer",false, "Defining writer for next added play");
    private static final Option addArtists=new Option("a","add-artists",false,"Adding new artist to project database");
   private static final Option getArtists=new Option("getA","get-artists",false,"Printing all artists from project database");
    private static final Option addDirectors=new Option("d","add-directors",false,"Adding new director to project database");
    private static final Option getDirectors=new Option("getD","get-directors",false,"Printing all directors from project database");
    private static final Option artistDefinition = new Option(null, "Artist",false, "Defining artist for next added play");
    private static final Option directorDefinition = new Option(null, "Director",false, "Defining director for next added play");


    /**
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar Project-cli-jar-with-dependencies.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addPlays);
        options.addOption(addWriters);
        options.addOption(addArtists);
        options.addOption(addDirectors);
        options.addOption(getPlays);
        options.addOption(getWriters);
        options.addOption(getArtists);
        options.addOption(getDirectors);
        options.addOption(writerDefinition);
        options.addOption(artistDefinition);
        options.addOption(directorDefinition);
        return options;
    }

    public static Writers searchThroughWriters(List<Writers> listOfWriters, String writerName) {

        Writers writer = null;
        writer = listOfWriters.stream().filter(cat -> cat.getFirst_name().toLowerCase().equals(writerName.toLowerCase())).findAny().get();
        return writer;

    }
    public static Artists searchThroughArtists(List<Artists> listOfWriters, String artistName) {

        Artists artist = null;
        artist = listOfWriters.stream().filter(cat -> cat.getArtist_name().toLowerCase().equals(artistName.toLowerCase())).findAny().get();
        return artist;

    }

    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);
        if((cl.hasOption(addPlays.getOpt()) || cl.hasOption(addPlays.getLongOpt())) && cl.hasOption((writerDefinition.getLongOpt()))){
            WritersManager writerManager = new WritersManager();
            PlaysManager playManager = new PlaysManager();
            Writers category = null;
            try {
                category = searchThroughWriters(writerManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no writer in the list! Try again.");
                System.exit(1);
            }

            Plays play = new Plays();
            play.setWriter(category);
            play.setWriter(writerManager.searchByWriterName(cl.getArgList().get(0)));
            play.setDate(Date.valueOf(LocalDate.now()));
            playManager.add(play);
            System.out.println("You successfully added a new play to database!");

        } else if(cl.hasOption(getPlays.getOpt()) || cl.hasOption(getPlays.getLongOpt())){
            PlaysManager playManager = new PlaysManager();
            playManager.getAll().forEach(q -> System.out.println(q.getPlay_name()));

        } else if(cl.hasOption(addWriters.getOpt()) || cl.hasOption(addWriters.getLongOpt())){
            try {
                WritersManager categoryManager = new WritersManager();
                Writers cat = new Writers();
                cat.setFirst_name(cl.getArgList().get(0));
                categoryManager.add(cat);
                System.out.println("Writer has been added successfully");
            }catch(Exception e) {
                System.out.println("There is already a writer with same name in database! Try again");
                System.exit(1);

            }

        } else if(cl.hasOption(getWriters.getOpt()) || cl.hasOption(getWriters.getLongOpt())){
           PlaysManager categoryManager = new PlaysManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getPlay_name()));

        } else {
            printFormattedOptions(options);
            System.exit(-1);

        }
        }
    }
