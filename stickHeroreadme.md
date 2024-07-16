<div align="center">
  <h1 style="font-family: 'Helvetica', sans-serif;"><span style="color:#00FFFF;">Documentation: StickHero</span></h1>
</div>

<div align="center">
  <h6 style="font-family: 'Helvetica', sans-serif;"> © Ayushman Pandey and Prashi Jain</h6>
</div>

An adaptation of Stick-Hero, made using Java & JavaFX for the Advanced Programming course (Monsoon 2023) course at IIIT Delhi.

***









## Class Structure

``` mathematica
Root Directory: com.example.stickherofx
├── Stick: Singleton pattern
├── StickGenerator: Factory pattern
├── Pillar: Class for pillars
├── PillarGenerator: Singleton pattern for generating pillars
├── Player: Class for player representation
├── PlayerState: Enum for player states
├── Point: Class for handling coordinates
├── Cherry: Class for cherry objects
├── HelloController: Controller for Game Page
├── HelloApplication: Main application class
├── Junittest: Unit tests for game functionality
└── loader


```

## How to Play
1. Hold 'SPACE': Extend the stick to jump between pillars.
2. Press 'K': Flip the player avatar.
3. Press 'ESCAPE': Pause the game.
4. Collect Cherries: Gain points and increase your cherry count.
5. Auto-Revive: If you have at least 5 cherries, you can revive after a fall.


## OOPS Patterns Implemented
- **DESIGN PATTERNS:**
    - Singleton: The StickGenerator and PillarGenerator classes ensure only one instance exists for generating sticks and pillars.
    - Factory: The PillarGenerator class is used to create new pillar objects.

- **THREADING:** We use JavaFX's AnimationTimer for real-time updates and game logic.
- We have used JAVA FX and UML for this project.
- **JUNIT:** We have added multiple tests in the Junittest class to verify functionality.
- **FILE I/O:** We have used file input/output to save our state in savedGame.txt.
- **OBJECT CLASS:** We have implemented the object class in toString() and equals() methods for comparisons and string representations.
- **ABSTRACT CLASS:** The Pillar class serves as an abstract base class for creating various pillar objects.
- **INHERITANCE:** The Player class inherits from Point, allowing for extended functionality.
- **CLASS RELATIONSHIPS:** Multiple class relationships exist, including association, aggregation, dependency, etc.
- **POLYMORPHISM** Used in various places for method overriding and flexibility.
- **Basic Principles like Encapsulation etc.** are followed throughout.
- **Collection Frameworks** have been implemented for managing game objects and states.


Note: All media used are royalty free, and it is played with the help of an https link.
