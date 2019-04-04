1.General information about the project

No big project consisting of 7 people would go well without planning, collaboration, proper job division and communication. In group 98, we dedicated a lot of time to make sure everyone in the group was up to date with current updates/changes to our program, either with weekly meetings and reviews, or concise but clear commit messages.

At the start of every week we would have a meeting to discuss the previous weeks’ achievements and encountered challenges. Every member of the team would update the team on what he did that week, what went well, what did not, and any improvements and comments he would have. After that, we would create an agenda for next week, divide our workload and start programming.

During the first weeks of the project, we encountered problems with job division - some people would get a significant portion of the workload while others would only have a chance to contribute less. This affected the morale of some team members, but it was quickly brought up in the next weekly meeting and it was made sure that every member gets their fair share of work.

Another problem that we encountered was miscommunications. There have been a few cases, mostly in the early weeks, where the same task would be done simultaneously by more than one person, making the other person’s work obsolete. After one particularly big case of this (one person did 3-4 hours of work for nothing), we made sure that this never happens again and started dividing work very clearly as well as improving real-time communication while working on something.

Our communications were done by a few means:
* Weekly issues and goals were posted on our scrumboard;
* Minor issues and quick questions/decisions were discussed in our WhatsApp group;
* Major decisions, merge requests and big problems were solved during our weekly meetings.

It might be worthy to note that to keep up with the schedule, we not only made use of the mandatory Monday meetings, but organized additional programming meetups in the TU Delft library.

Generally, as the project went on, everybody in their team started to understand what their responsibilities are, and how to approach them best. It might have helped that at the start of the project everybody divided their part of the work (two people for GUI, three people for server/database, and two people for testing/general problems), and sticked to this schedule through the rest of the project.

It is fair to say that at the end of this project everyone on our team improved in some way. It is obvious that we saw how important planning and communications are in a project of this scale, and, based on how serious problems were encountered less and less as the project went on, it is safe to say that we learned how to work in a team and improved our planning and communications skills.

Moreover, in addition to learning how to use Git, everybody had to program something they have never encountered before. Whether it was server, GUI or database, every member of the team gained important programming knowledge in an area they haven’t encountered before

To sum up, this project was very challenging, but at the end very rewarding, since every single one of us learned something new and improved their skills in multiple areas of computer science.

2.Design decisions:

One of the major decisions that we made as a team, was that at the start of the group project, we split the work on the application into groups. We decided that Omar, Alex and Vidas will be working on the server-side, Alvaro and Jonathan – on the databases, and Lachezar and Berry will design the GUI....

Concerning the technological choices that the team made, firstly, we researched the frameworks and libraries that we were going to need to build the application. The team then decided to build the server using the Spring boot framework, because of several reasons. First of all our TA had good experiences with it and recommended it to us as well. Furthermore, this framework is very well documented, including step by step guides. This helped us a lot on getting started, and with the build in ObjectMapper class, sending and retrieving JSON objects becomes really easy. On the client side we used the java HttpUrlConnection class to build our own handler class, because it's very simple and flexible. Concerning the GUI, we chose JavaFX, because it is easier to implement than Swing and AWT while it offers everything we needed. Furthermore, more than 70 libraries are implemented for JavaFX and thus, it was going to be much easier to design the application. The images were created on Adobe Illustrator, because we decided that we are going to have an unique interface of the application.
The server hosting is done on Heroku, we pretty much followed up on the course staff's recommendations there because it's free and has great support for data storage (through pgAdmin for example). The database is hosted on Heroku through pgAdmin, since we have experience with it, and it has integration with Heroku. We preferred to use MongoDB at first, but sadly this was not possible in combination with Heroku.  

3.Points for improvement:

There is much space for improvement in the software that we designed. For example, the GUI can be made even more advanced, e.g. background animations can be added to the main screen and the quality of the front-end code can be improved.
There is especially much room for improvement regarding testing: by using integration testing, end-to-end testing and TestFX for the GUI - whereas now we only have JUnit 5 tests, and sort-of integration testing on the database.
Apart from points for improvement, there are also things that have improved a lot over the course of the project. We have seen eachother grow, both individually and as a team.
To name a few things: The usage of git, work ethics, division of work, coding style, overall coding performance and especially communication.
The last bit I want to point out here is the part of knowing what is required part, because this has been and is still our biggest issue in my opinion.
I feel it could have made a big difference had we took a look in the rubrik more often and, sadly, I haven't seen much improvement on this point.

As a whole, the making of the project itself could have been improved by the team members. Although the team synced well after a few weeks, the planning did not go as expected and we had to create a new thoroughly thought plan in the middle of the timespan of the project.


4.Individual feedback

Lachezar Lesichkov


I was responsible for the delivery of the GUI part of the application. Having made several front-end applications before, i was confident that i would best fit in a team, where I design the application. I had no previous experience with Java GUI libraries and frameworks, but that was not a setback at the time.

After the team distributed the work among the members, I was eager to find out how this project is going to work out, because as I stated in my PDP, my strongest motivation was to find out how real applications with a team are built. First weeks were the hardest, because everything that I was supposed to use was new to me. Thus, in spite of having been ahead of schedule in week 1, I was late in weeks 2 and 3.  After that, I picked up my pace and managed to always stay ahead of schedule and, if possible, implement extra features.  

