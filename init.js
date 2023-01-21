db = db.getSiblingDB('animalQuiz');

db.createCollection('animal');
db.animal.insertMany([
    {
        "animal": "African Elephant",
        "age": 40,
        "weight": 5000,
        "size": 300,
        "spawnrate": 1,
        "sleepover": 2,
        "animalSpecies": "Säugetier"
    },
    {
        "animal": "Siberian Tiger",
        "age": 16,
        "weight": 300,
        "size": 100,
        "spawnrate": 4,
        "sleepover": 18,
        "animalSpecies": "Säugetier"
    },
    {
        "animal": "American Alligator",
        "age": 50,
        "weight": 230,
        "size": 240,
        "spawnrate": 30,
        "sleepover": 17,
        "animalSpecies": "Fisch"
    },
    {
        "animal": "Komodo Dragon",
        "age": 30,
        "weight": 91,
        "size": 310,
        "spawnrate": 1,
        "sleepover": 17,
        "animalSpecies": "Vogel"
    },
]);

db.createCollection('questions');
db.questions.insertMany([


    {
        "question": "Welches Tier kann 400 Jahre alt werden?",
        "property": "age",
        "criteria": "max"
    },

    {
        "question": "Welches Tier hat die kürzeste Lebenserwartung?",
        "property": "age",
        "criteria": "min"
    },
    {
        "question": "Welches Tier ist das schwerste?",
        "property": "weight",
        "criteria": "max"
    },

    {
        "question": "Welches Tier ist das leichteste?",
        "property": "weight",
        "criteria": "min"
    },


    {
        "question": "Welches Tier bringt nur ein Junges zur Welt?",
        "property": "spawnrate",
        "criteria": "min"
    },

    {
        "question": "Welches Tier bringt die meisten Jungen zur Welt?",
        "property": "spawnrate",
        "criteria": "max"
    },


    {
        "question": "Welches Tier schläft am längsten?",
        "property": "sleepover",
        "criteria": "max"
    },


    {
        "question": "Welches Tier ist das höchste?",
        "property": "size",
        "criteria": "max"
    },

    {
        "question": "Welches Tier ist das kleinste?",
        "property": "size",
        "criteria": "min"
    }
]);


db.createCollection("statistics");
db.statistics.insertMany([
    {
        "name": "Aha",
        "points": 1,
        "time": 1237,
    }
])
