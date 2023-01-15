db = db.getSiblingDB('animalQuiz');

db.createCollection('animal');
db.animal.insertMany([
    {
        "animal" : "Elephant",
        "maxAge" : 55
    },
    {
        "animal" : "Monkey",
        "maxAge" : 10
    }
]);
