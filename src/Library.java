import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Library {
    private String[][] commands;
    private final ArrayList<Books> libraryBooks;
    private final ArrayList<Members> libraryMembers;
    private final StringBuilder outputs;

    public Library(String input) throws IOException {
        Reading inputs = new Reading();
        this.commands = inputs.readFile(input);
        this.libraryBooks = new ArrayList<>();
        this.libraryMembers = new ArrayList<>();
        this.outputs = new StringBuilder();
    }

    public void setLibrary() {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i][0].equals("addBook") && commands[i][1].equals("P")) {
                libraryBooks.add(new PrintedBook(libraryBooks.size() + 1));
                outputs.append(String.format("Created new book: Printed [id: %d]\n", libraryBooks.size()));
            } else if (commands[i][0].equals("addBook") && commands[i][1].equals("H")) {
                libraryBooks.add(new HandWrittenBook(libraryBooks.size() + 1));
                outputs.append(String.format("Created new book: Handwritten [id: %d]\n", libraryBooks.size()));
            } else if (commands[i][0].equals("addMember") && commands[i][1].equals("S")) {
                libraryMembers.add(new Student(libraryMembers.size() + 1));
                outputs.append(String.format("Created new member: Student [id: %d]\n", libraryMembers.size()));
            } else if (commands[i][0].equals("addMember") && commands[i][1].equals("A")) {
                libraryMembers.add(new Academic(libraryMembers.size() + 1));
                outputs.append(String.format("Created new member: Academic [id: %d]\n", libraryMembers.size()));
            }

            else if (commands[i][0].equals("borrowBook")) {
                int bookId = Integer.parseInt(commands[i][1]) - 1;
                int memberId = Integer.parseInt(commands[i][2]) - 1;
                try {
                    if (!libraryBooks.get(bookId).isBorrowed() && !libraryBooks.get(bookId).isReadInLibrary() && libraryMembers.get(memberId).isCanBorrowBook()) {
                        libraryBooks.get(bookId).borrowBook(LocalDate.parse(commands[i][3]), libraryMembers.get(memberId));
                        libraryMembers.get(memberId).setBookBorrowed(libraryBooks.get(bookId));
                        outputs.append(String.format("The book [%d] was borrowed by member [%d] at %s\n", bookId + 1, memberId + 1, commands[i][3]));
                    }
                    else if(!libraryMembers.get(memberId).isCanBorrowBook()) {
                        outputs.append("You have exceeded the borrowing limit!\n");}
                } catch (Exception e) {
                    outputs.append("Students can not borrow handwritten.\n");
                }


            } else if (commands[i][0].equals("returnBook")) {
                int bookId = Integer.parseInt(commands[i][1]) - 1;
                int memberId = Integer.parseInt(commands[i][2]) - 1;
                if(!libraryBooks.get(bookId).isReadInLibrary()){
                    libraryMembers.get(memberId).feeCalculator(libraryBooks.get(bookId).getDeadline(),LocalDate.parse(commands[i][3]));
                }
                libraryBooks.get(bookId).returnBook(LocalDate.parse(commands[i][3]));
                libraryMembers.get(memberId).bookReturned(libraryBooks.get(bookId));
                if(libraryMembers.get(memberId).getFee()>0){
                    outputs.append(String.format("The book [%d] was returned by member [%d] at %s Fee: %d\n", bookId + 1, memberId + 1,commands[i][3],libraryMembers.get(memberId).getFee()));}
                else{
                    outputs.append(String.format("The book [%d] was returned by member [%d] at %s Fee: 0\n", bookId + 1, memberId + 1,commands[i][3]));
                }

            }

            else if (commands[i][0].equals("readInLibrary")) {
                int bookId = Integer.parseInt(commands[i][1]) - 1;
                int memberId = Integer.parseInt(commands[i][2]) - 1;
                try {
                    if (!libraryBooks.get(bookId).isBorrowed() && !libraryBooks.get(bookId).isReadInLibrary()) {
                        libraryBooks.get(bookId).ReadInLibrary(LocalDate.parse(commands[i][3]), libraryMembers.get(memberId));
                        outputs.append(String.format("The book [%d] was read in library by member [%d] at %s\n", bookId + 1, memberId + 1, commands[i][3]));
                    } else {
                        outputs.append("You can not read this book!\n");
                    }

                } catch (Exception e) {
                    outputs.append("Students can not read handwritten books!\n");
                }
            }
            else if (commands[i][0].equals("extendBook")){
                int bookId = Integer.parseInt(commands[i][1]) - 1;
                int memberId = Integer.parseInt(commands[i][2]) - 1;
                if(!libraryBooks.get(bookId).isExtended()){
                    libraryBooks.get(bookId).extendDeadline(LocalDate.parse(commands[i][3]));
                    outputs.append(String.format("The deadline of book [%d] was extended by member [%s] at %s\n",bookId+1,commands[i][2],commands[i][3]));
                    outputs.append(String.format("New deadline of book [%d] is %s\n",bookId+1,libraryBooks.get(bookId).getDeadline().toString()));
                }
                else{
                    outputs.append("You cannot extend the deadline!\n");
                }

            }

            else if (commands[i][0].equals("getTheHistory")) {
                ArrayList<Student> students = new ArrayList<>();
                ArrayList<Academic> academics = new ArrayList<>();
                ArrayList<PrintedBook> printedBooks = new ArrayList<>();
                ArrayList<HandWrittenBook> handWrittenBooks = new ArrayList<>();
                ArrayList<Books> borrowedBooks = new ArrayList<>();
                ArrayList<Books> readInLibraryBooks = new ArrayList<>();

                for (int j = 0; j < libraryMembers.size(); j++) {
                    if (libraryMembers.get(j) instanceof Student) {
                        students.add((Student) libraryMembers.get(j));
                    } else {
                        academics.add((Academic) libraryMembers.get(j));
                    }
                }
                for (int j = 0; j < libraryBooks.size(); j++) {
                    if (libraryBooks.get(j) instanceof PrintedBook) {
                        printedBooks.add((PrintedBook) libraryBooks.get(j));
                    } else {
                        handWrittenBooks.add((HandWrittenBook) libraryBooks.get(j));
                    }
                }
                for (int j = 0; j < libraryBooks.size(); j++) {
                    if (libraryBooks.get(j).isBorrowed()) {
                        borrowedBooks.add(libraryBooks.get(j));
                    }
                    if (libraryBooks.get(j).isReadInLibrary()) {
                        readInLibraryBooks.add(libraryBooks.get(j));
                    }


                }
                outputs.append("History of library:\n\n");
                outputs.append(String.format("Number of students: %d\n", students.size()));
                for (Student student : students) {
                    outputs.append(String.format("Student [id: %d]\n", student.getMemberId()));
                }
                outputs.append(String.format("\nNumber of academics: %d\n", academics.size()));
                for (Academic academic : academics) {
                    outputs.append(String.format("Academic [id: %d]\n", academic.getMemberId()));
                }
                outputs.append(String.format("\nNumber of printed books: %d\n", printedBooks.size()));
                for (PrintedBook printedBook : printedBooks) {
                    outputs.append(String.format("Printed [id: %d]\n", printedBook.getBookId()));
                }
                outputs.append(String.format("\nNumber of handwritten books: %d\n", handWrittenBooks.size()));
                for (HandWrittenBook handWrittenBook : handWrittenBooks) {
                    outputs.append(String.format("Handwritten [id: %d]\n", handWrittenBook.getBookId()));
                }
                outputs.append(String.format("\nNumber of borrowed books: %d\n",borrowedBooks.size()));
                for (Books book : borrowedBooks) {
                    outputs.append(String.format("The book [%d] was borrowed by member [%d] at %s\n", book.getBookId(), book.getMemberId(), book.getBorrowTime().toString()));
                }
                outputs.append(String.format("\nNumber of books read in library: %d\n", readInLibraryBooks.size()));
                for (Books book : readInLibraryBooks) {
                    outputs.append(String.format("The book [%d] was read in library by member [%d] at %s\n", book.getBookId(), book.getMemberId(), book.getBorrowTime().toString()));
                }
            }
        }
    }

    public StringBuilder getOutputs() {
        return outputs;
    }
}
