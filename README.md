SalesTaxApplication
===================

The java project for the sales tax problem.

Build Requirements:
JDK 1.6
Maven 3
Eclipse

Test case execution:
1. Download the project.
2. Open command prompt or terminal
3. Navigate to project directory and give command 'mvn test'
4. Test case will be executed as part of the build and results will be available in the build console or target folder within project directory

Steps to verify output:

1. Download the project
2. Import it into eclipse as existing maven project
3. Build the project within eclipse
4. Navigate to ReceiptPrinter class
5. Execute the class as java application.

Design Approach:
* Objects referring to real world entities are Item and Receipt.
* Receipt has ReceiptItems to be printed in the receipt.
* External world contact from application is in SalesTaxBaseService in form of reading property files for taxation rules.
* Tax rules can be updated and tax will be calculated accordingly.
* UML Class diagram is attached to the project.
* Assumption: Category of the item is external to the shopping cart calculation and will be available to cart for tax calculation.
* Assumption: for now only 4 categories of items are available. Exemption criteria will be set in property file.
