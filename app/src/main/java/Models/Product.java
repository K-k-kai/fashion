package Models;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {
    public String imageUrl;
    public  String prodname,description,category;
    public int price;
    public String id;


    public Product(String imageUrl, String prodname, String description, String category, int price) {
        this.imageUrl = imageUrl;
        this.prodname = prodname;
        this.description = description;
        this.category = category;
        this.price = price;
        this.id= UUID.randomUUID().toString();
    }

    public Product() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }




    @Override
    public String toString() {
        return "Product{" +
                "imageUrl='" + imageUrl + '\'' +
                ", prodname='" + prodname + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", id='" + id + '\'' +
                '}';
    }
}
