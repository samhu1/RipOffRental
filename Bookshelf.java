// Author: Samuel Hu
// SBU ID: 114251662
// Recitation 02

public class Bookshelf {
    private Book[] books;
    private int count;
    final int CAPACITY = 20;

    /**
     * This is a constructor to create a new Bookshelf object with 0 book and can a hold a max of 20 books
     */
    public Bookshelf(){
        books = new Book[CAPACITY];
        count = 0;
    }

    /**
     * This method returns the number of books in the bookshelf in O(1) time
     * @return
     * The number of books in the shelf
     */
    public int numBooks(){
        return count;
    }

    /**
     * This method returns the Book object at the inputted position
     * @param index
     * The position of the book on the bookshelf
     * @return
     * The Book object at the index
     * @throws ArrayIndexOutOfBoundException
     * When index is at a null index
     */
    public Book getBook(int index) throws ArrayIndexOutOfBoundsException{
        if(!(index > numBooks()))
            return books[index];
        else
            throw new ArrayIndexOutOfBoundsException("No books exist here.");
    }

    /**
     * This method removes the book object at the given index from the bookshelf
     * 
     * @param index
     * The position of the book in the bookshelf
     * @throws ArrayIndexOutOfBoundsException
     * When index is more than the number of books or below 0
     * @throws EmptyShelfException
     * When the shelf is empty
     */
    public void removeBook(int index) throws ArrayIndexOutOfBoundsException, EmptyShelfException{
        if(numBooks() <= 0){
            throw new EmptyShelfException("There is nothing to remove.");
        }
        if( index < 0 || index > numBooks()){
            throw new ArrayIndexOutOfBoundsException("The index is invalid.");
        }
        else{
            books[index] = null;
            if(getBook(index+1) == null){
                count--;
            }
            else{
                for(int i = index; i<=numBooks();i++){
                    Book temp = books[i];
                    books[i] = books[i+1];
                    books[i+1] = temp;
                }
                count--;
            }
        }
        
    }

    /**
     * This method adds a Book Object at the index given
     * 
     * @param index
     * The position for Book to be inserted
     * @param book
     * The book Object to be inserted
     * @throws IllegalArgumentException
     * When index is too high and will create a gap in the bookshelf
     * @throws FullShelfException
     * When there are more books than the CAPACITY
     */
    public void addBook(int index, Book book) throws IllegalArgumentException, FullShelfException{
        if(numBooks()>=CAPACITY)
            throw new FullShelfException("This shelf is full.");
        if(index <= numBooks()){
            for(int i = numBooks();i> index;i--){
                Book temp = books[i];
                books[i] = books[i-1];
                books[i-1] = temp;
            }
            books[index] = book;
            count++;
        }
        else if(index > numBooks()){
            throw new IllegalArgumentException("The index too high.");
        }
        else if(index<0){
            throw new IllegalArgumentException("The index is too low.");
        }
        

    }

    /**
     * This method switches the positions of the Book Objects at the given indices 
     * @param index1
     * The position of the first book
     * @param index2
     * The position of the second book
     * @throws
     * When the either index is invalid
     */
    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException{
        if(getBook(index1) == null || getBook(index2) == null){
            throw new ArrayIndexOutOfBoundsException("Null position");
        }
        else{
            if(index1 <= 20  && index2 <=20 && books[index1] instanceof Book && books[index2] instanceof Book){
                Book temp = books[index1];
                books[index1] = books[index2];
                books[index2] = temp;
            }
        }
    }

    /**
     * This method clones the Bookshelf Object
     * @return
     * The clone of the Bookshelf Object
     */
    public Object clone(){
        Bookshelf shelfCopy = new Bookshelf();
        for(int i = 0; i< numBooks(); i++){
            try {
                shelfCopy.addBook(i, (Book)books[i].clone());
            } catch (IllegalArgumentException e) {
                //TODO: handle exception
                System.out.println("The index is invalid.");
            }  catch (FullShelfException f) {
                System.out.println("This bookshelf is full.");
            }
        }
        return shelfCopy;
    }

    /**
     * This method checks if two bookshelves are equal
     * @return
     * A boolean value whether two Bookshelves are equal
     */
    public boolean equals(Object o){
        if(o instanceof Bookshelf){
            Bookshelf b = (Bookshelf)o;
            if(numBooks() == b.numBooks()){
                for(int i = 0; i <= b.numBooks();i++){
                    if(getBook(i).equals(b.getBook(i)))
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * This method returns the String format of the Bookshelf Object
     * @return
     * The String format of the Bookshelf Object
     */
    public String toString(){ 
        String bookStr = "";
        for(int i = 0; i < numBooks(); i++){
            bookStr = bookStr+ ((i+1)+".   "+books[i].toString()+"\n");
        }
        return bookStr;
    }
}
