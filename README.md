# XMLConverter

## About
The purpose of this project is bridge the data format between a legacy line-based text-file system and a new XML-based system.
This is done by reading the legacy format, transforming the input structure and exporting it to a new XML file.

## Technical Specifications
- The project is developed in Java.
- Maven as the dependency handler. 
- JUnit as the testing framework. 
- Jackson-dataformat-xml dependency for creating the XML data and writing to the XML file.

## Running
### With Maven
To run the application with Maven make sure Maven is installed and added to PATH, then run:
```mvn exec:java```. This should create the converted XML under XMLConverter/src/main/resources/output directory.

### IDE
The main method to run is App.java which is located at "XMLConverter/src/main/java/App.java".

## Testing
### With Maven
To run the unit-tests with Maven make sure Maven is installed and added to PATH, then run:
```mvn clean test``` in the project root.

### IDE
There's always the option of running all unit-tests directly in the IDE, if your IDE supports this. 
For IntelliJ, you can right-click "Run 'All Tests'" on the "XMLConverter/src/test/java" folder.

## Assumptions Made
In a real world scenario these are questions I would like to check with the customer before implementation to avoid the risk of delivering undesired functionality.

- For the sake of simplicity for this exercise I've assumed the input data is a singular line-based text file stored in a pre-determined location.
  This defaults to "XMLConverter/src/main/resources/input/input-data.txt" for this project. The location and file name can be changed in App.java main method.
- Much like the input file, I have assumed a pre-determined output location of the output XML file.
  This defaults to "XMLConverter/src/main/resources/output/input-data_converted.txt" for this project. The location and file name can be changed in App.java main method.
- I have assumed that the file type of the line-based file is not of importance for this exercise and have used a basic ".txt" format.
- I have assumed that the Telephone, Address and Family [T, A, F] lines are optional and should be omitted from the output XML if missing.
- The input example is missing the zip-code for Barack Obama's address. I have assumed this is a data issue and have input validation in the code that will return an exception if the zip code is missing. 
  The reasoning is that an address without a zip code is an incomplete address as well as the requirements clearly stating the address should follow the format: A|street|city|zipcode
- Between the input data and output XML the phone and address have switched place, I have assumed this is insignificant.
