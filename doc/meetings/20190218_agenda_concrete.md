# Meeting February 18th 2019
>* Chair: Alexander van Vugt
>* Secretary: Lachezar Lesichkov
## Opening
>* Is everybody present?
>* Did everybody finish the assigned tasks? Discuss issues and how to tackle them, if any.
>* Any other issues you wish to address.
## Small talk
> Take turns! How was your week? How are you feeling about your workload & studies?
> Did you do any exciting things last week you want to share with the team?
## Approval of the notes from last week.
> The notes can be found on GitLab and are of sufficient quality.
## Approval of the agenda
> Is everything we need to discuss today on the agenda?
> Anything to add?
## Decisions to make
>* Server/client side frameworks.
>  * Spring
>* gui framework.
>  * JavaFX
>  * Html based frameworks?
>* Server hosting
>  * Heroku is free and recommended by Andy, though it only supports SQL language.
>  More on this on the database bulletpoint.
>* Database framework, data structures & language
>  * Spring has a built in Jackson parser, but it's not perfect.
>  * As mentioned before, Heroku only supports SQL. If we are going to pick Heroku,
>  we will need to integrate a SQL based DBMS. Postgres would be a great option as
>  all of us (should) have experience with this. Maybe there are other options that
>  provide advantages that postgres does not.
>  * JSON based DBMS would be easier to use with java objects, as we don't have to
>  write a parser ourselves (Googles GSON library). Are there existing libraries
>  for SQL parsing that we can use?
>* Server based CO2 calculation or Web API?
>* Any other particularly great libraries/frameworks?
>* A name for our app? Project 98 sounds lame.
>* Features! (see rubric for detailed description of demands for the demo's)
>  * Client & server can communicate a simple request + response. (week 3)
>  * User is able to track the habit "Eating a vegetarian meal". (week 6)
>  * Choose at least 3 extra features: (week 8)
>    * Buying local produce
>    * Using bike instead of car
>    * Using public transport instead of car
>    * Lowering the temperature of your home
>    * Installing solar panels
>  * Track the CO2 that you save and compare to your friends. (week 8)
>  * Have an overview of CO2 produced by you and others. (week 8)
>  * Bonus round! Any suggestions?
>    * Badges, achievements and other stimuli. (as suggested by the rubric)
>    * Users can create an account/log in (read: sell their data) with facebook,
>    allowing us to track a user over multiple devices.
>    * Users are able to share their progress to social media.
>    * Users are able to see a graph of how their carbon footprint has developed
>    over the course of their usage of the app.
>    * Users are asked to fill in a questionnaire to determine their carbon footprint
>    when they first use the app.
>    * Android platform is supported.
>    * IOS support.
>    * WebApp integration in 2 major browsers.
>    * Think of features you saw in other apps.
>* Package organisation
>  * Template:
>    * src>main>java
>    * src>test>java
>  * Suggestion from my side:
>    * src>main>java
>    * src>test>java
>    * src>spring>server
>    * src>spring>client
>    * src>utility>java (wrapper methods, supporting classes, etc.)
>    * src>abstract>class
>    * src>abstract>interface
## Sprint plan!
>* Integrate necessary libraries/frameworks (maven);
>* Setting up client and server;
>  * Set up server hosting service;
>* Set up package organisation;
>* Designing a gui that fits the needs for our planned features;
>* Integrate support for chosen DBMS;
>* Prepare a Demo.
## Agenda for next week.
> Set up an agenda for the next meeting. Include weekly reports and sprint review.
> * Assign a chair & secretary for next week.
## Any other business
> Any things to clarify? Anything we should still discuss?
## Question round
> If you have any questions, ask them now.
## Closing
> Dismissed! Time to get your tasks done :-)