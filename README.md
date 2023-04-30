# FilmQueryProject

# Description
Retrieves data from a relational database according to user input. 
Allows user to specify a 'film ID' that correlates to a table's primary key, 
for which to view selected, or all, columns within the database's 'film' table. 
Users can input a string to search for all 
films whose title or description attributes contain that string. 
The program performs object-relational mapping, generating Film, Actor, and Inventory Item objects 
when their data are retrieved from correlated tables, 
with object member fields that correspond to each column's attribute value.

# Technologies Used
- Java OOP
- Eclipse
- JDBC API
- SQL
- MySQL RDBMS
- Git

# Lessons Learned
- Relies upon a database accessor object containing all implementations 
of the Driver Manager's getConnection() method.
- Used a Maven project object model .xml configuration file 
to define a MySQL driver dependency element.
- Instantiates SQL Connection, Prepared Statement, and Result Set objects when 
database accessor methods are called via the application.
- Formulated SQL queries using SELECT statements 
and WHERE clauses to filter row(s) required in the result sets, 
including logical operators when there were multiple conditions.
- Iterates through the result set, mapping SQL data types to appropriate variables 
with Java primitive data types and non-primitives, 
passing select variables into object constructors to assign object fields. 
- Uses predicate operator LIKE with wildcards to pattern match film title and description 
character data relative to a user input string.
- Employed bind variables within the Prepared Statement's query text, that vary according to user input.
- Employed INNER JOIN queries to obtain data from multiple tables, 
with defined join conditions between foreign keys and primary keys 
(e.g. retrieved language strings from an external table, that were represented by only an integer in the 'film' table).
- Employs ORDER BY clauses to sort result sets; film titles are alphabetized within film collections, 
and inventory items are sorted by ascending inventory item IDs.