package hu.ak_akademia.book4you.entities.certificate;

import java.time.LocalDate;

import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.user.Cashier;

public class Certificate {
	private int number;
	private LocalDate date;
	private Cashier cashier;
	private Direction direction;
	private Client client;
	private int amount;
	private Title title;
	private String comment;

	public Certificate(int number, Cashier cashier, Direction direction, Client client, int amount, Title title,
			String comment) {
		this.number = number;
		this.date = date.now();
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
}
