# AtlasAdventures by Pumpkin Patch

We are Pumpkin Patch, a software development group from the Fall 2023 class of CSC207. We have decided to create a fun and engaging geography trivia game in Java this semester, and will be using online API calls to retrieve randomly chosen geographic data about countries all over the world, and will use that data to quiz the user and then display their score on a final scoreboard.

# Problem Domain & App Description
We want to make a geography game that allows the user to click on a map given a question, and they have to click on the country based off either its name, its capital, or its abbreviation. Then, they will be able to see their performance stored locally, as well as an online database leaderboard so they can compare how they perform against everyone else around the planet.

# Current API
The API that functions as the backbone of our project is the CountryStateCity API](https://countrystatecity.in/). It allows us to pull a plethora of data about every country and territory on the planet to make our questions. The second API we're using is the Bing Maps API which allows us to have a interactive map object inside our quiz that the user can click on that returns to us the coordinate of wherever the user clicked.
