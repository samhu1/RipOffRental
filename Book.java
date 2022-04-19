// Author: Samuel Hu
// SBU ID: 114251662
// Recitation 02

public class Book implements Cloneable {
    private String title;
    private String author;
    private String borrower;
    private int condition;

    /**
     * This is a constructor to create a new instance of Book class 
     * @param title
     * The title of the book
     * @param author
     * The author of the book
     * @param condition
     * The condition of the book
     */
    public Book(String title, String author, int condition){
        this.title = title;
        this.author = author;
        this.condition = condition;
    }

    /**
     * This method returns the title of the book
     * 
     * @return
     * The title of the book
     */
    public String getTitle(){
        return title;
    }

    /**
     * This method returns the author of the book
     * 
     * @return
     * The author of the book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * This method returns the borrower of the book
     * 
     * @return
     * The borrower of the book
     */
    public String getBorrower(){
        return borrower;
    }

    /**
     * This method returns the condition of the book
     * 
     * @return
     * The condition of the book
     */
    public int getCondition(){
        return condition;
    }

    /**
     * This method sets the title of the book
     * 
     * @param title
     * The title of the book
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * This method sets the author of the book
     * 
     * @param title
     * The author of the book
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * This method sets the borrower of the book
     * 
     * @param title
     * The borrower of the book
     */
    public void setBorrower(String borrower){
        this.borrower = borrower;
    }

    /**
     * This method sets the condition of the book
     * 
     * @param title
     * The condition of the book
     */
    public void setCondition(int condition){
        this.condition =  condition;
    }

    /**
     * This method creates a deep clone of the Book element
     * 
     * @return
     *The clone of the Book element
     */
    public Object clone(){
        Book bookcopy = new Book(title, author, condition);
        try {
            bookcopy = (Book)super.clone();
            bookcopy.setBorrower(null);
        } catch (CloneNotSupportedException e) {
            System.out.print("Book could not be duplicated.");
        }
        return bookcopy;
    }
    
    /**
     * This method checks if two Book elements are equal
     * 
     * @param obj
     * The obj being compared to the Book element
     * 
     * @return
     * The boolean if two Books are the same
     */
    public boolean equals(Object obj){
        if(obj instanceof Book){
            Book c = (Book)obj;
            return(title.equals(c.title) && author.equals(c.author) && condition == c.condition);
        }
        else
            return false;
    }

    /** 
     * This method returns the String format of the Book Object
     * @return
     * The String format of the Book object
     */
    public String toString(){
        return (String.format("%-34s %-22s %-10s %-15s",this.title ,this.author ,this.condition , this.borrower));
    }
}
