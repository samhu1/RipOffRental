// Author: Samuel Hu
// SBU ID: 114251662
// Recitation 02

import java.util.Scanner;
public class RipoffRental {
    private static Bookshelf shelfA;
    private static Bookshelf shelfB;
    private static Bookshelf shelfC;
    public static Bookshelf currBookshelf;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        shelfA = new Bookshelf();
        shelfB = new Bookshelf();
        shelfC = new Bookshelf();
        currBookshelf = shelfA;
        
        char entry = '0';

        while(entry != 'q'){
        System.out.println("\nWelcome to Jack's aMAzin Textbook Rentals, highest price guaranteed!\n\n"+
        "Menu:\n"+
        "    A) Add Book\n"+
        "    S) Swap Books\n"+
        "    L) Loan Book\n"+
        "    R) Remove Book\n"+
        "    D) Duplicate Book\n"+
        "    C) Change Shelf\n"+
        "    O) Overwrite shelf with clone of current shelf\n"+
        "    E) Check if two shelves are equal\n"+
        "    P) Print current bookshelf\n"+
        "    Q) Quit\n");
            System.out.println("Please select an option: ");
            entry = input.nextLine().toLowerCase().charAt(0);

            if(entry == 'a'){
                System.out.println("Please select a title: ");
                String title = input.nextLine();

                System.out.println("Please select an author: ");
                String author = input.nextLine();

                System.out.println("Please select the condition: ");
                int condition = input.nextInt();

                System.out.println("Please select the position on shelf: ");
                int index = input.nextInt()-1;
                input.nextLine();

                if(condition>5 || condition<1){
                    System.out.println("The condition is invalid. Try again.");
                }
                else{
                    int before = currBookshelf.numBooks();
                    Book book = new Book(title, author, condition);

                    try {
                        currBookshelf.addBook(index, book);
                    } catch (IllegalArgumentException e) {
                        //TODO: handle exception
                        System.out.println("The index is invalid.");
                    }  catch (FullShelfException f) {
                        System.out.println("This bookshelf is full.");
                    }
                    
                    if(before<currBookshelf.numBooks()){
                        System.out.println("Book added!");
                    }
                    else
                        System.out.println("Book NOT added.");
                }
            }
            else if(entry == 's'){
                System.out.println("Please enter the first position: ");
                int index1 = input.nextInt()-1;
                input.nextLine();

                System.out.println("Please enter the second position: ");
                int index2 = input.nextInt()-1;
                input.nextLine();

                boolean swapped = false;
                try {
                    currBookshelf.swapBooks(index1, index2);
                    swapped =true;
                } catch (Exception e) {
                    System.out.println("One or both of your indexes is invalid.");
                }
                
                if(swapped)
                    System.out.println(currBookshelf.getBook(index1).getTitle()+" has swapped with "+ currBookshelf.getBook(index1).getTitle());
            }
            else if(entry == 'l'){
                
                System.out.println("Please enter the position: ");
                int index = input.nextInt()-1;
                input.nextLine();

                System.out.println("Please enter a recipient: ");
                String name = input.nextLine();

                System.out.println("Please enter the condition: ");
                int condition = input.nextInt();
                input.nextLine();

                if(currBookshelf.getBook(index).getCondition() == condition)
                    currBookshelf.getBook(index).setBorrower(name);

                System.out.println(currBookshelf.getBook(index).getTitle()+" has been loaned to "+name);

            }
            else if(entry == 'r'){
                System.out.println("Please enter the position of the book: ");
                int index = input.nextInt()-1;
                input.nextLine();

                int before = currBookshelf.numBooks();

                try {
                    currBookshelf.removeBook(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The position is invalid.");
                } catch (EmptyShelfException em) {
                    System.out.println("There are no books to remove.");
                }
                
                if(currBookshelf.numBooks()< before)
                    System.out.println("Book removed.");
            }
            else if(entry == 'd'){
                System.out.println("Please enter the position of the book: ");
                int index1 = input.nextInt()-1;
                input.nextLine();

                System.out.println("Please enter the position to insert the book: ");
                int index2 = input.nextInt()-1;
                input.nextLine();

                Book copy = (Book)currBookshelf.getBook(index1).clone();

                try {
                    currBookshelf.addBook(index2, copy);
                } catch (IllegalArgumentException e) {
                    System.out.println("The index is invalid.");
                }  catch (FullShelfException f) {
                    System.out.println("This bookshelf is full.");
                }

                if(currBookshelf.numBooks() < currBookshelf.CAPACITY){
                    System.out.println(currBookshelf.getBook(index1).getTitle()+" added to position " + (index2+1));
                }
            }
            else if(entry == 'c'){
                System.out.println("Please enter the bookshelf you wish to switch to: ");
                char shelf = input.nextLine().toLowerCase().charAt(0);

                if(shelf == 'a'){
                    currBookshelf = shelfA;
                    System.out.println("Shelf A selected.");
                }
                else if(shelf == 'b'){
                    currBookshelf = shelfB;
                    System.out.println("Shelf B selected.");
                }
                else if(shelf == 'c'){
                    currBookshelf = shelfC;
                    System.out.println("Shelf C selected.");
                }
                else
                    System.out.println("Bookshelf "+ shelf+" was not found.");

            }
            else if(entry == 'o'){
                System.out.println("Please enter the bookshelf you wish to override: ");
                char shelf = input.nextLine().toUpperCase().charAt(0);
                if(shelf == 'A')
                    shelfA = (Bookshelf)currBookshelf.clone();
                else if(shelf == 'B')
                    shelfB = (Bookshelf)currBookshelf.clone();
                else if(shelf == 'C')
                    shelfC = (Bookshelf)currBookshelf.clone();
                else
                    System.out.println("Bookshelf "+ shelf+" was not found.");

                System.out.println("Bookshelf " +shelf+ " was overwritten by Bookshelf "+getBookshelf());
            }
            else if(entry == 'e'){
                System.out.println("Please enter the firstBookshelf name: ");
                char bookshelf1 = input.nextLine().toLowerCase().charAt(0);;

                System.out.println("Please enter the second Bookshelf name: ");
                char bookshelf2 = input.nextLine().toLowerCase().charAt(0);;

                Bookshelf b1 = new Bookshelf();
                Bookshelf b2 = new Bookshelf();
                if(bookshelf1 == 'a')
                    b1 = shelfA;
                else if(bookshelf1 == 'b')
                    b1 = shelfB;
                else if(bookshelf1 == 'c')
                    b1 = shelfC;
                if(bookshelf2 == 'a')
                    b2 = shelfA;
                else if(bookshelf2 == 'b')
                    b2 = shelfB;
                else if(bookshelf2 == 'c')
                    b2 = shelfC;

                System.out.println(b1.equals(b2)?"These shelves are equal":"These shelves are NOT equal");
            }

            else if(entry == 'p'){
                String letter = getBookshelf();
                System.out.println("Bookshelf "+ letter+ ":\nNumber of books: "+currBookshelf.numBooks());
                System.out.printf("%s %5s %35s %22s %10s","Spot","Title","Author","Condition","Borrower");
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------");
                System.out.print(currBookshelf.toString());
            }
            
        }
        System.out.println("Goodbye!");
        input.close();
    }
    /**
     * This method gets the letter of the Bookshelf in String format
     * @return
     * The letter of the Bookshelf in String format
     */
    public static String getBookshelf(){
        String letter = "A";
        if(currBookshelf==shelfA)
            letter = "A";
        else if(currBookshelf==shelfB)
            letter = "B";
        else if(currBookshelf==shelfC)
            letter = "C";
        return letter;
    }
}
