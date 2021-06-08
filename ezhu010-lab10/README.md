# Lab 10

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers


(Q1) Import the sample file into a new collection named contacts. Hint: Use the mongoimport command.

    mongoimport --jsonArray --db cs167 --collection contacts --file ~/Desktop/workspace/ezhu010-lab10/contacts.json

(Q2) Retrieve all the users sorted by name.

    db.contacts.find().sort({Name:1})

        {
        "_id" : ObjectId("60bd8130474d1a7920659af5"),
        "Name" : "Aguirre Fox",
        "Address" : {
        "StreetNumber" : 540,
        "streetName" : "High Street",
        "city" : "Bloomington",
        "state" : "SC",
        "ZIPCode" : 29823
        },
        "Friends" : [
        "Glenn Mcbride",
        "Marlene Macias",
        "Constance Arnold",
        "Beard Dotson",
        "Hester Lowe"
        ],
        "Active" : true,
        "DOB" : "Sat Mar 15 2014 06:04:01 GMT+0000 (UTC)",
        "Age" : 49
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659af9"),
        "Name" : "Aimee Mcintosh",
        "Address" : {
        "StreetNumber" : 145,
        "streetName" : "Boardwalk ",
        "city" : "Mahtowa",
        "state" : "MA",
        "ZIPCode" : 35051
        },
        "Friends" : [
        "Chase Wyatt",
        "Kelly Hewitt",
        "Michael Rodriguez"
        ],
        "Active" : false,
        "DOB" : "Sun Mar 10 1996 19:54:41 GMT+0000 (UTC)",
        "Age" : 38
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659afd"),
        "Name" : "Cooke Schroeder",
        "Address" : {
        "StreetNumber" : 246,
        "streetName" : "Huntington Street",
        "city" : "Allendale",
        "state" : "NH",
        "ZIPCode" : 64947
        },
        "Friends" : [
        "Rebekah Winters",
        "Grace Lewis",
        "Stephanie Hyde"
        ],
        "Active" : false,
        "DOB" : "Wed Oct 19 2016 15:38:31 GMT+0000 (UTC)",
        "Age" : 32
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659af6"),
        "Name" : "Craft Parks",
        "Address" : {
        "StreetNumber" : 397,
        "streetName" : "Hudson Avenue",
        "city" : "Glenbrook",
        "state" : "UT",
        "ZIPCode" : 96867
        },
        "Friends" : [
        "Love Short",
        "Dickerson Brock",
        "Berg Levy",
        "Lottie Pickett"
        ],
        "Active" : false,
        "DOB" : "Tue May 21 1996 13:17:58 GMT+0000 (UTC)",
        "Age" : 42
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659afc"),
        "Name" : "Hayes Weaver",
        "Address" : {
        "StreetNumber" : 722,
        "streetName" : "Diamond Street",
        "city" : "Belva",
        "state" : "OH",
        "ZIPCode" : 90952
        },
        "Friends" : [
        "Cecilia Oneill",
        "Trujillo Wilkins",
        "Clara Day",
        "Briana Ellis"
        ],
        "Active" : true,
        "DOB" : "Thu Sep 21 2000 06:45:36 GMT+0000 (UTC)",
        "Age" : 44
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659af8"),
        "Name" : "Levine Johnston",
        "Address" : {
        "StreetNumber" : 737,
        "streetName" : "McKinley Avenue",
        "city" : "Helen",
        "state" : "DE",
        "ZIPCode" : 48935
        },
        "Friends" : [
        "Willis Morrow",
        "Sutton Massey",
        "Gibson Herrera"
        ],
        "Active" : false,
        "DOB" : "Sat Jan 09 1988 00:04:53 GMT+0000 (UTC)",
        "Age" : 44
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659af4"),
        "Name" : "Patrick Thornton",
        "Address" : {
        "StreetNumber" : 152,
        "streetName" : "Newkirk Avenue",
        "city" : "Summertown",
        "state" : "ID",
        "ZIPCode" : 95160
        },
        "Friends" : [
        "Small Barlow",
        "Carson Cherry",
        "Leah Sherman",
        "Joyner Buckner"
        ],
        "Active" : true,
        "DOB" : "Sat Jan 12 2002 19:11:20 GMT+0000 (UTC)",
        "Age" : 25
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659af7"),
        "Name" : "Sandy Oneil",
        "Address" : {
        "StreetNumber" : 819,
        "streetName" : "Pierrepont Place",
        "city" : "Gibsonia",
        "state" : "AR",
        "ZIPCode" : 10032
        },
        "Friends" : [
        "Lorraine Berry",
        "Ola Brewer",
        "Sharlene Franklin",
        "Melanie Lynn",
        "Chandra Gilliam"
        ],
        "Active" : false,
        "DOB" : "Thu Aug 07 1980 05:30:31 GMT+0000 (UTC)",
        "Age" : 41
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659afa"),
        "Name" : "Susan Graham",
        "Address" : {
        "StreetNumber" : 797,
        "streetName" : "Doone Court",
        "city" : "Frierson",
        "state" : "IL",
        "ZIPCode" : 15612
        },
        "Friends" : [
        "Clark Sharpe",
        "Guerra Goodman",
        "Vinson Jones",
        "Swanson Avery",
        "Socorro Morse"
        ],
        "Active" : false,
        "DOB" : "Fri Mar 17 1972 15:42:29 GMT+0000 (UTC)",
        "Age" : 41
        }
        {
        "_id" : ObjectId("60bd8130474d1a7920659afb"),
        "Name" : "Workman Holloway",
        "Address" : {
        "StreetNumber" : 955,
        "streetName" : "Covert Street",
        "city" : "Sylvanite",
        "state" : "GA",
        "ZIPCode" : 95335
        },
        "Friends" : [
        "Sullivan Blackwell",
        "Ratliff Mccray"
        ],
        "Active" : false,
        "DOB" : "Thu Nov 12 1981 05:06:00 GMT+0000 (UTC)",
        "Age" : 29
        }

