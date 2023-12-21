package BookstoreData;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

public class Book implements Serializable {
	@Serial
	private static final long serialVersionUID = 5296705482940410483L;
	private transient SimpleStringProperty isbn13P, titleP, priceP,stockP ,genreP,authorP,paperBackP;
    private String isbn13;
	private String title;
	private String description;
	private float price;
    private int stock;
    private String author;
    private String genre;
    private ArrayList<Genre> genres = new ArrayList<>();
	private boolean Paperback; // or e-book
	
	public Book(String isbn13, String title, String description, float price, String author, boolean paperback,int stock) {
		this.isbn13 = isbn13;
		this.title = title;
		this.price = price;
		this.author = author;
		this.description = description;
		this.Paperback = paperback;
        this.stock = stock;
        for (Genre g: genres) {
            this.genre.concat(g.toString() + ", ");
        }
        setIsbn13(isbn13);
		setTitle(title);
		setPrice(price);
		setAuthor(author);
		setPaperBack(paperback);
		setStock(stock);
		
        
        
	}
	
    
	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	



	@Override
	public String toString() {
		return this.title + " by " + this.author.toString() + ", " + this.price + " leke";

    }

    public enum Genre {
        MYSTRERY, ACTION, HISTORICAL, DYSTOPIAN, FANTASY 
    }

    public String getIsbn13() {

		if(isbn13P == null) {
			setIsbn13(isbn13);
		}

        return isbn13P.get();
    }

    public void setIsbn13(String isbn13p) {
        this.isbn13P = new SimpleStringProperty(isbn13p) ;
    }

    public String getTitle() {
        if(titleP == null) {
			setTitle(title);
		}
		return titleP.get();
    }
	
    public void setTitle(String titleP) {
        this.titleP = new SimpleStringProperty(titleP);
    }

    public String getAuthor() {
        if(authorP == null) {
			setAuthor(author);
		}
		return authorP.get();
    }

    public void setAuthor(String authorP) {
        this.authorP = new SimpleStringProperty(authorP);
    }

	public void setStock (int stock) {
		this.stockP = new SimpleStringProperty(Integer.toString(stock));
	}

	public String getStock() {
		if(stockP == null) {
			setStock(stock);
		}
		return stockP.get();
	}

	public String getPaperBack() {
		if(paperBackP == null) {
			setPaperBack(Paperback);
		}
		return paperBackP.get();
	}

	public void setPaperBack(boolean paperBackP) {
		this.paperBackP = new SimpleStringProperty(Boolean.toString(paperBackP));
	}

    public void setPrice(float priceP) {
		this.priceP = new SimpleStringProperty(Float.toString(priceP));
	}

    public String getPrice() {
        if(priceP == null) {
			setPrice(price);
		}
		return priceP.get();
    }

    public void removeStock(int amount) {
        if(this.stock-amount>=0)
		    this.stock -= amount;
    }

    public void addStock(int amount) {
        this.stock += amount;
    }

    public int getStockInt() {
        return this.stock;
    }
}
