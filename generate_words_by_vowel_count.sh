tr -s '[[:punct:][:space:]]' '\n' <  short_stories.txt | tr "[:upper:]" "[:lower:]" | sort | uniq > short_stories_words.txt
cat short_stories_words.txt | sed -e 's/[^aeiou]//g' | awk '{ print length($0); }' > short_stories_words_vowel_counts.txt
paste short_stories_words_vowel_counts.txt short_stories_words.txt | sort | less > short_stories_words_by_vowel_count.txt


grep -E '[aeiou]{2}' short_stories_words.txt > 2_vowels_in_row.txt
grep -E '[aeiou]{3}' short_stories_words.txt > 3_vowels_in_row.txt


cat short_stories_words_by_vowel_count.txt | grep 1 | awk '{print ""$2;}' | paste -sd "\t" - > 1vowels.txt
cat short_stories_words_by_vowel_count.txt | grep 2 | awk '{print ""$2;}' | paste -sd "\t" - > 2vowels.txt
cat short_stories_words_by_vowel_count.txt | grep 3 | awk '{print ""$2;}' | paste -sd "\t" - > 3vowels.txt
cat short_stories_words_by_vowel_count.txt | grep 4 | awk '{print ""$2;}' | paste -sd "\t" - > 4vowels.txt
cat short_stories_words_by_vowel_count.txt | grep 5 | awk '{print ""$2;}' | paste -sd "\t" - > 5vowels.txt
cat short_stories_words_by_vowel_count.txt | grep 6 | awk '{print ""$2;}' | paste -sd "\t" - > 6vowels.txt

