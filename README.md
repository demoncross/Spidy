# Spidy
A simple web crawler in Java.

The GUI is made using Swing. 

The user enters a URL, a depth, a list of target words and a destination folder to save the results. 

The crawler does a BFS till the given depth and downloads a web page if the URL contains any of the target words. And saves it in the correspoding folder

Jaunt API is used for HTML parsing and downloading a web page completely along with all its resources (scripts, images etc).

So, for example if you want to crawl the website of Times Of India. Then enter "http://www.timesofindia.com" as the URL.

Enter any positive value (say 3) as the depth.

And then, enter the target words separated by semicolon. For example, if you want to download the web pages that contain "delhi" "mumbai" or "kolkata" in their URL, then enter "delhi;mumbai;kolkata".

Also, give the address of the destination folder where the results have to be saved. For example "/home/tushar/results/". Then the program will create 3 sub folders -

/home/tushar/results/delhi

/home/tushar/results/mumbai

/home/tushar/results/kolkata

All these folders will be used to save the results. So, all the crawled pages having the word "delhi" in their URL will get downloaded to /home/tushar/results/delhi.

If the URL of some page contains more than one of the target words, then it will be saved in all the folders.

