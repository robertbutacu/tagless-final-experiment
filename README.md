# tagless-final-experiment
A repo to test out the tagless final design pattern using Cats

The main theme would be to experiment with a program which imitates a real-life web project.
This will include:
    - a layer for repository/connector
    - a layer for service
    - a layer for controllers
    
Using tagless final, it allows us to push the execution at the outside layers of the app,
depending on our needs - aka, Future or some other asynchronous Monad might be used in production,
while for testing, a synchronous Monad like Try can be used.