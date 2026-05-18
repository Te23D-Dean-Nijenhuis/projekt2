/*
Dean Nijenhuis TE23D
denna klassen är användar objektet
*/

package librarysystem;

public class User implements Comparable<User> {
    private String id;
    private String name;
    private String email;

    public User (String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
    @Override
    public int compareTo(User other) {
        return this.name.compareToIgnoreCase(other.name);
    }
    
    
}
