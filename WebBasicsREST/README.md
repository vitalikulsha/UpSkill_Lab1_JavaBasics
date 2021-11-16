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

The system contains two objects: category and author. The category object has fields for id, title, and author list. The "author" object contains fields for the id, author's first and last name, book and category. Tables are linked by category id.

