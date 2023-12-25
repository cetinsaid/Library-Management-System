import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class Academic extends Members{

    public Academic(int memberId) {
        super(memberId);
    }

    @Override
    public void setBookBorrowed(Books borrowedBook) {
        super.setBookBorrowed(borrowedBook);
        if(getBook().size()==4){
            setCanBorrowBook(false);}
    }
}
