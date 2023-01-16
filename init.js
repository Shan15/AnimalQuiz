db = db.getSiblingDB('animalQuiz');

db.createCollection('animal');
db.animal.insertMany([
    {
        "animal": "Elephant",
        "maxAge": 55
    },
    {
        "animal": "Monkey",
        "maxAge": 10
    }
]);

db.createCollection("statistics");
db.statistics.insertMany([
    {
        "name": "Aha",
        "points": 1,
        "time": 1237
    }
])
