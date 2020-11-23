# UIPortfolio-p1-dataminers
## How to Run
### Prereqs
* Download Oracle Java JDK [HERE](http://jdk.java.net/)
* Install Intellij on machine

### GitHub Practices
* when possible create a new branch with new changes and merge via pull request to check compatibility

### Building Project in Intellij
* Run from Intellij Build/Run <br>
<img src="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/statics/intellijRunAnywhere.png" width="268" height="58"><br>

* or in NavigationMenu.java <br>
<img class="one" src="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/statics/intellijRunInCode.png" width="600" height="274">

## Launch Screen
![](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/readme-test/statics/MenuUI.png) 
The menu is a hub with a file menu that opens our various programs. Select menu item to launch to a program.

## Project Contributors
Contributor | GitHub Link | Focus in Project & Navigation to Demo via Menu |
----------- | ----------- | ------------- |
Dominic Phung | [@domph](https://github.com/domph) | Demos -> **Notepad** <br>Pair Share -> Chang, Phung, Ramsayer -> **Dominic's Calculator** (individual project) |
Andrew Pegg | [@andrewcomputsci2019](https://github.com/andrewcomputsci2019) |  |
Jason Chang | [@Dragon0344](https://github.com/Dragon0344) |Pair Share -> Chang, Phung, Ramsayer -> **Converters**   |
Michael Hayes | [@mhayescs19](https://github.com/mhayescs19) | Demos -> **Hangman** <br>Pair Share -> Pegg, Hayes -> **Calculator** |
David Ramsayer | [@davidramsayer](https://github.com/davidramsayer) |  | 

## Code Updates
| Name | Code Blocks |
|------- | ----------- |
| Dominic | - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/tree/master/src/notepad">Notepad project folder</a> <br> - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/notepad/NotepadView.java#L219">Create menu item function (allows for multiple keybinds)</a> <br>- <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/DominicCalculator.java">Calculator file</a> |
| Andrew |  |
| Jason | -<a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/UnitConversion/src/view_control/ConverterHub.java"> Converter Hub(Can access all of the converters from here</a> <br> |
| Michael | - [Main driver of HangmanConsole.java](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/HangmanConsole.java#L36) <br> - [Activation of console in control (triggered by GUI button)](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/control_hangman/Hangman.java#L62)<br> - Using game state booleans in both GUI and console: [GUI](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/HangmanUI.java#L224), [Console](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/HangmanConsole.java#L67)|
| David |  |

## Project Updates
### Notepad (Dominic)
* Goal met: Converted to MVC
<br><img class="one" src="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/statics/NotepadMVC.PNG" width="597" height="269"> <br>
Note: The lines from the MVCNotepad class are because that file creates instances of all three classes (it isn't part of the MVC process)
* Goal met: Added new & new window file menu items
* Goal met: Added Format > Word Wrap
* Goal met: Binded buttons to hotkeys
* Goal met: Implemented Ctrl + Mouse Wheel for zooming

### Unit Converter (Jason)
* Goal met: Made a semi-decent hub(could be better in my opinion


### Hangman Game (Michael)
* Major goal met:
  * Created alternative view for hangman using same control and model <br>
  <img class="one" src="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/statics/hangmanViewJFrame.png" width="332" height="220">   <img class="one" src="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/statics/hangmanViewConsole.png" width="445.14" height="220">

  * See control line directories at the top of each the view files (HangmanUI.java and HangmanControl.java) for other specific areas of where control is used
* Added end of round functionality in control - game over (player did not guess the phrase correctly) and success (player guessed the phrase)

# Update History
### Scrum Disscussion
* Project Contributors (contrib, github, journal), combine group grade, put any longer documents (prereq files) inside of a separate README
* Add supplemental images to describe how project files work
* Assests folder to store images to embed
* TODO or done

## Week of October 30, 2020
| Name | Self Grade (/20) | Scrum Leader Grade (/5) | Code Blocks |
| ------------- | ----------- | ----------- | ----------- |
|  Dominic ([domph](https://github.com/domph)) | 18/20 | 5/5 <br> ✓ achievement from week goals | - Met project goals for notepad project (see list of completed goals below). <br> &nbsp;&nbsp; - Image of new UI/functionality: <a href="https://rtx.azurewebsites.net/bb90765c0eb8b208bb0dadfaa98a61b4.png">Link</a> <br> &nbsp;&nbsp; - Code links: <br> &nbsp;&nbsp;&nbsp;&nbsp; - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/notepad/NotepadGUI.java">NotepadGUI (where most of the changes occurred)</a>: some changes/additions include ZoomIn/ZoomOut functions, BottomPanel code and CreateBottomLabel function, window listener for window closing <br> &nbsp;&nbsp;&nbsp;&nbsp; - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/notepad/FileManager.java">FileManager (small change)</a>  |
|  Andrew (andrewcomputsci2019) | 18/20 | 5/5 <br> ✓ achievement from week goals | Finished pairshare console base calcuator and is currnetly fully functional, <a href="https://github.com/mhayescs19/pegg-hayes-calculator-p1-dataminers/blob/master/src/view_control/CalculatorConsole.java#L49">console method, small changes throught out file as well</a>. Ive also began my unit converter currently have a working ui and One working Unitconverion conversion method <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/UnitConverter/UnitUiForm.java#L3">UnitConverter</a>|
|  Jason (dragon0344) | 18/20 | 5/5 <br> ✓ achievement from week goals | -Key listner now works, <br> &nbsp;&nbsp; - Code will now convert and functions effectively as a calculator <br> &nbsp;&nbsp; - Code <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/UnitConversion/src/view_control/TemperatureConverterUI.java">here</a> <br> &nbsp;&nbsp; -New code is in lines 26-74, 144-154 |
| Michael ([mhayescs19](https://github.com/mhayescs19)) | 18/20 | 5/5 <br> ✓ achievement from week goals | - Current UI: [Link](https://drive.google.com/file/d/1z_B9yMbarmwa3vHOIiDvsiEZaVh3vjnT/view?usp=sharing) <br> - MVC created for Hangman <br>  - [HangmanUI](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/HangmanUI.java#L52) added control, driver moved into control file <br>  - [HangmanControl](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/control_hangman/Hangman.java#L24) new class, used to check letter guesses, activates UI <br>  - [HangmanModel(Phrase)](https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/model_hangman/Phrase.java#L8) used to manage the phrase and mutations when guessing |
|  David ([davidramsayer](https://github.com/davidramsayer)) | 18/20 | 5/5 <br> ✓ achievement from week goals | -added both versions of Tic Tac Toe <br> &nbsp;&nbsp;&nbsp; -<a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/TicTacTerminal.java">terminal version</a> completely finished <br> &nbsp;&nbsp;&nbsp; -<a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/TicTacGUI.java">GUI version</a> is functional, but there are some problems with the game ending dialogue box that I want to find a new solution for. |


## Project Updates
### Notepad (Dominic)
* Goal met: Added zoom functionality
  * Added View > Zoom file menu buttons (Zoom In and Zoom Out)
  * Displays zoom percentage in the bottom bar
  * Goes from 10% to 500%
* Goal met: Added a cursor tracker in the bottom bar (displays line and column) 
* Goal met: Added a save prompt upon exiting notepad
  * If a file has been edited but not saved, exiting the notepad will display a prompt to save/not save the file before closing.

### Unit Converter (Jason)
* Got Key listner to work(finally)
* Backspace worked
* Created a few methods that can probably be inherited.

### Hangman Game (Michael) 
##### (following Dominic's goal met format)
* Goal met: MVC (hopefully correctly) implemented isolating model (phrase creation), control (checks guessed letters), view (creates 26 alphabet buttons, hangman icons, phrase display)
* Goal met: added guessing letter functionality; now you can guess the phrase
* TODO: 
  * add different game states for replay (right now you can only guess one word)
  * need to add functionality to punish the player for wrong letter guesses

## Week of Ocotober 23, 2020
* Michael (mhayescs19) 5/5 
  * <a href="https://github.com/mhayescs19/pegg-hayes-calculator-p1-dataminers/blob/master/src/view_control/CalculatorConsole.java">Control of Simple Calculator (Pair Share)</a> 
  * <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/hangman/src/view_control/HangmanUI.java">HangmanUI (Main project)</a> 
* Dominic (domph) 5/5
  * Updated my miniproject calculator to work across several windows (previously, the buttons would be messed up if several calculators were open). <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/view_control/DominicCalculator.java">File Link</a>
  * Began work on the notepad (main project). See below for more details on what progress has been made. <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/tree/master/src/notepad">Folder Link</a>
   * Images: <a href="https://rtx.azurewebsites.net/cf355e74f59ae3775c737e0992e05430.png">Notepad UI</a>, <a href="https://rtx.azurewebsites.net/f7f2268d0371489fa1980696041afcd7.png">Save File Dialog</a>
* Jason (dragon0344) 3/5-Not enough work done
<a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/UnitConversion/src/view_control/TemperatureConverterUI.java">The semiworking code</a>


* Andrew (andrewcomputsci2019) 4/5
  * <a href="https://github.com/mhayescs19/pegg-hayes-calculator-p1-dataminers/blob/master/src/view_control/CalculatorConsole.java"> Console based calcuator (Pair Share)</a> 
* David n/a
## Project Updates
### Hangman Game (Michael)
* Began creating UI, working to implement MVC once individual pair share calculator complete

### Notepad (Dominic)
* Main UI has been finished
* File menu bar has been added
* Added the following buttons (functionality has been added as well): open, save, and save as
* Indicator to tell if a file has been edited (asterik next to the file's name on the window title)
### Unit Converter (Jason)
* Started framework for the code, not much progress this week
* Created a mental plan/list for what needs to be done
* Frickin key listener is broken
* more research needed in trying to create operations(if I have 6 different units, the code becomes a mess)
