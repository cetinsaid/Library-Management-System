import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Reading ioFile = new Reading();
        Library library= new Library(args[0]);
        library.setLibrary();
        ioFile.writeFile(args[1], String.valueOf(library.getOutputs()));
    }
}