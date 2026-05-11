package librarysystem;

public abstract class LibraryItem {
    protected String id;
    protected String title;
    protected boolean isAvailable;

    public LibraryItem (String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    
    public boolean getIsAvailable (){
        return isAvailable;
    } 
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void SetAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public boolean isBorrowable() {
        return false;
    }


}
