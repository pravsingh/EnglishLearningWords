tr -s '[[:punct:][:space:]]' '\n' <  short_stories.txt | tr "[:upper:]" "[:lower:]" | sort | uniq > short_stories_words.txt
cat short_stories_words.txt | sed -e 's/[^aeiou]//g' | awk '{ print length($0); }' > short_stories_words_vowel_counts.txt
paste short_stories_words_vowel_counts.txt short_stories_words.txt | sort
