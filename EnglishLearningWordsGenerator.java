package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnglishLearningWordsGenerator {

	private final Map<String, WordMetadata> wordCache = new HashMap<>();

	public static void main(String[] args) {

		EnglishLearningWordsGenerator wordGenerator = new EnglishLearningWordsGenerator();
		wordGenerator.warmupWordCache();
		wordGenerator.findAllLearningWords();

	}

	public void findAllLearningWords() {

		for (int vowels = 1; vowels <= 3; vowels++) {
			for (int length = vowels; length <= 5; length++) {
				List<String> words = fetchWords(vowels, length);
				if (words.size() > 0) {
					System.out.println(vowels + " vowels of size " + length);
					words.forEach(word -> {

						WordMetadata wordMetadata = wordCache.get(word);

						String partsOfSpeech = wordMetadata.getPartsOfSpeech() == null ? ""
								: wordMetadata.getPartsOfSpeech();

						System.out.println(word + "\t" + "[" + partsOfSpeech + "]\t" + wordMetadata.getMeaning());
					});
				}
			}
		}
	}

	public List<String> fetchWords(int numOfVowels, int wordLength) {

		return wordCache.keySet().stream()
				// word length = wordLength
				.filter(line -> line.length() == wordLength)
				// numOfVowels vowels in word
				.filter(line -> line.replaceAll("(?i)[^aeiou]+", "").length() == numOfVowels)
				.sorted()
				// collect them all
				.collect(Collectors.toList());

	}

	public void warmupWordCache() {

		InputStream is = EnglishLearningWordsGenerator.class.getResourceAsStream("/real_gre_words.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		// line format: "[<marker>] <word> : <meaning>"
		br.lines()
				// fetch metadata
				.map(line -> new WordMetadata(line))
				// cache them all
				.forEach(word -> wordCache.put(word.getWord(), word));
	}

	public static class WordMetadata {
		private String word;
		private String partsOfSpeech;
		private String meaning;

		public WordMetadata(String line) {

			String first = line.split(":")[0].trim();

			if (first.split(" ").length > 1) {
				this.partsOfSpeech = first.split(" ")[0];
				this.word = first.split(" ")[1];
			} else {
				this.word = first.split(" ")[0];
			}

			this.meaning = line.split(":")[1].trim();

		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public String getPartsOfSpeech() {
			return partsOfSpeech;
		}

		public void setPartsOfSpeech(String partsOfSpeech) {
			this.partsOfSpeech = partsOfSpeech;
		}

		public String getMeaning() {
			return meaning;
		}

		public void setMeaning(String meaning) {
			this.meaning = meaning;
		}

		@Override
		public String toString() {
			return "WordMetadata [partsOfSpeech=" + partsOfSpeech + ", meaning=" + meaning + "]";
		}

	}

}