My strongest point during this point was the dedication towards the project. I stumbled upon many problems, but my motivation to add more features to the application i am building drove me forward. As a weaker point, I would single out the bad planning that i did for weeks 2 and 3, where i was behind schedule. At that point, i had not realized that the most work to be done is at the start. After that was easy.       

Concerning my team, I am of the opinion that we managed to sync very well. We all worked very well together and everyone managed to keep up with the established pace. Whenever one had problem with something, others tried and helped him. The only conflict that we had was in the middle of the project and it was about the bad planning and the division of the work. However, we solved it in no time and were on track again.


---------------


Bejruz Domínguez Adilova


During the OOP project I was part of the duo in charge of the GUI, together with Lachezar. My work consisted mostly of graphics, styling, and layout. The team functioned surprisingly well, given how all the members were selected randomly. I found that most of the group members were more ambitious with the final grade than I was, but this did not stop me from contributing to the group whenever possible and whenever requested.

Admittedly, my performance during the first two weeks was lackluster. Nevertheless, I picked up my pace shortly after and found the set of skills I could bring to the table that would uniquely contribute towards the final product and the project as a whole. A factor that added towards my less than ideal start was the lack of clear delineation of work, which when brought up to our attention, we resolved swiftly.

Much to my surprise, aside from the work delineation mishap at the start, which was resolved with more abundant and clearer communication, there were no other notable conflict between the team members. The greatest testament to how our group functioned like a well-oiled machine and with an uncanny lack of conflict is that we finished the app two weeks in advance of the deadline, which allowed us to further work on extra features and polish out the existing ones.

In conclusion, my greatest shortcoming during the project was, in my opinion, my lack of ambition at the start, where as I consider my strong point to be my decent communication skills and creativity.


---------------


Vidas Bacevičius


For this project, I was responsible for the testing/general help on the project. At first I was tasked to work on the server with Alexander and Omar, but quickly realized that I couldn't pick up on all the new material so quickly, and my work would have to be done by someome else to keep up with the schedule, so I started to stick with what I had experience in and could help with - testing and quality. The first quarter of CSE, the OOP course in particular really helped here, since, during it I learned about unit testing and could apply and expand my knowledge here.

Since I started to work on testing, my contribution to the project increased vastly. I also continued communicating with Alexander, helping him if he needed it, and asking for help and advice myself. I also liked that everyone was very enthusiastic about the project, which increased my morale and made the work on this project more enjoyable.

At the start of the project I had some problems with Git, which stemmed mostly from inexperience with version control system. Thankfully, my teammates helped me when I encountered any issues, for which I am very thankful.

Overall, I really liked this course, because not only did I get to improve my computer science/programming and version control skills, but also see how a project of this scale is executed and how important planning and communications are.



---------------


Jonathan Garack

I am satisfied with the progress I did during this quarter while working on the project. I think our whole group worked very efficient. I had a lot of opportunities to improve my programing skills and I think I used each of them. Each time I had a problem my team or our TA were really helpful.

I mainly focused on the Database part of the project. I created and designed the postgresql database, during this time I finally found useful lectures from previous quarters. I also learned how to connect the database part of the project with the application. The most difficult part of my work, was testing, but finally i managed to do them.

Of course I had few problems doing the project, but the communication between each of the team members was really good. Each time I had a problem someone helped me.

At the beginning of the project definitely one of my weak points was that I have never used most programs that the teaching team asked us to use. I had a lot of problems with gitlab and sourcetree, especially source but after few weeks i got used to this and it was fine.

I am happy with the teams dynamic and I hope there will be more projects like this one in the next quarters.





---------------


Álvaro Buj Mancha


My job in the project has been mainly focused on the deployment of our server and database in Heroku. On the first weeks Jonathan and I were in charge of the database, we worked on the original schema and on uploading it to Heroku to work with it. After this we started with the deployment of the app itself and later we split the job: Jonathan worked mainly on the database and I worked on the server.

It definitely took me a while to pick up the pace, the first couple of weeks my job was slow because I wasn't understanding the overall structure of our project and I was looking at it from the wrong place and this is where the main problem I had appeared: I was working on deploying the entire app on Heroku and for that I spent a week and a half on solving errors related with the JavaFX Heroku buildpack version so I worked on creating our own JavaFX buildpack. This was a waste of time due to the fact that we had to deploy the server and not the entire app. However, in that time I faced so many Heroku errors that I already knew all the fixes when they appeared later so it wasn't that bad after all.  

I didn't work as much as I'd liked with the code: the only work I did with it was with the POM file dependencies and the modifications to the server-side code that I had to do in order to deploy it to Heroku. But this was because the part I was assigned to do didn't require more and even though it wasn't exactly what I wanted I got to learn what I wrote in my personal development plan which was learning how to work on an environment that was similar to what a job would be.

The only problem I had with my team is that when I created 2 submodules to work on the server-side and client-side separately we had issues due to the fact that some classes were needed both in the server and client side. To fix this I moved my work to a new repository and I worked on it on my own to create an independent server-side module.

As a conclusion I would say that I really enjoyed the project and I found the weekly meetings very interesting, seeing our TA Tijmen asking more from us felt as if we were doing an actual job and my teammates took it really professionally, worked really hard and asked a lot from me so I'm really glad I got to work with them.
