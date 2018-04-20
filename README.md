# mydict

This is a simple program with graphical interface to analize English texts and create a dictionary for learning.

It takes only UTF-8 encoding and following formats: txt, srt (subtitles fro movies) and fb2.

In the end of work you will get the list of words you don't know in text, sorted by first appearance in text.

Logic of work is simple:
1. Read file into memory and convert it into a list of words
2. Filter out words you already know by using a "MyDictionary" file (the list of words you know)
3. Make a list and show it to you with ability to go over it using mouse or keboard (up/down for scroll and right/left for remove)
4. During removing a word you will automatically add it to the "MyDictionary" file
5. Programm can save state so you can close it and return later
