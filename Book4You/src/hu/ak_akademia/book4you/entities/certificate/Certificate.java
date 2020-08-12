package hu.ak_akademia.book4you.entities.certificate;

import java.io.Serializable;
import java.time.LocalDate;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.user.Cashier;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Certificate implements Serializable{

	
	//https://stackoverflow.com/questions/18791566/notserializableexception-on-simplelistproperty
	private static final long serialVersionUID = 1L;
	
	private final int number;
	private final LocalDate date;
	private final Cashier cashier;
	private final Direction direction;
	private final Client client;
	private final IntegerProperty amount;
	private final Title title;
	private final String comment;

	public Certificate(int number, LocalDate date, Cashier cashier, Direction direction, Client client, int amount, Title title,
			String comment) {
		this.number = number;
		this.date = date;
		this.cashier = cashier;
		this.direction = direction;
		this.client = client;
		this.amount = new SimpleIntegerProperty(amount);
		this.title = title;
		this.comment = comment;
	}

	public int getNumber() {
		return number;
	}

	public LocalDate getDate() {
		return date;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public Direction getDirection() {
		return direction;
	}

	public Client getClient() {
		return client;
	}

	public int getAmount() {
		return amount.get();
	}
	
	public IntegerProperty amountProperty() {
		return amount;
	}

	public Title getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		
		text.append("number=" + number + " ");
		text.append("date=" + date + " ");
		text.append("cashier=" + cashier + " ");
		text.append("direction=" + direction + " ");
		text.append("client=" + client + " ");
		text.append("amount=" + amount + " ");
		text.append("title=" + title + " ");
		text.append("comment=" + comment);
		
		return text.toString(); 
	}
	
	
}
