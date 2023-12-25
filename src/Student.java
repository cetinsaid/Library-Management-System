import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class Student extends Members{

    public Student(int memberId) {
        super(memberId);
    }


    @Override
    public void setBookBorrowed(Books borrowedBook) {
        super.setBookBorrowed(borrowedBook);
        if(getBook().size()==2){
            setCanBorrowBook(false);}
    }
}
