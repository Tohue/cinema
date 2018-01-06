package entities;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class Film {
    private StringProperty name;
    private StringProperty description;
    private StringProperty genre;
    private StringProperty country;
    private IntegerProperty length;
    private ObjectProperty<Image> poster;

    public Film(String name, String description, String genre, String country, int length, Image poster) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.genre = new SimpleStringProperty(genre);
        this.country = new SimpleStringProperty(country);
        this.length = new SimpleIntegerProperty(length);
        this.poster = new SimpleObjectProperty<>(poster);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public int getLength() {
        return length.get();
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public Image getPoster() {
        return poster.get();
    }

    public ObjectProperty<Image> posterProperty() {
        return poster;
    }

    public void setPoster(Image poster) {
        this.poster.set(poster);
    }
}
