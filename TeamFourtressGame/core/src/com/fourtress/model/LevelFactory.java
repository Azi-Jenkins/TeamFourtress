package com.fourtress.model;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class LevelFactory {

	private static LevelFactory thisInstance;

	private LevelFactory() {

	}

	public static LevelFactory getInstance() {
		if (thisInstance == null) {
			thisInstance = new LevelFactory();
		}
		return thisInstance;
	}

	public Level makeLevel(int levelNum, Box2dModel model) {
		HashMap<String, Item> levelItems;
		switch (levelNum) {
		case 1:
			levelItems = getLevel1Items();
			return new Level(new TmxMapLoader().load("Maps/HistoryMap.tmx"), model, levelItems);
		case 2:
			levelItems = getLevel2Items();
			return new Level(new TmxMapLoader().load("Maps/Computing.tmx"), model, levelItems);
		case 3:
			levelItems = getLevel3Items();
			return new Level(new TmxMapLoader().load("Maps/EnglishCanteenMap.tmx"), model, levelItems);
		}
		return null;
	}

	private HashMap<String, Item> getLevel1Items() {
		HashMap<String, Item> items = new HashMap<String, Item>();
		LinkedList<Book> books = new LinkedList<Book>();
		items.put("A", new Book("A", "Book", null));
		items.put("B", new Book("B", "Book", null));
		items.put("C", new Book("C", "Book", null));
		items.put("D", new Book("D", "Book", null));
		items.put("E", new Book("E", "Book", null));
		items.put("F", new Book("F", "Book", null));
		items.put("G", new Book("G", "Book", null));
		books.add((Book) items.get("A"));
		books.add((Book) items.get("B"));
		books.add((Book) items.get("C"));
		books.add((Book) items.get("D"));
		books.add((Book) items.get("E"));
		books.add((Book) items.get("F"));
		books.add((Book) items.get("G"));
		ItemPile<Book> pileOfBooks = new ItemPile<Book>("Pile of books", "Pile", null);
		pileOfBooks.setContents(books);
		items.put("Books", pileOfBooks);
		items.put("LibraryKey", new Key("LibraryKey", "Key", null, "red"));
		items.put("OfficeKey", new Key("OfficeKey", "Key", null, "blue"));
		items.put("EndKey", new Key("EndKey", "Key", null, "green"));
		return items;
	}
	
	private HashMap<String, Item> getLevel2Items() {
		HashMap<String, Item> items = new HashMap<String, Item>();
		LinkedList<Book> books = new LinkedList<Book>();
		items.put("A", new Book("A", "Book", null));
		items.put("B", new Book("B", "Book", null));
		items.put("C", new Book("C", "Book", null));
		items.put("D", new Book("D", "Book", null));
		items.put("E", new Book("E", "Book", null));
		items.put("F", new Book("F", "Book", null));
		items.put("G", new Book("G", "Book", null));
		books.add((Book) items.get("A"));
		books.add((Book) items.get("B"));
		books.add((Book) items.get("C"));
		books.add((Book) items.get("D"));
		books.add((Book) items.get("E"));
		books.add((Book) items.get("F"));
		books.add((Book) items.get("G"));
		ItemPile<Book> pileOfBooks = new ItemPile<Book>("Pile of books", "Pile", null);
		pileOfBooks.setContents(books);
		items.put("Books", pileOfBooks);
		items.put("LibraryKey", new Key("LibraryKey", "Key", null, "red"));
		items.put("OfficeKey", new Key("OfficeKey", "Key", null, "blue"));
		items.put("EndKey", new Key("EndKey", "Key", null, "green"));
		return items;
	}
	
	private HashMap<String, Item> getLevel3Items() {
		HashMap<String, Item> items = new HashMap<String, Item>();
		LinkedList<Book> books = new LinkedList<Book>();
		items.put("A", new Book("A", "Book", null));
		items.put("B", new Book("B", "Book", null));
		items.put("C", new Book("C", "Book", null));
		items.put("D", new Book("D", "Book", null));
		items.put("E", new Book("E", "Book", null));
		items.put("F", new Book("F", "Book", null));
		items.put("G", new Book("G", "Book", null));
		books.add((Book) items.get("A"));
		books.add((Book) items.get("B"));
		books.add((Book) items.get("C"));
		books.add((Book) items.get("D"));
		books.add((Book) items.get("E"));
		books.add((Book) items.get("F"));
		books.add((Book) items.get("G"));
		ItemPile<Book> pileOfBooks = new ItemPile<Book>("Pile of books", "Pile", null);
		pileOfBooks.setContents(books);
		items.put("Books", pileOfBooks);
		items.put("LibraryKey", new Key("LibraryKey", "Key", null, "red"));
		items.put("OfficeKey", new Key("OfficeKey", "Key", null, "blue"));
		items.put("EndKey", new Key("EndKey", "Key", null, "green"));
		return items;
	}
}

