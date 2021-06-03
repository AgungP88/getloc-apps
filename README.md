# Machine Learning Path

## Description
GetLoc : Get Location
the idea of our application is to provide recommendations for the fastest and most efficient route to several tourist destinations and also provide recommendations for tourist attractions based on user. based on what the user likes and also provides packages for several tourist attractions according to several parameters such as city, price, category, and also time

### Optimation shortest Route
The Traveling Salesman Problem (TSP) is a classic problem that tries to find the shortest route for salesmen who want to visit several cities without having to visit the same city more than once.

Reinforcement learning (RL) is an area of machine learning concerned with how intelligent agents ought to take actions in an environment in order to maximize the notion of cumulative reward. Reinforcement learning is one of three basic machine learning paradigms, alongside supervised learning and unsupervised learning.

### Recomendation System
Collaborative filtering is a technique that can filter out items that a user might like on the basis of reactions by similar users. It works by searching a large group of people and finding a smaller set of users with tastes similar to a particular user.

Content-based recommenders treat recommendation as a user-specific classification problem and learn a classifier for the user's likes and dislikes based on an item's features. In this system, keywords are used to describe the items and a user profile is built to indicate the type of item this user likes.

### Dataset

To build the TSP we were inspired to use this dataset to check if our optimization route works especially for testing Reinforcement Learning in [here](http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/index.html)

and we were inspired by this data set to build our recommendation system in [here](https://www.kaggle.com/azharianisah/infotempatwisata) and in [here](https://data.world/cityofaustin/m964-vp2q)

But we decided to build our own dataset because we didn't find the right dataset for our project. We crawl data from many sources and make it into a single dataset which you can view in [here](https://docs.google.com/spreadsheets/d/1lq6qeYAhBJBJbuyC9DpwKehQ6mWswMkd-NMfbDlkwnw/edit?pli=1#gid=990183666)



### Reference
* Solved TSP for optimasion route with RL in [here](https://medium.com/unit8-machine-learning-publication/routing-traveling-salesmen-on-random-graphs-using-reinforcement-learning-in-pytorch-7378e4814980)
* Build Content-based recomendation system in [here](https://www.kdnuggets.com/2020/07/building-content-based-book-recommendation-engine.html)
* Collaborative Filtering in [here](https://gilberttanner.com/blog/building-a-book-recommendation-system-usingkeras)