(Q3) List only the id and names sorted in reverse alphabetical order by name (Z-to-A).
        
        db.contacts.find({}, {_id:1, Name:1}).sort({Name:-1})

        { "_id" : ObjectId("60bd8130474d1a7920659afb"), "Name" : "Workman Holloway" }
        { "_id" : ObjectId("60bd8130474d1a7920659afa"), "Name" : "Susan Graham" }
        { "_id" : ObjectId("60bd8130474d1a7920659af7"), "Name" : "Sandy Oneil" }
        { "_id" : ObjectId("60bd8130474d1a7920659af4"), "Name" : "Patrick Thornton" }
        { "_id" : ObjectId("60bd8130474d1a7920659af8"), "Name" : "Levine Johnston" }
        { "_id" : ObjectId("60bd8130474d1a7920659afc"), "Name" : "Hayes Weaver" }
        { "_id" : ObjectId("60bd8130474d1a7920659af6"), "Name" : "Craft Parks" }
        { "_id" : ObjectId("60bd8130474d1a7920659afd"), "Name" : "Cooke Schroeder" }
        { "_id" : ObjectId("60bd8130474d1a7920659af9"), "Name" : "Aimee Mcintosh" }
        { "_id" : ObjectId("60bd8130474d1a7920659af5"), "Name" : "Aguirre Fox" }

(Q4) Is the comparison of the attribute name case-sensitive? Show how you try this with the previous query and include your answer.

    db.contacts.find({}, {_id:1, Name:1}).sort({Name:-1})
    The comparison is case sensitive because running the above command shows nothing.

(Q5) Repeat Q3 above but do not show the _id field.

    db.contacts.find({}, {_id:0, Name:1}).sort({Name:-1})

    { "Name" : "Workman Holloway" }
    { "Name" : "Susan Graham" }
    { "Name" : "Sandy Oneil" }
    { "Name" : "Patrick Thornton" }
    { "Name" : "Levine Johnston" }
    { "Name" : "Hayes Weaver" }
    { "Name" : "Craft Parks" }
    { "Name" : "Cooke Schroeder" }
    { "Name" : "Aimee Mcintosh" }
    { "Name" : "Aguirre Fox" }

(Q6) Insert the following document to the collection.  {Name: {First: “David”, Last: “Bark”}}  Does MongoDB accept this document while the name field has a different type than other records?

        Yes. 
        db.contacts.insert({Name: {First: "David", Last: "Bark"}})
        WriteResult({ "nInserted" : 1 })

(Q7) Where do you expect the new record to be located in the sort order? Verify the answer and explain.

    I expect the new record to be located at the top because the new name is a object.
    Since we are sorting in decending order objects will appear first. 


(Q8) Where do you expect the new document to appear in the sort order. Verify your answer and explain after running the query.

    db.contacts.insert({Name: ["David", "Bark"]})

    { "Name" : { "First" : "David", "Last" : "Bark" } }
    { "Name" : "Workman Holloway" }
    { "Name" : "Susan Graham" }
    { "Name" : "Sandy Oneil" }
    { "Name" : "Patrick Thornton" }
    { "Name" : "Levine Johnston" }
    { "Name" : "Hayes Weaver" }
    { "Name" : [ "David", "Bark" ] }
    { "Name" : "Craft Parks" }
    { "Name" : "Cooke Schroeder" }
    { "Name" : "Aimee Mcintosh" }
    { "Name" : "Aguirre Fox" }

    I expect the name to be in between Hayes and Craft because the Array will be 
    the same as the max value which is David. 

(Q9) Where do you expect the last inserted record, {Name: ["David", "Bark"]} to appear this time? Does it appear in the same position relative to the other records? Explain why or why not.


    db.contacts.find({}, {_id:0, Name:1}).sort({Name:1})
    { "Name" : "Aguirre Fox" }
    { "Name" : "Aimee Mcintosh" }
    { "Name" : [ "David", "Bark" ] }
    { "Name" : "Cooke Schroeder" }
    { "Name" : "Craft Parks" }
    { "Name" : "Hayes Weaver" }
    { "Name" : "Levine Johnston" }
    { "Name" : "Patrick Thornton" }
    { "Name" : "Sandy Oneil" }
    { "Name" : "Susan Graham" }
    { "Name" : "Workman Holloway" }
    { "Name" : { "First" : "David", "Last" : "Bark" } }

    Since the query is asecnding order, Bark will be used for the Array. 
    That means Array will be in between Aimee and Cooke 


(Q10) Build an index on the Name field for the users collection. Is MongoDB able to build the index on that field with the different value types stored in the Name field?
        
        Yes you can. 
        db.contacts.createIndex({Name:1})
        {
        "createdCollectionAutomatically" : false,
        "numIndexesBefore" : 1,
        "numIndexesAfter" : 2,
        "ok" : 1
        }
