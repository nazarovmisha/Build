public class Author {
    private String firstName;
    private String lastName;
    private  String biography;

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Author setBiography(String biography) {
        this.biography = biography;
        return this;
    }
}
