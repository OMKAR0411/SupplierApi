Makersharks Search API:
Developed a search page for buyers to find manufacturers based on customized requirements. This proof of concept for the search API allows users to retrieve a list of manufacturers according to specific criteria such as location, nature of business, and manufacturing processes.

Prerequisites
Java Development Kit (JDK): Version 8 or higher
Maven: For dependency management and building the project
Git: For version control
Spring Boot: To create and run the REST API


End Point To Save 
PostMapping
curl -X POST http://localhost:8080/api/supplier 

Json Input
{
  "company_name": "XYZ Manufacturing",
  "website": "http://xyzmanufacturing.com",
  "location": "San Francisco",
  "nature_of_business": "Technology",
  "manufacturing_processes": "Electronics Assembly"
}


End Point
GetMapping
curl -X GET "http://localhost:8080/api/supplier/San%20Francisco/Technology/Electronics%20Assembly"




