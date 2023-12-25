import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class Members{
    private final int memberId;
    private long fee;

    private boolean canBorrowBook;
    private ArrayList<Books> book;

    public Members(int memberId) {
        this.memberId = memberId;

        this.canBorrowBook = true;
        this.book = new ArrayList<>();
    }
    public void feeCalculator(LocalDate borrowTime, LocalDate returnTime) {
        this.fee = DAYS.between(borrowTime,returnTime);
    }
    public void setBookBorrowed(Books borrowedBook){
        book.add(borrowedBook);

    }

    public void bookReturned(Books returnedBook){
        book.remove(returnedBook);
    }

    public void setCanBorrowBook(boolean canBorrowBook) {
        this.canBorrowBook = canBorrowBook;
    }

    public boolean isCanBorrowBook() {
        return canBorrowBook;
    }


    public int getMemberId() {
        return memberId;
    }

    public long getFee() {
        return fee;
    }

    public ArrayList<Books> getBook() {
        return book;
    }
}
