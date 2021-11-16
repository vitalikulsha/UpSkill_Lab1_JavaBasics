# Home task "Design a REST API"
## Description
Discuss with a tutor choosing one of the following variants of a system to design REST API for.
* Catalogue:
    - Campus: rooms, occupation;
    - Library: books authors, categories;
    - Storehouse: storage space, goods, orders;
    - Store: goods, amounts, prices, discounts;
    - ...
* Logging:
    - Ship log: entries, events, dates;
    - Server logs: events, severity, instances;
    - ...
* Scheduling/Alerting systems:
    - Zoo animals nutrition scheduling and alerting;
    - Education activities deadlines scheduling and alerting;
    - IT department tickets scheduling and alerting;
    - ...

Then follow the steps described below.

## Steps

* Describe, what entities must it use.
* Describe, what operations with these functions must the system support.
* Design REST API including collections, filters, pagination, etc.
* Show or send a mentor the following results: Entities & Operations description, REST API description.

## Solution

The "Library" system contains two objects: category and author. The "Сategory" object has fields for id, title, and author list. The "Аuthor" object contains fields for the id, author's first and last name, book and category. Tables are linked by category id.

### ER-diagram
<img src="data/img/ER-diagram-library.png" width="900">

The "Category" object supports the following operations:

* adding a category to the database;
* getting a category by id;
* getting a category by title;
* getting all categories;
* category update;
* cascading category deletion.

The "Author" object supports the following operations:

* adding an author to the database by category id;
* getting author by id;
* getting all authors;
* author update;
* deleting an author.

### Adding a category to the database
<img src="data/img/addCategory.png" width="900">
<img src="data/img/addCategory-already-exists.png" width="900">

## Getting a category by id from the database
<img src="data/img/getCategoryById-without-authors.png" width="900">

## Getting a category by title from the database
<img src="data/img/getCategoryByTitle.png" width="900">
<img src="data/img/getCategoryByTitle-not-found.png" width="900">

## Getting all categories from the database
<img src="data/img/getAllCategories-without-authors.png" width="900">
<img src="data/img/getAllCategories-with-authors.png" width="900">
<img src="data/img/getAllCategories-not-found.png" width="900">

## Category update
<img src="data/img/updateCategory-ok.png" width="900">
<img src="data/img/updateCategory-get.png" width="900">
<img src="data/img/updateCategory-not-found.png" width="900">

## Cascading category deletion
<img src="data/img/deleteCategory-ok.png" width="900">
<img src="data/img/deleteCategory-get.png" width="900">

## Adding an author to the database by category id
<img src="data/img/addAuthor.png" width="900">
<img src="data/img/addAuthor-category-not-found.png" width="900">

## Getting author by id from the database
<img src="data/img/getAuthorById.png" width="900">
<img src="data/img/addAuthor-not-found.png" width="900">

## Getting all authors from the database
<img src="data/img/getAllAuthors.png" width="900">
<img src="data/img/getAllAuthors-not-found.png" width="900">
