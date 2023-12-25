import java.time.LocalDate;
import java.time.LocalDateTime;

public class HandWrittenBook extends Books{
    private RuntimeException exception;

    public HandWrittenBook(int id) {
        super(id);
    }
    @Override
    public void borrowBook(LocalDate borrowTime, Members member) {
        throw exception;
    }
    @Override
    public void ReadInLibrary(LocalDate borrowTime,Members member){
        if(member instanceof Student){
            throw exception;}
        else{
            super.ReadInLibrary(borrowTime,member);}
        }
    }


