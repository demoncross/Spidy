# Spidy
A simple web crawler in Java. 

The GUI is made using Swing. 

The user enters a URL, a depth, a list of target words and a destination folder to save the results. 

The crawler does a BFS till the given depth and downloads a web page if the URL contains any of the target words. And saves it in the correspoding folder

Jaunt API is used for HTML parsing and downloading a web page completely along with all its resources (scripts, images etc).
