package net.bytebond.chatfilter.yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class filterStorage {

	private static final String FILE_PATH = "plugins/ChatFilter/nono-words.yml"; // Change this to your desired file path

	public static void filterStorage(String[] args) {
		List<String> nonoWords = new ArrayList<>();
		//nonoWords.add("slur");
		//nonoWords.add("slur1");
		//nonoWords.add("slur2");

		// Save nono-words to file
		////saveNonoWordsToFile(nonoWords);

		// Read nono-words from file
		List<String> readNonoWords = readNonoWordsFromFile();
		System.out.println("Read nono-words: " + readNonoWords);

		// Add a new nono-word
		////addNonoWord("slur3");

		// Read nono-words again after adding
		readNonoWords = readNonoWordsFromFile();
		System.out.println("Read nono-words after adding: " + readNonoWords);

		// Remove a nono-word
		removeNonoWord("slur1");

		// Read nono-words again after removing
		readNonoWords = readNonoWordsFromFile();
		System.out.println("Read nono-words after removing: " + readNonoWords);
	}

	public static void saveIfNotExists() {
		File file = new File(FILE_PATH);
		if (!file.exists()) {
			saveNonoWordsToFile(new ArrayList<>());
			System.out.println("File created: " + FILE_PATH);
		}
	}

	public static void saveNonoWordsToFile(List<String> nonoWords) {
		try (Writer writer = new FileWriter(FILE_PATH)) {
			// Choose either JSON or YAML format
			// Save as YAML
			DumperOptions options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
			Yaml yaml = new Yaml(options);
			yaml.dump(nonoWords, writer);

			System.out.println("Nono-words saved to file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> readNonoWordsFromFile() {
		try (Reader reader = new FileReader(FILE_PATH)) {
			// Choose either JSON or YAML format
			// Read as YAML
			Yaml yaml = new Yaml();
			List<String> nonoWords = yaml.load(reader);

			// CHAT SPAM System.out.println("Nono-words read from file.");
			return nonoWords != null ? nonoWords : new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>(); // Return an empty list in case of an error
		}
	}

	public static void addNonoWord(String newNonoWord) {
		List<String> nonoWords = readNonoWordsFromFile();
		nonoWords.add(newNonoWord);
		saveNonoWordsToFile(nonoWords);
		System.out.println("Added nono-word: " + newNonoWord);
	}

	public static void removeNonoWord(String nonoWordToRemove) {
		List<String> nonoWords = readNonoWordsFromFile();
		if (nonoWords.remove(nonoWordToRemove)) {
			saveNonoWordsToFile(nonoWords);
			System.out.println("Removed nono-word: " + nonoWordToRemove);
		} else {
			System.out.println("Nono-word not found: " + nonoWordToRemove);
		}
	}

	public static boolean containsNoNoWord(String message) {
		List<String> nonoWords = readNonoWordsFromFile();
		for (String nonoWord : nonoWords) {
			if (message.contains(nonoWord)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNonoWord(String word) {
		List<String> nonoWords = readNonoWordsFromFile();
		for (String nonoWord : nonoWords) {
			if (word.equals(nonoWord)) {
				return true;
			}
		}
		return false;
	}

	public static filterStorage getInstance() {
		return new filterStorage();
	}
}
