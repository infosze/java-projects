package hu.ak_akademia.book4you.entities.certificate;

import java.io.Serializable;
import java.time.LocalDate;

import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.user.Cashier;

public class Certificate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final int number;
	private final LocalDate date;
	private final Cashier cashier;
	private final Direction direction;
	private final Client client;
	private final int amount;
	private final Title title;
	private final String comment;

	public Certificate(int number, Cashier cashier, Direction direction, Client client, int amount, Title title,
			String comment) {
		this.number = number;
		this.date = LocalDate.now();
		this.cashier = cashier;
		this.direction = direction;
		this.client = client;
		this.amount = amount;
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
		
		text.append("number=" + number + "/n");
		text.append("date=" + date + "/n");
		text.append("cashierObj=" + cashier + "/n");
		text.append("direction=" + direction + "/n");
		text.append("clientObj=" + client + "/n");
		text.append("amount=" + amount + "/n");
		text.append("title=" + title + "/n");
		text.append("comment=" + comment + "/n");
		
		return text.toString(); 
	}
	
	
}
