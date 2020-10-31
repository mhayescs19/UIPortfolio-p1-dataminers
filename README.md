# UIPortfolio-p1-dataminers
## How to Run
* Run from Intellij Build/Run or in NavigationMenu.java
* Navigation menu image: <a href="https://rtx.azurewebsites.net/6c8513f809acfe4b81a59eb7b6bea0c0.png">Link</a>. The menu is a hub with a file menu that opens our various programs.

## Self-Grades

| Name | Self Grade (/20) | Scrum Leader Grade (/5) | Code Blocks |
| ------------- | ----------- | ----------- | ----------- |
|  Dominic ([domph](https://github.com/domph)) |  |  | - Met project goals for notepad project (see list of completed goals below). <br> &nbsp;&nbsp; - Image of new UI/functionality: <a href="https://rtx.azurewebsites.net/bb90765c0eb8b208bb0dadfaa98a61b4.png">Link</a> <br> &nbsp;&nbsp; - Code links: <br> &nbsp;&nbsp;&nbsp;&nbsp; - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/notepad/NotepadGUI.java">NotepadGUI (where most of the changes occurred)</a>: some changes/additions include ZoomIn/ZoomOut functions, BottomPanel code and CreateBottomLabel function, window listener for window closing <br> &nbsp;&nbsp;&nbsp;&nbsp; - <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/notepad/FileManager.java">FileManager (small change)</a>  |
|  Andrew (andrewcomputsci2019) |  |  | Finished pairshare console base calcuator and is currnetly fully functional, <a href="https://github.com/mhayescs19/pegg-hayes-calculator-p1-dataminers/blob/master/src/view_control/CalculatorConsole.java#L49">console method, small changes throught out file as well</a>. Ive also began my unit converter currently have a working ui and One working Unitconverion conversion method <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/master/src/UnitConverter/UnitUiForm.java#L3">UnitConverter</a>|
|  Jason (dragon0344) | 18/20 |  | -Key listner now works, <br> &nbsp;&nbsp; - Code will now convert and functions effectively as a calculator <br> &nbsp;&nbsp; - Code <a href="https://github.com/mhayescs19/UIPortfolio-p1-dataminers/blob/UnitConversion/src/view_control/TemperatureConverterUI.java">here</a> <br> &nbsp;&nbsp; -New code is in lines 26-74, 144-154 |
| Michael ([mhayescs19](https://github.com/mhayescs19)) |  |  | |
|  David (davidramsayer) |  |  | |


## Project Updates
### Hangman Game (Michael)
* 

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


# Update History

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
