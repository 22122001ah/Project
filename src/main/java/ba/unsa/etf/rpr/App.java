package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DirectorsManager;
import ba.unsa.etf.rpr.business.PlaysManager;
import ba.unsa.etf.rpr.business.WritersManager;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Directors;
import ba.unsa.etf.rpr.domain.Plays;
import ba.unsa.etf.rpr.domain.Writers;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
    private static final Option getPlays = new Option("getP", "get-plays",false, "Printing all plays from project database");
    private static final Option getWriters = new Option("getW", "get-writers",false, "Printing all writers from project database");
   private static final Option getArtists=new Option("getA","get-artists",false,"Printing all artists from project database");
    private static final Option getDirectors=new Option("getD","get-directors",false,"Printing all directors from project database");
    private static final Option play_name = new Option("pn", "play-name", false, "Defining the employee first-name");
    private static final Option genre = new Option("g", "genre", false, "Defining the employee last-name");
    private static final Option price = new Option("pr", "price", false, "Defining the employee address");
    private static final Option date_of_play = new Option("dof", "date-of-play", false, "Defining the hire date when employee started to work");
    private static final Option dirId = new Option("dId", "directorfk", false, "Defining the department where employee will work");
    private static final Option writerId = new Option("eId", "writerfk", false, "Defining the project on which employee will work");
    private static final Option maxcap = new Option("mc", "maxcap", false, "Defining the employee's status of education");
    private static final Option soldtickets = new Option("st", "soldtickets", false, "Defining the employee's salary");

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
        options.addOption(getPlays);
        options.addOption(getWriters);
        options.addOption(getArtists);
        options.addOption(getDirectors);
        options.addOption(soldtickets);
        options.addOption(maxcap);
        options.addOption(dirId);
        options.addOption(writerId);
        options.addOption(date_of_play);
        options.addOption(price);
        options.addOption(genre);
        options.addOption(play_name);
        return options;
    }

    public static Writers searchThroughWriters(List<Writers> listOfWriters, String writerName) {

        return listOfWriters.stream().filter(cat -> cat.getFirst_name().toLowerCase().equals(writerName.toLowerCase())).findAny().get();
    }
    public static Artists searchThroughArtists(List<Artists> listOfArtists, String artistName) {


        return listOfArtists.stream().filter(cat -> cat.getArtist_name().toLowerCase().equals(artistName.toLowerCase())).findAny().get();


    }

    public static Plays searchThroughPlays(List<Plays> listOfPlays, String playName) {
        return listOfPlays.stream().filter(cat -> cat.getPlay_name().toLowerCase().equals(playName.toLowerCase())).findAny().get();
    }

    public static Directors searchThroughDirectors(List<Directors> listOfDirectors, String directorName) {

        return listOfDirectors.stream().filter(cat -> cat.getFirst_name().toLowerCase().equals(directorName.toLowerCase())).findAny().get();

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
        if((cl.hasOption(addPlays.getOpt()) || cl.hasOption(addPlays.getLongOpt()))){
            WritersManager writerManager = new WritersManager();
            PlaysManager playManager = new PlaysManager();
            DirectorsManager directorsManager=new DirectorsManager();
            Writers category = null;
            try {
                category = searchThroughWriters(writerManager.getAll(), cl.getArgList().get(5));
            }catch(Exception e) {
                System.out.println("There is no writer in the list! Try again.");
                System.exit(1);
            }

            Plays play = new Plays();
            play.setWriter(category);
            SimpleDateFormat parser = new SimpleDateFormat("dd.mm.yyyy");;
            play.setDate(parser.parse(cl.getArgList().get(3)));
            play.setPlay_name(cl.getArgList().get(0));
            play.setGenre(cl.getArgList().get(1));
            play.setMaxcap(Integer.parseInt(cl.getArgList().get(6)));
            Directors dir=searchThroughDirectors(directorsManager.getAll(),cl.getArgList().get(4));
            play.setDirector(dir);
            play.setSoldtickets(Integer.parseInt(cl.getArgList().get(7)));
            play.setPrice(Integer.parseInt(cl.getArgList().get(2)));
            playManager.add(play);
            System.out.println("You successfully added a new play to database!");

        } else if(cl.hasOption(getPlays.getOpt()) || cl.hasOption(getPlays.getLongOpt())){
            PlaysManager playManager = new PlaysManager();
            playManager.getAll().forEach(q -> System.out.println(q.getPlay_name()));

        }else if(cl.hasOption(getWriters.getOpt()) || cl.hasOption(getWriters.getLongOpt())){
           PlaysManager categoryManager = new PlaysManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getPlay_name()));

        }
        else if(cl.hasOption(getDirectors.getOpt()) || cl.hasOption(getDirectors.getLongOpt())){
            PlaysManager categoryManager = new PlaysManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getDirector()));
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);

        }
        }
    }
