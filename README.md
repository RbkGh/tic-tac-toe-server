# TicTacToe Server- Write and deploy a stateless API that correctly answers the question,<br>“What move will result in the best outcome for O on this tic-tac-toe board?” 
### Requirements
> Java 8+ <br>
> docker V18.09.2+(optional) <br>
> docker-compose v1.23.2+(optional)

### Run All Tests(Test coverage is 100%)
> just type code below in your root directory,no need to have gradle installed on your machine as we are using the wrapper
```$xslt
./gradlew test
```
### Quick Run(Gradle) without docker
```$xslt
./gradlew bootRun
```
### Quick Start - Run With Docker [If you are running with docker instead,then change the active profile property in application.properties to "prod"]
```$xslt

 1. docker-compose build
 2. docker-compose up [Make sure nothing is running on port 8080] 
 
```
> Once you use the docker ,You can now access API on http://localhost:8080/tictactoe, not http://localhost:8080/ <br>
> Note, you should be able to run all the sample responses below and get the same result.


### Sample Requests And Responses 
> Request
```$xslt
GET http://localhost:8080/?board=+xxo++o++

```

> Response
```$xslt

HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 9
Date: Sat, 03 Apr 2021 02:43:16 GMT
Keep-Alive: timeout=60
Connection: keep-alive

oxxo  o  

Response code: 200; Time: 373ms; Content length: 9 bytes
```

> Request
```$xslt
GET http://localhost:8080/?board=+xxo++o++x

```

> Response
```$xslt

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 03 Apr 2021 02:45:56 GMT
Connection: close

{
  "timestamp": "2021-04-03T02:45:56.539+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "",
  "path": "/"
}

Response code: 400; Time: 126ms; Content length: 104 bytes
```

> Directory Structure 
```bash
#generated with 'tree' command 
.
├── Dockerfile 
├── README.md
├── build.gradle
├── docker-compose.yml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── wave
    │   │           └── tictactoe
    │   │               ├── TicTacToeRodneyBoachieApplication.java                      #Application main class that pieces everything together to be run
    │   │               ├── base
    │   │               │   ├── OptimalMoveGenerator.java                               #Interface for generating best move on board
    │   │               │   └── OptimalMoveGeneratorTicTacImplementation.java           #Custom implementation of OptimalMoveGenerator interface for Tic-tac-toe
    │   │               ├── configs
    │   │               │   └── AppMainConfigurator.java                                #Application configurations is made here, for now,there is a factory class that specifies the OptimalMoveGenerator implementation to be used application wide
    │   │               ├── controllers
    │   │               │   └── BoardController.java                                    #Controller that handles the root "/" endpoint, "board" request parameter is also processed here
    │   │               ├── converters
    │   │               │   └── BoardStateConverter.java                                #Converts board state from string to char[][] and from char[][] to string when responding to incoming web requests
    │   │               ├── exceptions
    │   │               │   └── InvalidBoardStateException.java                         #Custom exception that throws a 400 status code when a passed in board state is invalid
    │   │               ├── models
    │   │               │   └── SingleMove.java                                         #Model class for best move information 
    │   │               ├── services
    │   │               │   └── BoardService.java                                       #Service that handles incoming request from BoardController
    │   │               └── validations
    │   │                   └── BoardStateValidator.java                                #Validates and throws InvalidBoardStateException in case a board is invalid
    │   └── resources
    │       ├── application-production.properties                                       #Resources file that deals with application properties for a live environment, eg database address, port number and the likes
    │       ├── application-test.properties                                             #Resources file for our end to end tests properties
    │       └── application.properties                                                  #This is where we specify which properties file to use for our app,either "production" or "test"
    └── test
        └── java
            └── com
                └── wave
                    └── tictactoe
                        ├── TicTacToeRodneyBoachieApplicationTests.java              
                        └── controllers
                            └── BoardControllerTest.java                                #This handles integration tests for our BoardController endpoint, (100% class coverage)

```

# Kubernetes(k8s) deployment 
```
Due to time constraints, no k8s deployment was added but it should be simple to add as the application is already containerized.
```
