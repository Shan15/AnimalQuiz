db = db.getSiblingDB('animalQuiz');

db.createCollection('animal');
db.animal.insertMany([
    {
        "animal": "Elephant",
        "age": 55
    },
    {
        "animal": "Monkey",
        "age": 10
    }
]);

db.createCollection('questions');
db.questions.insertMany([
    {
        "topic": "age",
        "questions": [
            {
                "question": "welches tier wird am aeltesten?",
                "property": "age",
                "criteria": "max"
            },
            {
                "question": "welches tier wird am fruehsten sterben?",
                "property": "age",
                "criteria": "min"
            }
        ]
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
