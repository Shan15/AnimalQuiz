db = db.getSiblingDB('animalQuiz');

db.createCollection('animal');
db.animal.insertMany([
    {
        "animal": "Afrikanischer Elefant",
        "age": 40,
        "weight": 5000,
        "size": 300,
        "spawnrate": 1,
        "sleepover": 2,
        "animalSpecies": "Säugetiere"
    },
    {
        "animal": "Sibirischer Tiger",
        "age": 16,
        "weight": 300,
        "size": 120,
        "spawnrate": 4,
        "sleepover": 18,
        "animalSpecies": "Säugetiere"
    },
    {
        "animal": "Mississippi-Alligator",
        "age": 50,
        "weight": 230,
        "size": 240,
        "spawnrate": 30,
        "sleepover": 17,
        "animalSpecies": "Reptilien"
    },
    {
        "animal": "Komodowaran",
        "age": 30,
        "weight": 91,
        "size": 310,
        "spawnrate": 1,
        "sleepover": 17,
        "animalSpecies": "Reptilien"
    },

    {
        "animal": "Wombat",
        "age": 10,
        "weight": 35,
        "size": 120,
        "spawnrate": 1,
        "sleepover": 12,
        "animalSpecies": "Säugetiere"
    },
    {
        "animal": "Vielfrass",
        "age": 13,
        "weight": 25,
        "size": 110,
        "spawnrate": 4,
        "sleepover": 4,
        "animalSpecies": "Säugetiere"
    },
    {
        "animal": "Leistenkrokodil",
        "age": 70,
        "weight": 520,
        "size": 600,
        "spawnrate": 60,
        "sleepover": 0,
        "animalSpecies": "Reptilien"
    },
    {
        "animal": "Weisskopfseeadler",
        "age": 20,
        "weight": 25,
        "size": 100,
        "spawnrate": 3,
        "sleepover": 14,
        "animalSpecies": "Vögel"
    },
    {
        "animal": "Grizzlybär",
        "age": 25,
        "weight": 500,
        "size": 2,
        "spawnrate": 2,
        "sleepover": 14,
        "animalSpecies": "Säugetiere"
    },


]);

db.createCollection('questions');
db.questions.insertMany([


    {
        "question": "Welches Tier hat die längste Lebenserwartung?",
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
        "question": "Welches Tier ist das grösste?",
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
