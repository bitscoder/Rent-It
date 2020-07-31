package in.autodice.rentit;

public class Product {
    String image;
    String text;
    String Id;
    String con;
    Product() {

    }

    Product(String img, String txt) {
        this.image = img;
        this.text = txt;
    }

    Product(String img, String txt, String id) {
        this.image = img;
        this.text = txt;
        this.Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }


}
