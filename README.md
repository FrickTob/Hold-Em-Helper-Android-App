# Hold Em Helper Android App
**Author: Toby Frick**

Welcome to the Hold 'Em Helper app for Android!

This is an open source Android app developed by Toby Frick. The source code is available on GitHub under the MIT license; the app is also available on Google Play.

I recently started playing poker casually with friends and thought that there should be an app that gives beginner poker players standard information to help them learn poker faster and more enjoyably.

As a result, I began developing this app. In it, there are three sections:

- **Hand Rankings** - A list of all 10 scoring poker hands
- **Starting Hands Guide** - Learn the overall rank of your starting hand and your odds of winning against 9 other random hands
- **Winning Odds** - Tell Hold 'Em Helper what your hand and board look like and it will calculate your winning odds against one random opponent

The most complicated part of the development process is the logic behind the Winning Odds section. It took a long time to implement the logic to determine the strength of a given hand, compare that to another hand to determine a winner, and finally do that will all possible hands given different numbers of cards on the table.

Over the course of this project, I learned about how android apps are made. Specifically, I learned more about how the XML and Java files work together,
UI design, simplifying logic and background threading.

I am a college student at Grinnell College and this is my first experience with app development. Hope you like it!

Google Play Link [here](https://play.google.com/store/apps/details?id=com.holdemhelper)
