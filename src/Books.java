import java.time.LocalDate;

public class Books {
    private boolean isBorrowed;
    private boolean isExtended;
    private boolean isReadInLibrary;
    private LocalDate borrowTime;
    private LocalDate returnTime;
    private final int bookId;
    private int memberId;
    private int daysToExtend;
    private LocalDate deadline;

    public Books(int id) {
        this.bookId = id;
        this.isReadInLibrary = false;
        this.borrowTime = null;
        this.returnTime = null;
        this.deadline = null;
        this.isBorrowed = false;
        this.isExtended = false;
        this.daysToExtend = 0;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }


    public boolean isExtended() {
        return isExtended;
    }

    public void borrowBook(LocalDate borrowTime,Members member) throws Exception {
        this.borrowTime = borrowTime;
        this.isBorrowed = true;
        this.memberId = member.getMemberId();
        if(member instanceof Student){
            this.daysToExtend = 7;}
        else{
            this.daysToExtend= 14;}
        this.deadline = borrowTime.plusDays(daysToExtend);
    }
    public void returnBook(LocalDate returnTime){
        this.returnTime = returnTime;
        this.borrowTime = null;
        this.deadline = null;
        this.isBorrowed = false;
        this.isReadInLibrary = false;
        this.memberId = 0;
    }
    public void ReadInLibrary(LocalDate borrowTime,Members member) {
        this.borrowTime = borrowTime;
        this.isReadInLibrary = true;
        this.memberId = member.getMemberId();
    }

    public boolean isReadInLibrary() {
        return isReadInLibrary;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getBorrowTime() {
        return borrowTime;
    }

    public int getMemberId() {
        return memberId;
    }
    public void extendDeadline(LocalDate currentTime){
        this.deadline = currentTime.plusDays(daysToExtend);
        this.isExtended = true;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
